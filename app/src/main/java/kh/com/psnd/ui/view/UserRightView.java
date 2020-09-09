package kh.com.psnd.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.chip.Chip;

import core.lib.databinding.ChipActionBinding;
import core.lib.utils.Log;
import kh.com.psnd.databinding.LayoutUserRightBinding;
import kh.com.psnd.network.model.UserRole;
import kh.com.psnd.network.model.UserRolePrivilege;
import kh.com.psnd.ui.fragment.user.AddUserFragment;
import kh.com.psnd.ui.fragment.user.SelectUserRightFragment;
import lombok.val;

public class UserRightView extends FrameLayout {

    private LayoutUserRightBinding binding = null;

    public UserRightView(@NonNull Context context) {
        super(context);
        init();
    }

    public UserRightView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserRightView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutUserRightBinding.inflate(LayoutInflater.from(getContext()), this, true);

        if (isInEditMode()) {

        }
    }

    public void setupUI(@NonNull AddUserFragment fragment, @NonNull UserRolePrivilege userRolePrivilege, @NonNull UserRole userRole) {
        Log.i("Current User Role : " + userRole);
        binding.btnEdit.setOnClickListener(__ -> {
            val selectUserRightFragment = SelectUserRightFragment.newInstance(userRolePrivilege, userRole);
            selectUserRightFragment.setCallback(currentUserRole -> setupUI(fragment, userRolePrivilege, currentUserRole));
            selectUserRightFragment.show(fragment.getActivity().getSupportFragmentManager(), "");
        });
        setTag(userRole);
        binding.currentUserRole.setText(userRole.getName());
        binding.groupUserPrivileges.removeAllViews();
        if (userRole.getPrivileges() != null) {
            for (val privilege : userRole.getPrivileges()) {
                if (privilege != null) {
                    val chipBinding = ChipActionBinding.inflate(LayoutInflater.from(getContext()));
                    val chip        = (Chip) chipBinding.getRoot();
                    chip.setText(privilege.getName());
                    chip.setCheckable(false);
                    chip.setClickable(false);
                    binding.groupUserPrivileges.addView(chip);
                }
            }
        }
    }

}