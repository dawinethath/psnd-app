package kh.com.psnd.service;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import core.lib.base.BaseIntentService;
import core.lib.utils.Log;
import io.reactivex.annotations.NonNull;
import kh.com.psnd.network.request.RequestConfig;
import kh.com.psnd.network.response.ResponseConfig;
import kh.com.psnd.network.task.TaskConfig;
import lombok.val;
import retrofit2.Response;

public class ConfigService extends BaseIntentService {

    public static void start(Context context) {
        val intent = new Intent(context, ConfigService.class);
        context.startService(intent);
    }

    public ConfigService() {
        super(ConfigService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        val task = new TaskConfig();
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onReceiveResult(@NonNull RequestConfig request, @NonNull Response result) throws Exception {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseConfig) result.body();
                    if (data != null) {
                        data.getResult().saveToCache();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
            }
        }));
    }
}
