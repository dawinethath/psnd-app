package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.lib.base.BaseFragment;
import kh.com.psnd.databinding.LayoutDetailHeaderToolbarBinding;

public class DetailHeaderToolbarView extends FrameLayout {

    private LayoutDetailHeaderToolbarBinding binding = null;

    public DetailHeaderToolbarView(@NonNull Context context) {
        super(context);
        init();
    }

    public DetailHeaderToolbarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DetailHeaderToolbarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutDetailHeaderToolbarBinding.inflate(LayoutInflater.from(getContext()), this, true);
        if (!isInEditMode()) {
            binding.imageProfile.setImageURI(DetailHeaderView.TEST_IMAGE);
        }
    }

    public void setupUI(@NonNull BaseFragment fragment) {

    }

}