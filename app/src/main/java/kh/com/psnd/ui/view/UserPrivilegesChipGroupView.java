package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import core.lib.databinding.TextOutlineBinding;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.LayoutPrivilegesChipGroupBinding;
import kh.com.psnd.network.model.UserPrivilege;
import kh.com.psnd.network.model.UserProfile;
import lombok.val;

public class UserPrivilegesChipGroupView extends FrameLayout {

    private LayoutPrivilegesChipGroupBinding binding = null;

    public UserPrivilegesChipGroupView(@NonNull Context context) {
        super(context);
        init();
    }

    public UserPrivilegesChipGroupView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserPrivilegesChipGroupView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutPrivilegesChipGroupBinding.inflate(LayoutInflater.from(getContext()), this, true);
        if (!isInEditMode()) {
            setVisibility(GONE);
        }
    }

    public void setupUI(@NonNull UserProfile userProfile) {
        binding.chipGroup.removeAllViews();
        val privileges = userProfile.getPrivileges();
        for (val privilege : privileges) {
            Log.i("privilege : " + privilege);
            val chip = makeChip(privilege);
            binding.chipGroup.addView(chip);
        }
        setVisibility(binding.chipGroup.getChildCount() == 0 ? GONE : VISIBLE);
    }

    private View makeChip(UserPrivilege privilege) {
        val textOutlineBinding = TextOutlineBinding.inflate(LayoutInflater.from(getContext()));
        textOutlineBinding.text.setText(privilege.getName());
        textOutlineBinding.text.setTag(privilege);
        textOutlineBinding.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimension(R.dimen.text_size_13sp));
        return textOutlineBinding.getRoot();
    }

}