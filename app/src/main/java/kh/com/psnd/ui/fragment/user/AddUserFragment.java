package kh.com.psnd.ui.fragment.user;

import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.google.android.material.snackbar.Snackbar;

import core.lib.base.BaseBottomSheetDialogFragment;
import core.lib.dialog.DialogProgress;
import core.lib.listener.MyTextWatcher;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentAddUserBinding;
import kh.com.psnd.helper.CognitoHelper;
import kh.com.psnd.network.model.SearchStaff;
import kh.com.psnd.network.model.UserProfile;
import kh.com.psnd.network.model.UserRole;
import kh.com.psnd.network.model.UserRolePrivilege;
import kh.com.psnd.network.request.RequestUserAdd;
import kh.com.psnd.network.response.ResponseUserAdd;
import kh.com.psnd.network.task.TaskUserAdd;
import lombok.val;
import retrofit2.Response;

public class AddUserFragment extends BaseBottomSheetDialogFragment<FragmentAddUserBinding> {

    private SearchAddUserFragment searchAddUserFragment = null;
    private DialogProgress        progress              = null;
    private SearchStaff           mSearchStaff          = null;

    public static AddUserFragment newInstance(@NonNull UserRolePrivilege userRolePrivilege) {
        val fragment = new AddUserFragment();
        val bundle   = new Bundle();
        bundle.putSerializable(UserRolePrivilege.EXTRA, userRolePrivilege);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), false, dialogInterface -> getCompositeDisposable().clear());
        searchAddUserFragment = new SearchAddUserFragment(getContext(), this::updateUI, getCompositeDisposable());
        searchAddUserFragment.setPercentWidthDialog(0.99f);

        val userRolePrivilege = (UserRolePrivilege) getArguments().getSerializable(UserRolePrivilege.EXTRA);

        binding.userRight.setupUI(this, userRolePrivilege, userRolePrivilege.getDefaultRole());
        loadingUserRight();

        binding.fakeInputLayoutFullName.setOnClickListener(__ -> searchAddUserFragment.show());
        binding.cardImageProfile.setOnClickListener(__ -> searchAddUserFragment.show());
        binding.btnSubmit.setOnClickListener(__ -> onClickSubmit());
        binding.username.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }
        });
    }

    private boolean isValidateUsername() {
        if (TextUtils.isEmpty(binding.username.getText())) {
            binding.textInputLayoutUserName.setError(getString(R.string.username_error));
            return false;
        }
        binding.textInputLayoutUserName.setError(null);
        return true;
    }

    private void onClickSubmit() {
        if (!ApplicationUtil.isOnline()) {
            Snackbar.make(getView(), R.string.noInternetConnection, Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (isValidateUsername() && binding.passwordView.isValidPassword()) {
            progress.show();
            // Call our api to register, then register with cognito
            val staffId        = mSearchStaff == null ? 0 : mSearchStaff.getStaffId();
            val username       = binding.username.getText().toString();
            val role           = (UserRole) binding.userRight.getTag();
            val requestUserAdd = new RequestUserAdd(staffId, username, role.getKeyName(), role.getPrivileges());
            val task           = new TaskUserAdd(requestUserAdd);
            getCompositeDisposable().add(task.start(task.new SimpleObserver() {

                @Override
                public Class<?> clazzResponse() {
                    return null;
                }

                @Override
                public void onNext(@io.reactivex.annotations.NonNull Response result) {
                    Log.i("LOG >> onNext >> result : " + result);
                    if (result.isSuccessful()) {
                        val data = (ResponseUserAdd) result.body();
                        if (!data.isAccessDenied()) {
                            val newUserProfile = data.getResult();
                            if (newUserProfile != null) {
                                signUpWitCognito(newUserProfile);
                                return;
                            }
                        }
                        Snackbar.make(binding.getRoot(), data.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                    progress.dismiss();
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(e);
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                    progress.dismiss();
                }
            }));
        }
    }

    private void signUpWitCognito(UserProfile userProfile) {
        val username     = binding.username.getText().toString();
        val pwd          = binding.passwordView.getPassword();
        val signUpOption = CognitoHelper.getAuthSignUpOptions(userProfile.getStaff());
        Amplify.Auth.signUp(username, pwd, signUpOption, new Consumer<AuthSignUpResult>() {
            @Override
            public void accept(@NonNull AuthSignUpResult value) {
                Log.i("Amplify : " + value);
                progress.dismiss();
                if (value.isSignUpComplete()) {
                    Looper.prepare();
                    Toast.makeText(getContext(), R.string.msg_created_user_success, Toast.LENGTH_LONG).show();
                    dismiss();
                    // todo need to paste object profile to list search
                }
            }
        }, new Consumer<AuthException>() {
            @Override
            public void accept(@NonNull AuthException value) {
                Log.i("Amplify : " + value);
                Looper.prepare();
                Toast.makeText(getContext(), value.getCause().getMessage(), Toast.LENGTH_LONG).show();
                progress.dismiss();
            }
        });
    }

    private void updateUI(SearchStaff searchStaff) {
        mSearchStaff = searchStaff;
        binding.fullName.setText(searchStaff.getFullName());
        binding.staffNumber.setText(searchStaff.getStaffNumber());
        binding.imageProfile.setImageURI(searchStaff.getPhoto());
        binding.username.setTextWithEndCursor(searchStaff.getStaffNumberEn());

        binding.username.requestFocus();
//        binding.username.postDelayed(() -> ApplicationUtil.showKeyboard(getContext(), binding.username), 600);
    }

    private void loadingUserRight() {

    }

    @Override
    public void onDestroyView() {
        searchAddUserFragment.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume");
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_add_user;
    }

}