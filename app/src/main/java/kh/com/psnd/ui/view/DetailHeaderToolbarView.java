package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.lib.base.BaseFragment;
import kh.com.psnd.databinding.LayoutDetailHeaderToolbarBinding;
import kh.com.psnd.mock.MockData;
import kh.com.psnd.network.model.Staff;

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
        }
    }

    public void setupUI(@NonNull BaseFragment fragment, @NonNull Staff staff) {
        binding.imageProfile.setImageURI(staff.getPhoto());
        binding.firstNameKH.setText(staff.getFullName());
        binding.staffId.setText(staff.getId());
    }

}