package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import core.lib.base.BaseFragment;
import kh.com.psnd.databinding.LayoutDetailAdapterBinding;
import kh.com.psnd.ui.adapter.DetailAdapter;

public class DetailAdapterView extends FrameLayout {

    private LayoutDetailAdapterBinding binding = null;
    private DetailAdapter              adapter = null;

    public DetailAdapterView(@NonNull Context context) {
        super(context);
        init();
    }

    public DetailAdapterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DetailAdapterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutDetailAdapterBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public void setupUI(@NonNull BaseFragment fragment, @IntRange(from = 1) @StringRes int titleHeader_1) {
        binding.header.header1.setText(titleHeader_1);
        adapter = new DetailAdapter(fragment);
        binding.recyclerView.setupUI(null);
        binding.recyclerView.setNestedScrollingEnabled(false);
        binding.recyclerView.setAdapter(adapter);
    }

}