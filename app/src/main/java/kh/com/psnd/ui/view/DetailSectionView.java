package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import java.util.List;

import core.lib.base.BaseFragment;
import kh.com.psnd.databinding.LayoutDetailSectionBinding;
import kh.com.psnd.network.model.StaffRecord;
import lombok.val;

public class DetailSectionView extends FrameLayout {

    private LayoutDetailSectionBinding binding = null;

    public DetailSectionView(@NonNull Context context) {
        super(context);
        init();
    }

    public DetailSectionView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DetailSectionView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutDetailSectionBinding.inflate(LayoutInflater.from(getContext()), this, true);
        if (!isInEditMode()) {
            setVisibility(GONE);
        }
    }

    public void setupUI(@NonNull BaseFragment fragment, List<StaffRecord> staffRecords, @Nullable ItemDetailSectionView.Callback callback, @IntRange(from = 1) @StringRes int topHeader, @IntRange(from = 1) @StringRes int... headers) {
        if (staffRecords == null || staffRecords.size() == 0) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);
        {
            binding.title.setText(topHeader);
            val view = new ItemDetailSectionHeaderView(getContext());
            view.bind(headers);
            binding.recyclerView.addView(view);
        }

        for (val item : staffRecords) {
            val view = new ItemDetailSectionView(getContext());
            view.bind(item, callback);
            binding.recyclerView.addView(view);
        }
    }

}