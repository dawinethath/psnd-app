package kh.com.psnd.ui.view;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.lib.base.BaseFragment;
import core.lib.listener.MyTextWatcher;
import core.lib.utils.Log;
import kh.com.psnd.databinding.LayoutSearchBarBinding;
import kh.com.psnd.helper.ActivityHelper;
import lombok.Getter;
import lombok.Setter;

public class SearchBarView extends FrameLayout {

    private final long                   TIME_DELAY = 500L;
    @Getter
    private       LayoutSearchBarBinding binding    = null;
    private       Callback               callback   = null;
    private       BaseFragment           fragment   = null;
    private       Handler                handler    = new Handler();
    private       MyRunnable             runnable   = new MyRunnable();

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
        binding.textField.setStartIconOnClickListener(__ -> ActivityHelper.openSearchOptionActivity(getContext()));
        binding.textField.setEndIconOnClickListener(__ -> {
            binding.txtSearch.setText("");
            callback.onClickedClear();
        });
        binding.txtSearch.addTextChangedListener(onTextChangeListener);
        binding.txtSearch.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, TIME_DELAY);
                return true;
            }
            return false;
        });
    }

    private TextWatcher onTextChangeListener = new MyTextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.i(charSequence);
            if (TextUtils.isEmpty(binding.txtSearch.getText().toString())) {
                callback.onClickedClear();
            }
            else {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, TIME_DELAY);
            }
        }
    };

    @Setter
    public class MyRunnable implements Runnable {

        @Override
        public void run() {
            callback.doSearch(binding.txtSearch.getText());
        }
    }

    public interface Callback {

        void doSearch(CharSequence search);

        void onClickedClear();
    }

    public void onPause() {
        handler.removeCallbacks(runnable);
    }
}