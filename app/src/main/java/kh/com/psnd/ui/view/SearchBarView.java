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
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.databinding.LayoutSearchBarBinding;
import kh.com.psnd.network.model.SearchFilter;
import kh.com.psnd.ui.fragment.SearchOptionFragment;
import lombok.Getter;
import lombok.Setter;

public class SearchBarView extends FrameLayout {

    private final long                   TIME_DELAY           = 500L;
    @Getter
    private       LayoutSearchBarBinding binding              = null;
    private       Callback               callback             = null;
    private       BaseFragment           fragment             = null;
    private       Handler                handler              = new Handler();
    private       MyRunnable             runnable             = new MyRunnable();
    private       SearchOptionFragment   searchOptionFragment = null;

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

    public void onResume() {
        binding.txtSearch.setSelection(binding.txtSearch.getText().length());
        binding.searchHistory.setupUI(callbackHistory);
    }

    private SearchHistoryChipGroupView.Callback callbackHistory = new SearchHistoryChipGroupView.Callback() {
        @Override
        public void clicked(Object object) {
            Log.i(object);
            if (object instanceof String) {
                binding.txtSearch.setText(object.toString());
                binding.txtSearch.setSelection(binding.txtSearch.getText().length());
            }
            else if (object instanceof SearchFilter) {
                callback.doSearch((SearchFilter) object);
            }
        }

        @Override
        public void showSearchOption(SearchFilter filter) {
            searchOptionFragment.show(filter);
        }
    };

    public String getSearchString() {
        return binding.txtSearch.getText().toString();
    }

    public void setupUI(@NonNull BaseFragment fragment, @NonNull Callback callback) {
        this.fragment = fragment;
        this.callback = callback;
        searchOptionFragment = new SearchOptionFragment(getContext(), filter -> {
            binding.searchHistory.setupUI(callbackHistory);
            callback.doSearch(filter);
        }, fragment.getCompositeDisposable());
        //  searchOptionFragment.setPercentHeightDialog(0.9f);
        searchOptionFragment.setPercentWidthDialog(0.99f);

        binding.txtSearch.requestFocus();
        binding.txtSearch.postDelayed(() -> ApplicationUtil.showKeyboard(getContext(), binding.txtSearch), 600);
        binding.textField.setStartIconOnClickListener(__ -> {
            searchOptionFragment.show();
        });
        binding.textField.setEndIconOnClickListener(__ -> {
            binding.txtSearch.setText("");
            ApplicationUtil.showKeyboard(getContext(), binding.txtSearch);
            callback.onClickedClear();
        });
        binding.txtSearch.addTextChangedListener(onTextChangeListener);
        binding.txtSearch.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            switch (actionId) {
                case EditorInfo.IME_ACTION_DONE:
                case EditorInfo.IME_ACTION_SEARCH:
                    handler.removeCallbacks(runnable);
                    handler.postDelayed(runnable, TIME_DELAY);
                    return true;
            }
            return false;
        });
    }

    public void clearTextBox() {
        binding.txtSearch.removeTextChangedListener(onTextChangeListener);
        binding.txtSearch.setText("");
        binding.txtSearch.addTextChangedListener(onTextChangeListener);
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

        void doSearch(SearchFilter filter);

        void onClickedClear();
    }

    public void onPause() {
        handler.removeCallbacks(runnable);
    }
}