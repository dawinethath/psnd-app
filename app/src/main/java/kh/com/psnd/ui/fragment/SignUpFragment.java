package kh.com.psnd.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.services.cognitoidentityprovider.model.InvalidPasswordException;
import com.amazonaws.services.cognitoidentityprovider.model.UsernameExistsException;
import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.listener.MyTextWatcher;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSignupBinding;
import kh.com.psnd.eventbus.SingUpSuccess;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.network.model.SignUpStep1;
import kh.com.psnd.network.model.Staff;
import kh.com.psnd.network.model.UserProfile;
import kh.com.psnd.network.request.RequestQRCode;
import kh.com.psnd.network.response.ResponseLogin;
import kh.com.psnd.network.response.ResponseStaff;
import kh.com.psnd.network.task.TaskQRCode;
import lombok.val;
import retrofit2.Response;

public class SignUpFragment extends BaseFragment<FragmentSignupBinding> {
    private DialogProgress progress = null;
    private Staff          staff    = null;

    public static SignUpFragment newInstance(SignUpStep1 signUpStep1) {
        val fragment = new SignUpFragment();
        val bundle   = new Bundle();
        bundle.putSerializable(SignUpStep1.EXTRA, signUpStep1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), true, dialogInterface -> getCompositeDisposable().clear());
        binding.btnSignUp.setEnabledWithAlpha(false);

        IntentIntegrator.forSupportFragment(this)
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .setPrompt(getString(R.string.qrcode_signup))
                .initiateScan();

        binding.passwordView.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                binding.btnSignUp.setEnabledWithAlpha(binding.passwordView.isValidPassword());
                binding.msg.setText("");
            }
        });
        binding.btnSignUp.setOnClickListener(__ -> doSignUp());
    }

    public void updateUI(@NonNull Staff staff) {
        this.staff = staff;
        binding.staffName.setText(staff.getFullName());
        binding.office.setText(staff.getOffice());
        binding.department.setText(staff.getDepartment());
        binding.imageProfile.setImageURI(staff.getPhoto());
        binding.username.setText(staff.getIdEn());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (!TextUtils.isEmpty(result.getContents())) {
                loadDetailByQRCode(result.getContents());
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 1 : check username from server
     * 2 : check username from cognito server
     */
    private void doSignUp() {
        if (binding.passwordView.isValidPassword() && !TextUtils.isEmpty(binding.username.getText())) {
            if (!ApplicationUtil.isOnline()) {
                Toast.makeText(getContext(), R.string.noInternetConnection, Toast.LENGTH_LONG).show();
                return;
            }

            {
                binding.msg.setText("");
                progress.show();
                val username       = binding.username.getText().toString();
                val pwd            = binding.passwordView.getPassword();
                val userAttributes = new ArrayList<AuthUserAttribute>();
                userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.name(), staff.getFullName()));
                userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.picture(), staff.getPhoto()));
                userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.address(), staff.getStaffId() + ""));
                userAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.custom("custom:staff_id"), staff.getStaffId() + ""));

                val signUpOption = AuthSignUpOptions.builder()
                        .userAttributes(userAttributes)
                        .build();
                Amplify.Auth.signUp(username, pwd, signUpOption, new Consumer<AuthSignUpResult>() {
                    @Override
                    public void accept(@NonNull AuthSignUpResult value) {
                        Log.i("Amplify : " + value);
                        if (value.isSignUpComplete()) {
                            // todo save user profile, open main screen, fetch user's token
                            val profile = new UserProfile(username, pwd, staff, null);
                            doAutoSignIn(profile);
                        }
                        else {
                            progress.dismiss();
                        }
                    }
                }, new Consumer<AuthException>() {
                    @Override
                    public void accept(@NonNull AuthException value) {
                        Log.i("Amplify : " + value);
                        if (value.getCause() instanceof UsernameExistsException) {
                            binding.msg.setText(R.string.user_already_exists);
                        }
                        else if (value.getCause() instanceof InvalidPasswordException) {
                            binding.msg.setText(value.getCause().getMessage());
                        }
                        else {
                            binding.msg.setText(value.getMessage());
                        }
                        progress.dismiss();
                    }
                });
            }
        }
    }

    private void doAutoSignIn(UserProfile profile) {
        Amplify.Auth.signIn(profile.getUsername(), profile.getPwd(), new Consumer<AuthSignInResult>() {
            @Override
            public void accept(@NonNull AuthSignInResult value) {
                Log.i("result : " + value);
                if (value.isSignInComplete()) {
                    fetchAuthSession(profile);
                }
                else {
                    progress.dismiss();
                }
            }
        }, new Consumer<AuthException>() {
            @Override
            public void accept(@NonNull AuthException value) {
                Log.e("result : " + value);
            }
        });
    }

    private void fetchAuthSession(UserProfile profile) {
        Amplify.Auth.fetchAuthSession(
                result -> {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    Log.i("result : " + result);

                    try {
                        profile.setTokens(AWSMobileClient.getInstance().getTokens());
                        LoginManager.loggedIn(profile);
                        EventBus.getDefault().post(new SingUpSuccess());
                        ActivityHelper.openMainActivity(getContext());
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.e("AuthQuickStart " + error.toString());
                    progress.dismiss();
                }
        );
    }

    private void loadDetailByQRCode(String qrcode) {
        if (!ApplicationUtil.isOnline()) {
            Toast.makeText(getContext(), R.string.noInternetConnection, Toast.LENGTH_LONG).show();
            getBaseFragmentActivity().finish();
            return;
        }
        progress.show();
        val task = new TaskQRCode(new RequestQRCode(qrcode));
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            @Override
            public Class<?> clazzResponse() {
                return ResponseLogin.class;
            }

            @Override
            public void onNext(Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                progress.dismiss();
                if (result.isSuccessful()) {
                    val data  = (ResponseStaff) result.body();
                    val staff = data.getResult();
                    updateUI(staff);
                    Log.i(staff);
                }
                else {
                    getActivity().finish();
                    Toast.makeText(getContext(), R.string.msg_scan_wrong_qrcode, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
                progress.dismiss();
                finish();
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }));
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_signup;
    }

}