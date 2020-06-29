package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.lib.base.BaseFragment;
import kh.com.psnd.databinding.LayoutDetailHeaderBinding;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.mock.MockData;

public class DetailHeaderView extends FrameLayout {

    private LayoutDetailHeaderBinding binding = null;

    public DetailHeaderView(@NonNull Context context) {
        super(context);
        init();
    }

    public DetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutDetailHeaderBinding.inflate(LayoutInflater.from(getContext()), this, true);
        if (!isInEditMode()) {
            binding.imageProfile.setImageURI(MockData.TEST_IMAGE);
        }
    }

    public void setupUI(@NonNull BaseFragment fragment) {
        binding.cardImageProfile.setOnClickListener(__ -> ActivityHelper.openImagePreviewActivity(getContext(), MockData.TEST_IMAGE));
    }

}