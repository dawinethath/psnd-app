package kh.com.psnd.ui.fragment.user;

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
import kh.com.psnd.network.model.UserRolePrivilege;
import lombok.Setter;
import lombok.val;

public class SelectUserRightFragment extends BaseBottomSheetDialogFragment<FragmentSelectUserRightBinding> {

    public static SelectUserRightFragment newInstance(@NonNull UserRolePrivilege userRolePrivilege, @NonNull UserRole userRole) {
        val fragment = new SelectUserRightFragment();
        val bundle   = new Bundle();
        bundle.putSerializable(UserRole.EXTRA, userRole);
        bundle.putSerializable(UserRolePrivilege.EXTRA, userRolePrivilege);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Setter
    private Callback callback;

    @Override
    public void setupUI() {
        val currentRole       = (UserRole) getArguments().getSerializable(UserRole.EXTRA);
        val userRolePrivilege = (UserRolePrivilege) getArguments().getSerializable(UserRolePrivilege.EXTRA);

        initUserRole(userRolePrivilege, currentRole);
        loadChooseUserPrivileges(userRolePrivilege);
        loadChooseUserRoles(userRolePrivilege);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (callback != null) {
            val currentUserRole = (UserRole) binding.currentUserRole.getTag();
            callback.dismiss(currentUserRole);
        }
    }

    private UserRole getCustomUserRole(UserRolePrivilege userRolePrivilege) {
        for (val item : userRolePrivilege.getRoles()) {
            if (item.getKeyName().equals(MockUsers.ROLE_CUSTOM_ID)) {
                return item;
            }
        }
        return null;
    }

    private void initUserRole(UserRolePrivilege userRolePrivilege, UserRole userRole) {
        val     jsonUserRolePrivilege = BaseApp.getGson().toJson(userRole.getPrivileges());
        boolean found                 = false;
        for (val item : userRolePrivilege.getRoles()) {
            val jsonPrivilege = BaseApp.getGson().toJson(item.getPrivileges());
            if (jsonPrivilege.length() == jsonUserRolePrivilege.length()) {
                userRole.setKeyName(item.getKeyName());
                userRole.setName(item.getName());
                found = true;
                break;
            }
        }
        if (!found) {
            val customUserRole = getCustomUserRole(userRolePrivilege);
            if (customUserRole != null) {
                userRole.setKeyName(customUserRole.getKeyName());
                userRole.setName(customUserRole.getName());
            }
        }

        val item = userRole.clone();
        binding.currentUserRole.setTag(item);
        binding.currentUserRole.setText(item.getName());
        loadUserPrivileges(userRolePrivilege, item);
    }

    private void selectedRole(UserRolePrivilege userRolePrivilege, UserRole userRole) {
        Log.i(userRole);
        initUserRole(userRolePrivilege, userRole);
    }

    private void selectedPrivilege(UserRolePrivilege userRolePrivilege, UserPrivilege privilege) {
        Log.i(privilege);
        val currentUserRole = (UserRole) binding.currentUserRole.getTag();
        currentUserRole.addPrivilege(privilege.clone());
        initUserRole(userRolePrivilege, currentUserRole);
    }

    private void removedPrivilege(UserRolePrivilege userRolePrivilege, UserPrivilege privilege) {
        Log.i(privilege);
        val currentUserRole = (UserRole) binding.currentUserRole.getTag();
        currentUserRole.removePrivilege(privilege.clone());
        initUserRole(userRolePrivilege, currentUserRole);
    }

    private void loadUserPrivileges(UserRolePrivilege userRolePrivilege, UserRole currentUserRole) {
        binding.groupUserPrivileges.removeAllViews();
        if (currentUserRole.getPrivileges() != null) {
            for (val privilege : currentUserRole.getPrivileges()) {
                if (privilege != null) {
                    Chip chip = null;
                    if (privilege.getKeyName().equals(MockUsers.PRIVILEGE_DEFAULT_BASIC_INFO_ID)) {
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
                    chip.setOnCloseIconClickListener(__ -> removedPrivilege(userRolePrivilege, privilege));
                    binding.groupUserPrivileges.addView(chip);
                }
            }
        }
    }

    private void loadChooseUserPrivileges(UserRolePrivilege userRolePrivilege) {
        binding.groupChooseUserPrivileges.removeAllViews();
        for (val privilege : userRolePrivilege.getPrivileges()) {
            if (privilege != null && !privilege.getKeyName().equals(MockUsers.PRIVILEGE_DEFAULT_BASIC_INFO_ID)) {
                val chipBinding = ChipActionBinding.inflate(LayoutInflater.from(getContext()));
                val chip        = (Chip) chipBinding.getRoot();
                chip.setText(privilege.getName());
                chip.setTag(privilege);
                chip.setOnClickListener(__ -> selectedPrivilege(userRolePrivilege, privilege));
                binding.groupChooseUserPrivileges.addView(chip);
            }
        }
    }

    private void loadChooseUserRoles(UserRolePrivilege userRolePrivilege) {
        binding.groupChooseUserRoles.removeAllViews();
        for (val userRole : userRolePrivilege.getRoles()) {
            val chipBinding = ChipActionBinding.inflate(LayoutInflater.from(getContext()));
            val chip        = (Chip) chipBinding.getRoot();
            chip.setText(userRole.getName());
            chip.setTag(userRole);
            chip.setOnClickListener(__ -> selectedRole(userRolePrivilege, userRole));
            binding.groupChooseUserRoles.addView(chip);
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