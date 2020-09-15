package kh.com.psnd.ui.fragment.user;

import android.os.Bundle;

import androidx.annotation.NonNull;

import core.lib.dialog.BaseDialogFragment;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentUserInfoBinding;
import kh.com.psnd.network.model.UserProfile;
import lombok.val;

public class UserInfoFragment extends BaseDialogFragment<FragmentUserInfoBinding> {

    public static UserInfoFragment newInstance(@NonNull UserProfile userProfile) {
        val fragment = new UserInfoFragment();
        val bundle   = new Bundle();
        bundle.putSerializable(UserProfile.EXTRA, userProfile);
        fragment.setArguments(bundle);
        fragment.setEnableFullscreen(true);
        return fragment;
    }

    @Override
    public void setupUI() {
        binding.btnBack.setOnClickListener(__ -> dismiss());
        binding.btnEdit.setOnClickListener(__ -> {

        });
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_user_info;
    }

}