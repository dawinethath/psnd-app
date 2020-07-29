package kh.com.psnd.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.chip.Chip;

import java.util.List;

import core.lib.databinding.ChipActionBinding;
import kh.com.psnd.database.dao.SearchHistoryDao;
import kh.com.psnd.databinding.LayoutHistoryChipGroupBinding;
import lombok.val;

public class SearchHistoryChipGroupView extends FrameLayout {

    private LayoutHistoryChipGroupBinding binding = null;

    public SearchHistoryChipGroupView(@NonNull Context context) {
        super(context);
        init();
    }

    public SearchHistoryChipGroupView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchHistoryChipGroupView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutHistoryChipGroupBinding.inflate(LayoutInflater.from(getContext()), this, true);
        if (!isInEditMode()) {
            setVisibility(GONE);
        }
    }

    public void setupUI(@NonNull Callback callback) {
        binding.chipGroup.removeAllViews();
        List<String> labels = SearchHistoryDao.getCache().getSearch();
        for (val label : labels) {
            if (!TextUtils.isEmpty(label)) {
                val chipBinding = ChipActionBinding.inflate(LayoutInflater.from(getContext()));
                val chip        = (Chip) chipBinding.getRoot();
                chip.setText(label);
                chip.setEnsureMinTouchTargetSize(false);
                chip.setOnClickListener(__ -> callback.clicked(label));
                binding.chipGroup.addView(chip);
            }
        }
        setVisibility(binding.chipGroup.getChildCount() == 0 ? GONE : VISIBLE);
    }

    interface Callback {
        void clicked(String str);
    }
}