package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.lib.base.BaseFragment;
import kh.com.psnd.databinding.LayoutDetailHeaderBinding;

public class LayoutDetailHeaderView extends FrameLayout {

    private LayoutDetailHeaderBinding binding = null;

    public LayoutDetailHeaderView(@NonNull Context context) {
        super(context);
        init();
    }

    public LayoutDetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LayoutDetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutDetailHeaderBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public void setupUI(@NonNull BaseFragment fragment) {

    }

}