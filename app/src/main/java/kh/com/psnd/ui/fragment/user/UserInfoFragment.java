package kh.com.psnd.ui.fragment.user;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.joda.time.format.DateTimeFormat;

import core.lib.dialog.BaseDialogFragment;
import core.lib.dialog.DialogProgress;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentUserInfoBinding;
import kh.com.psnd.eventbus.UpdateAccountSuccessEventBus;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.network.model.UserProfile;
import kh.com.psnd.network.request.RequestUserDisable;
import kh.com.psnd.network.response.ResponseUserDisable;
import kh.com.psnd.network.task.TaskUserDisable;
import lombok.val;
import retrofit2.Response;

public class UserInfoFragment extends BaseDialogFragment<FragmentUserInfoBinding> {

    private DialogProgress progress;

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
        progress = new DialogProgress(getContext(), false, dialogInterface -> getCompositeDisposable().clear());
        updateUI();
    }

    private void updateUI() {
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

        {
            try {
                binding.status.setTextColor(Color.parseColor(userProfile.getActiveColor()));
            } catch (Throwable e) {
            }
            if (userProfile.isActive()) {
                binding.btnSuspend.setImageResource(R.drawable.ic_user_suspend);
                binding.status.setText(R.string.active);
            }
            else {
                binding.btnSuspend.setImageResource(R.drawable.ic_user_active);
                binding.status.setText(R.string.suspend);
            }
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
                            // todo change password
                            break;
                        case 1:
                            // todo change user's role
                            break;
                    }
                })
                .show();
    }

    private void clickedOnSuspend(UserProfile userProfile) {
        val icon    = userProfile.isActive() ? R.drawable.ic_user_suspend : R.drawable.ic_user_active;
        val title   = userProfile.isActive() ? R.string.suspend : R.string.active;
        val message = userProfile.isActive() ? R.string.msg_ask_suspend : R.string.msg_ask_active;

        new MaterialAlertDialogBuilder(getContext())
                .setIcon(icon)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> executeTaskUserDisable(userProfile)).show();
    }

    private void executeTaskUserDisable(UserProfile userProfile) {
        if (!ApplicationUtil.isOnline()) {
            Snackbar.make(getView(), R.string.noInternetConnection, Snackbar.LENGTH_SHORT).show();
            return;
        }
        val request = new RequestUserDisable(userProfile.getId(), !userProfile.isActive());
        val task    = new TaskUserDisable(request);
        progress.show();
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@NotNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseUserDisable) result.body();
                    if (data != null) {
                        // set active or suspend then update UI
                        userProfile.setActive(data.getResult().isActive());
                        userProfile.setActiveColor(data.getResult().getActiveColor());

                        val bundle = new Bundle();
                        bundle.putSerializable(UserProfile.EXTRA, userProfile);
                        setArguments(bundle);
                        updateUI();
                        EventBus.getDefault().postSticky(new UpdateAccountSuccessEventBus(userProfile));
                    }
                }
                progress.hide();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
                progress.hide();
            }
        }));
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_user_info;
    }

}