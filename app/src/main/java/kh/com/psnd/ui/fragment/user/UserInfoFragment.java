package kh.com.psnd.ui.fragment.user;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.joda.time.format.DateTimeFormat;

import core.lib.dialog.BaseDialogFragment;
import core.lib.dialog.DialogProgress;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import io.reactivex.disposables.CompositeDisposable;
import kh.com.psnd.R;
import kh.com.psnd.base.App;
import kh.com.psnd.databinding.FragmentUserInfoBinding;
import kh.com.psnd.eventbus.UpdateAccountSuccessEventBus;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.network.model.UserProfile;
import kh.com.psnd.network.model.UserRole;
import kh.com.psnd.network.model.UserRolePrivilege;
import kh.com.psnd.network.request.RequestUserDisable;
import kh.com.psnd.network.request.RequestUserUpdateRole;
import kh.com.psnd.network.response.ResponseUserDisable;
import kh.com.psnd.network.response.ResponseUserRolePrivilege;
import kh.com.psnd.network.response.ResponseUserUpdateRole;
import kh.com.psnd.network.task.TaskUserDisable;
import kh.com.psnd.network.task.TaskUserRolePrivilege;
import kh.com.psnd.network.task.TaskUserUpdateRole;
import lombok.val;
import retrofit2.Response;

public class UserInfoFragment extends BaseDialogFragment<FragmentUserInfoBinding> {

    private DialogProgress    progress;
    private UserRolePrivilege rolePrivilege;

    public static UserInfoFragment newInstance(@NonNull UserProfile userProfile, @Nullable UserRolePrivilege rolePrivilege) {
        val fragment = new UserInfoFragment();
        val bundle   = new Bundle();
        bundle.putSerializable(UserProfile.EXTRA, userProfile);
        if (rolePrivilege != null) {
            bundle.putSerializable(UserRolePrivilege.EXTRA, rolePrivilege);
        }
        fragment.setArguments(bundle);
        fragment.setEnableFullscreen(true);
        return fragment;
    }

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), false, dialogInterface -> getCompositeDisposable().clear());
        rolePrivilege = (UserRolePrivilege) getArguments().getSerializable(UserRolePrivilege.EXTRA);
        updateUI();
    }

    private void updateUI() {
        val userProfile = (UserProfile) getArguments().getSerializable(UserProfile.EXTRA);
        binding.btnBack.setOnClickListener(__ -> dismiss());
        binding.btnEdit.setOnClickListener(v -> clickedOnEdit(v, userProfile));
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

    private void clickedOnEdit(@NonNull View view, @NonNull UserProfile userProfile) {
        PopupMenu    popup    = new PopupMenu(getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_user_edit, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.change_password:
                    // todo change password
                    break;
                case R.id.change_user_role:
                    if (rolePrivilege == null) {
                        loadRolePrivilege(userProfile);
                    }
                    else {
                        showForm_ChangeUserRightFragment(userProfile, rolePrivilege);
                    }
                    break;
            }
            return false;
        });
        popup.show();
    }

    private void showForm_ChangeUserRightFragment(@NonNull UserProfile userProfile, @NonNull UserRolePrivilege rolePrivilege) {
        val userRole = userProfile.getRole().clone();
        userRole.setPrivileges(userProfile.getPrivileges());
        val fragment = SelectUserRightFragment.newInstance(rolePrivilege, userRole);
        fragment.setCallback(currentUserRole -> updateUserRole(userProfile, currentUserRole));
        fragment.show(getActivity().getSupportFragmentManager(), "");
    }

    private void loadRolePrivilege(@NonNull UserProfile userProfile) {
        progress.show();
        val task = new TaskUserRolePrivilege();
        new CompositeDisposable().add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                progress.dismiss();
                if (result.isSuccessful()) {
                    val data = (ResponseUserRolePrivilege) result.body();
                    if (data != null) {
                        showForm_ChangeUserRightFragment(userProfile, data.getResult());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
                progress.dismiss();
            }
        }));
    }

    private void updateUserRole(@NonNull UserProfile userProfile, @NotNull UserRole newUserRole) {
        Log.i(newUserRole);
        String jsonOldRole       = App.getGson().toJson(userProfile.getRole());
        String jsonOldPrivileges = App.getGson().toJson(userProfile.getPrivileges());

        String jsonNewRole       = App.getGson().toJson(new UserRole(newUserRole.getKeyName(), newUserRole.getName(), null));
        String jsonNewPrivileges = App.getGson().toJson(newUserRole.getPrivileges());

        // check if user's role or privileges changed, then we call api
        if (jsonOldRole.length() != jsonNewRole.length() || jsonOldPrivileges.length() != jsonNewPrivileges.length()) {
            binding.progressBar.setVisibility(View.VISIBLE);
            val request = new RequestUserUpdateRole(userProfile.getId(), newUserRole.getKeyName(), newUserRole.getPrivileges());
            val task    = new TaskUserUpdateRole(request);
            new CompositeDisposable().add(task.start(task.new SimpleObserver() {

                @Override
                public Class<?> clazzResponse() {
                    return null;
                }

                @Override
                public void onNext(Response result) {
                    Log.i("LOG >> onNext >> result : " + result);
                    if (result.isSuccessful()) {
                        val data = (ResponseUserUpdateRole) result.body();
                        if (data.isSuccess()) {
                            userProfile.setPrivileges(newUserRole.getPrivileges());
                            userProfile.setRole(newUserRole.clone());
                            userProfile.getRole().setPrivileges(null);
                            updateBroadcast(userProfile);
                            Snackbar.make(getView(), R.string.successful, Snackbar.LENGTH_SHORT).show();
                        }
                        else {
                            Snackbar.make(getView(), data.getMessage(), Snackbar.LENGTH_SHORT).show();
                        }
                    }
                    binding.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(e);
                    binding.progressBar.setVisibility(View.GONE);
                }
            }));
        }
        else {
            Log.i("User role no changes");
        }
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
                        updateBroadcast(userProfile);
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

    private void updateBroadcast(UserProfile userProfile) {
        val bundle = new Bundle();
        bundle.putSerializable(UserProfile.EXTRA, userProfile);
        setArguments(bundle);
        updateUI();
        EventBus.getDefault().postSticky(new UpdateAccountSuccessEventBus(userProfile));
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_user_info;
    }

}