package kh.com.psnd.service;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import core.lib.base.BaseIntentService;
import core.lib.utils.Log;
import io.reactivex.disposables.CompositeDisposable;
import kh.com.psnd.eventbus.LoadRolePrivilegeEventBus;
import kh.com.psnd.network.response.ResponseUserRolePrivilege;
import kh.com.psnd.network.task.TaskUserRolePrivilege;
import lombok.val;
import retrofit2.Response;

public class LoadRolePrivilegeService extends BaseIntentService {

    public static void start(Context context, String clazzName) {
        val intent = new Intent(context, LoadRolePrivilegeService.class);
        intent.putExtra("CLASS_NAME", clazzName);
        context.startService(intent);
    }

    public LoadRolePrivilegeService() {
        super(LoadRolePrivilegeService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        val clazz = intent.getStringExtra("CLASS_NAME");
        Log.i("From class name : " + clazz);
        val task = new TaskUserRolePrivilege();
        new CompositeDisposable().add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseUserRolePrivilege) result.body();
                    EventBus.getDefault().postSticky(new LoadRolePrivilegeEventBus(data.getResult(), clazz));
                    return;
                }
                EventBus.getDefault().postSticky(new LoadRolePrivilegeEventBus(null, clazz));
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
                EventBus.getDefault().postSticky(new LoadRolePrivilegeEventBus(null, clazz));
            }
        }));
    }
}
