package kh.com.psnd.ui.fragment.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import core.lib.base.BaseApp;
import core.lib.base.BaseBottomSheetDialogFragment;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ChipActionAdminBinding;
import kh.com.psnd.databinding.ChipEntryAdminBinding;
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
    private Callback       callback;
    private List<UserRole> allRoles = new ArrayList<>();

    @Override
    public void setupUI() {
        val currentRole       = (UserRole) getArguments().getSerializable(UserRole.EXTRA);
        val userRolePrivilege = (UserRolePrivilege) getArguments().getSerializable(UserRolePrivilege.EXTRA);
        allRoles.clear();
        if (currentRole.getKeyName().toLowerCase().equals("vip")) {
            for (val role : userRolePrivilege.getRoles()) {
                if (role.getKeyName().toLowerCase().equals("vip")) {
                    allRoles.add(role);
                    break;
                }
            }
        }
        else {
            for (val role : userRolePrivilege.getRoles()) {
                if (!role.getKeyName().toLowerCase().equals("vip")) {
                    allRoles.add(role);
                }
            }
        }

        UserRole selectedUserRole = null;
        for (val role : userRolePrivilege.getRoles()) {
            if (role.getKeyName().toLowerCase().equals(currentRole.getKeyName().toLowerCase())) {
                selectedUserRole = role;
                break;
            }
        }

        initUserRole(currentRole);
        loadChooseUserRoles();
        loadChooseUserPrivileges(selectedUserRole);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (callback != null) {
            val currentUserRole = (UserRole) binding.currentUserRole.getTag();
            callback.dismiss(currentUserRole);
        }
    }

    private void initUserRole(UserRole userRole) {
        val jsonUserRolePrivilege = BaseApp.getGson().toJson(userRole.getPrivileges());
        for (val item : allRoles) {
            val jsonPrivilege = BaseApp.getGson().toJson(item.getPrivileges());
            if (jsonPrivilege.length() == jsonUserRolePrivilege.length()) {
                userRole.setKeyName(item.getKeyName());
                userRole.setName(item.getName());
                break;
            }
        }

        val item = userRole.clone();
        binding.currentUserRole.setTag(item);
        binding.currentUserRole.setText(item.getName());
        loadUserPrivileges(item);
    }

    private void selectedRole(UserRole userRole) {
        Log.i(userRole);
        initUserRole(userRole);
        loadChooseUserPrivileges(userRole);
    }

    private void selectedPrivilege(UserRole userRole, UserPrivilege privilege) {
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
                    if (privilege.getKeyName().equals(MockUsers.PRIVILEGE_DEFAULT_BASIC_INFO_ID)) {
                        val chipBinding = ChipActionAdminBinding.inflate(LayoutInflater.from(getContext()));
                        chip = (Chip) chipBinding.getRoot();
                    }
                    else {
                        val chipBinding = ChipEntryAdminBinding.inflate(LayoutInflater.from(getContext()));
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

    private void loadChooseUserPrivileges(UserRole userRole) {
        binding.groupChooseUserPrivileges.removeAllViews();
        for (val privilege : userRole.getPrivileges()) {
//            if (privilege != null && !privilege.getKeyName().equals(MockUsers.PRIVILEGE_DEFAULT_BASIC_INFO_ID)) {
            val chipBinding = ChipActionAdminBinding.inflate(LayoutInflater.from(getContext()));
            val chip        = (Chip) chipBinding.getRoot();
            chip.setText(privilege.getName());
            chip.setTag(privilege);
            chip.setOnClickListener(__ -> selectedPrivilege(userRole, privilege));
            binding.groupChooseUserPrivileges.addView(chip);
//            }
        }
    }

    private void loadChooseUserRoles() {
        binding.groupChooseUserRoles.removeAllViews();
        for (val userRole : allRoles) {
            val chipBinding = ChipActionAdminBinding.inflate(LayoutInflater.from(getContext()));
            val chip        = (Chip) chipBinding.getRoot();
            chip.setText(userRole.getName());
            chip.setTag(userRole);
            chip.setOnClickListener(__ -> selectedRole(userRole));
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