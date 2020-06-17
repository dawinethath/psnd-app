package kh.com.psnd.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import core.lib.base.BaseFragment;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.LayoutSearchBarBinding;
import kh.com.psnd.network.request.RequestSearch;
import kh.com.psnd.network.response.ResponseSearch;
import kh.com.psnd.network.task.TaskSearch;
import kh.com.psnd.ui.activity.SearchBySectionActivity;
import lombok.Setter;
import lombok.val;
import retrofit2.Response;

public class SearchBarView extends FrameLayout {

    private final long TIME_DELAY = 1_000L;
    private LayoutSearchBarBinding binding = null;
    private Callback callback = null;
    private BaseFragment fragment = null;
    private Handler handler = new Handler();
    private MyRunnable runnable = new MyRunnable();

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
        this.fragment = fragment;
        this.callback = callback;
        binding.textField.setStartIconOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                val intent = new Intent(getContext(), SearchBySectionActivity.class);
                fragment.startActivity(intent);
            }
        });
        binding.txtSearch.addTextChangedListener(onTextChangeListener);
    }

    private TextWatcher onTextChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.i(charSequence);
            runnable.setSearch(charSequence);
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, TIME_DELAY);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void doSearch(CharSequence search) {
        if (TextUtils.isEmpty(search)) {
            // todo do something here
            Log.i("Search : " + search);
            return;
        }
        if (!ApplicationUtil.isOnline()) {
            Snackbar.make(fragment.getView(), R.string.noInternetConnection, Snackbar.LENGTH_SHORT).show();
            return;
        }
        Log.i("Sent request to server...");
        val compositeDisposable = fragment.getCompositeDisposable();
        val task = new TaskSearch(new RequestSearch(search));
        compositeDisposable.add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return ResponseSearch.class;
            }

            @Override
            public void onReceiveResult(RequestSearch request, Response result) throws Exception {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    callback.onResult();
                }
                setVisibility(GONE);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
            }
        }));
    }

    @Setter
    public class MyRunnable implements Runnable {
        private CharSequence search;

        @Override
        public void run() {
            doSearch(search);
        }
    }

    public interface Callback {

        void onResult();

        void onClickedClear();
    }

    public void onPause() {
        handler.removeCallbacks(runnable);
    }
}