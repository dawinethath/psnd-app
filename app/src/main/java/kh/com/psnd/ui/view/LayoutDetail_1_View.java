package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.lib.base.BaseFragment;
import kh.com.psnd.databinding.LayoutDetail1Binding;

public class LayoutDetail_1_View extends FrameLayout {

    private LayoutDetail1Binding binding = null;

    public LayoutDetail_1_View(@NonNull Context context) {
        super(context);
        init();
    }

    public LayoutDetail_1_View(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LayoutDetail_1_View(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutDetail1Binding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public void setupUI(@NonNull BaseFragment fragment) {

    }

}