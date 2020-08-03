package kh.com.psnd.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.chip.Chip;

import core.lib.databinding.ChipEntryBinding;
import core.lib.utils.Log;
import kh.com.psnd.database.dao.SearchHistoryDao;
import kh.com.psnd.databinding.LayoutHistoryChipGroupBinding;
import kh.com.psnd.network.model.SearchFilter;
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

        val cache  = SearchHistoryDao.getCache();
        val labels = cache.getSearch();
        for (val label : labels) {
            Log.i("label : " + label);
            if (!TextUtils.isEmpty(label) && !SearchHistoryDao.LAST_FILTER.equals(label)) {
                val chip = makeChip(label);
                chip.setOnClickListener(__ -> callback.clicked(label));
                chip.setOnCloseIconClickListener(__ -> {
                    cache.remove(label);
                    setupUI(callback);
                });
                binding.chipGroup.addView(chip);
            }
            else if (SearchHistoryDao.LAST_FILTER.equals(label)) {
                val filter = SearchFilter.getLastFilter();
                if (filter != null) {
                    val labelChip = filter.getLabelChip();
                    if (!TextUtils.isEmpty(labelChip)) {
                        val chip = makeChip(labelChip);
                        chip.setOnClickListener(__ -> callback.showSearchOption(filter));
                        chip.setOnCloseIconClickListener(__ -> {
                            SearchFilter.clearLastFilter();
                            setupUI(callback);
                        });
                        binding.chipGroup.addView(chip);
                    }
                }
            }
        }
        setVisibility(binding.chipGroup.getChildCount() == 0 ? GONE : VISIBLE);
    }

    private Chip makeChip(String label) {
        val chipBinding = ChipEntryBinding.inflate(LayoutInflater.from(getContext()));
        val chip        = (Chip) chipBinding.getRoot();
        chip.setText(label);
        chip.setEnsureMinTouchTargetSize(false);
        return chip;
    }

    interface Callback {
        void clicked(Object object);

        void showSearchOption(SearchFilter filter);
    }
}