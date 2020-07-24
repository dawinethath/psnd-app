package kh.com.psnd.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.google.android.material.chip.Chip;

import core.lib.base.BaseApp;
import core.lib.base.BaseBottomSheetDialogFragment;
import core.lib.databinding.ChipActionBinding;
import core.lib.databinding.ChipEntryBinding;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSelectUserRightBinding;
import kh.com.psnd.mock.MockUsers;
import kh.com.psnd.network.model.UserPrivilege;
import kh.com.psnd.network.model.UserRole;
import lombok.Setter;
import lombok.val;

public class SelectUserRightFragment extends BaseBottomSheetDialogFragment<FragmentSelectUserRightBinding> {

    public static SelectUserRightFragment newInstance(@NonNull UserRole userRole) {
        val fragment = new SelectUserRightFragment();
        val bundle   = new Bundle();
        bundle.putSerializable(UserRole.EXTRA, userRole);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Setter
    private Callback callback;

    @Override
    public void setupUI() {
        val currentRole = (UserRole) getArguments().getSerializable(UserRole.EXTRA);

        initUserRole(currentRole);
        loadChooseUserPrivileges();
        loadChooseUserRoles(currentRole);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (callback != null) {
            val currentUserRole = (UserRole) binding.currentUserRole.getTag();
            callback.dismiss(currentUserRole);
        }
    }

    private UserRole getCustomUserRole() {
        for (val item : MockUsers.userRoles) {
            if (item.getId() == MockUsers.ROLE_CUSTOM_ID) {
                return item;
            }
        }
        return null;
    }

    private void initUserRole(UserRole userRole) {
        val     jsonUserRolePrivilege = BaseApp.getGson().toJson(userRole.getPrivileges());
        boolean found                 = false;
        for (val item : MockUsers.userRoles) {
            val jsonPrivilege = BaseApp.getGson().toJson(item.getPrivileges());
            if (jsonPrivilege.length() == jsonUserRolePrivilege.length()) {
                userRole.setId(item.getId());
                userRole.setName(item.getName());
                found = true;
                break;
            }
        }
        if (!found) {
            val customUserRole = getCustomUserRole();
            userRole.setId(customUserRole.getId());
            userRole.setName(customUserRole.getName());
        }

        val item = userRole.clone();
        binding.currentUserRole.setTag(item);
        binding.currentUserRole.setText(item.getName());
        loadUserPrivileges(item);
    }

    private void selectedRole(UserRole userRole) {
        Log.i(userRole);
        initUserRole(userRole);
    }

    private void selectedPrivilege(UserPrivilege privilege) {
        Log.i(privilege);
        val currentUserRole = (UserRole) binding.currentUserRole.getTag();
        currentUserRole.addPrivilege(privilege.clone());
        initUserRole(currentUserRole);
    }

    private void removedPrivilege(UserPrivilege privilege) {
        Log.i(privilege);
        val currentUserRole = (UserRole) binding.currentUserRole.getTag();
        currentUserRole.removePrivilege(privilege.clone());
        initUserRole(currentUserRole);
    }

    private void loadUserPrivileges(UserRole currentUserRole) {
        binding.groupUserPrivileges.removeAllViews();
        if (currentUserRole.getPrivileges() != null) {
            for (val privilege : currentUserRole.getPrivileges()) {
                if (privilege != null) {
                    Chip chip = null;
                    if (privilege.getId() == MockUsers.PRIVILEGE_BASIC_INFO_ID) {
                        val chipBinding = ChipActionBinding.inflate(LayoutInflater.from(getContext()));
                        chip = (Chip) chipBinding.getRoot();
                    }
                    else {
                        val chipBinding = ChipEntryBinding.inflate(LayoutInflater.from(getContext()));
                        chip = (Chip) chipBinding.getRoot();
                        chip.setCheckable(false);
                    }
                    chip.setClickable(false);
                    chip.setText(privilege.getName());
                    chip.setTag(privilege);
                    chip.setOnCloseIconClickListener(__ -> removedPrivilege(privilege));
                    binding.groupUserPrivileges.addView(chip);
                }
            }
        }
    }

    private void loadChooseUserPrivileges() {
        binding.groupChooseUserPrivileges.removeAllViews();
        for (val privilege : MockUsers.userPrivileges) {
            if (privilege != null && privilege.getId() != MockUsers.PRIVILEGE_BASIC_INFO_ID) {
                val chipBinding = ChipActionBinding.inflate(LayoutInflater.from(getContext()));
                val chip        = (Chip) chipBinding.getRoot();
                chip.setText(privilege.getName());
                chip.setTag(privilege);
                chip.setOnClickListener(__ -> selectedPrivilege(privilege));
                binding.groupChooseUserPrivileges.addView(chip);
            }
        }
    }

    private void loadChooseUserRoles(UserRole currentUserRole) {
        binding.groupChooseUserRoles.removeAllViews();
        for (val userRole : MockUsers.userRoles) {
            if (userRole != null && userRole.getId() != MockUsers.ROLE_CUSTOM_ID) {
                val chipBinding = ChipActionBinding.inflate(LayoutInflater.from(getContext()));
                val chip        = (Chip) chipBinding.getRoot();
                chip.setText(userRole.getName());
                chip.setTag(userRole);
                chip.setOnClickListener(__ -> selectedRole(userRole));
                binding.groupChooseUserRoles.addView(chip);
            }
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_select_user_right;
    }

    public interface Callback {
        void dismiss(@NonNull UserRole currentUserRole);
    }
}