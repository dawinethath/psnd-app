package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.lib.base.BaseFragment;
import core.lib.utils.Log;
import kh.com.psnd.databinding.LayoutSearchBarBinding;
import kh.com.psnd.network.request.RequestLogin;
import kh.com.psnd.network.response.ResponseLogin;
import kh.com.psnd.network.task.TaskLogin;
import lombok.val;
import retrofit2.Response;

public class SearchBarView extends FrameLayout {
    private LayoutSearchBarBinding binding  = null;
    private Callback               callback = null;

    public SearchBarView(@NonNull Context context) {
        super(context);
        init();
    }

    public SearchBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchBarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutSearchBarBinding.inflate(LayoutInflater.from(getContext()), this, true);

        if (isInEditMode()) {

        }
    }

    public void setupUI(@NonNull BaseFragment fragment, @NonNull Callback callback) {
        this.callback = callback;

    }

    private void doSearch(@NonNull BaseFragment fragment, String search) {
        val task = new TaskLogin(new RequestLogin("username", "pwd"));
        fragment.getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return ResponseLogin.class;
            }

            @Override
            public void onReceiveResult(RequestLogin request, Response result) throws Exception {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {

                }
                setVisibility(GONE);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
            }
        }));
    }

    public abstract static class Callback {

        public void onResult() {
        }
    }
}