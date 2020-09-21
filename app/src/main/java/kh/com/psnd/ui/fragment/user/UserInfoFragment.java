package kh.com.psnd.ui.fragment.user;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.joda.time.format.DateTimeFormat;

import core.lib.dialog.BaseDialogFragment;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentUserInfoBinding;
import kh.com.psnd.helper.ActivityHelper;
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
        val userProfile = (UserProfile) getArguments().getSerializable(UserProfile.EXTRA);
        binding.btnBack.setOnClickListener(__ -> dismiss());
        binding.btnEdit.setOnClickListener(__ -> clickedOnEdit(userProfile));
        binding.btnSuspend.setOnClickListener(__ -> clickedOnSuspend(userProfile));
        binding.btnDetail.setOnClickListener(__ -> ActivityHelper.openDetailActivity(getContext(), userProfile.getStaff()));
        binding.staffName.setVisibility(View.GONE);
        binding.btnDetail.setVisibility(View.GONE);
        if (userProfile.getStaff() != null) {
            binding.staffName.setVisibility(View.VISIBLE);
            binding.btnDetail.setVisibility(View.VISIBLE);
            binding.staffName.setText(userProfile.getStaff().getFullName());
            binding.image.setImageURI(userProfile.getStaff().getPhoto());
        }
        if (userProfile.isActive()) {
            binding.status.setText(R.string.active);
            binding.status.setTextColor(getResources().getColor(R.color.greenLight));
        }
        else {
            binding.status.setText(R.string.suspend);
            binding.status.setTextColor(getResources().getColor(R.color.redLight));
        }
        binding.username.setText(userProfile.getUsername());
        binding.userRight.setupUI(this, userProfile);

        val formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
        val dateTime  = formatter.parseDateTime(userProfile.getCreatedAt());
        val createAt  = dateTime.toString("dd MMM yyyy");
        binding.signupSince.setText(getString(R.string.date_created, createAt));
    }

    private void clickedOnEdit(UserProfile userProfile) {
        new MaterialAlertDialogBuilder(getContext())
                .setItems(R.array.userEdit, (dialog, which) -> {
                    switch (which) {
                        case 0:

                            break;
                        case 1:

                            break;
                    }
                })
                .show();
    }

    private void clickedOnSuspend(UserProfile userProfile) {
        new MaterialAlertDialogBuilder(getContext())
                .setIcon(R.drawable.ic_outline_exit_to_app_24)
                .setTitle(R.string.logout)
                .setMessage(R.string.msg_ask_logout)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.logout, (dialogInterface, i) -> {

                }).show();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_user_info;
    }

}