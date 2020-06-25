package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.lib.base.BaseFragment;
import kh.com.psnd.databinding.LayoutDetailHeaderBinding;

public class DetailHeaderView extends FrameLayout {

    public static final String                    TEST_IMAGE = "https://psnd.app/Uploads/cards/2020/3/c8de91a6-1957-4524-bbd0-db55ca3b3791.jpg";
    private             LayoutDetailHeaderBinding binding    = null;

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
            binding.imageProfile.setImageURI(TEST_IMAGE);
        }
    }

    public void setupUI(@NonNull BaseFragment fragment) {

    }

}