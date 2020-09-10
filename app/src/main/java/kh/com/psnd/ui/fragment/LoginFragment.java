package kh.com.psnd.ui.fragment;

import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.services.cognitoidentityprovider.model.NotAuthorizedException;
import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;

import org.greenrobot.eventbus.EventBus;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.listener.MyTextWatcher;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentLoginBinding;
import kh.com.psnd.eventbus.SingUpSuccessEventBus;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.network.model.LoginProfile;
import kh.com.psnd.network.request.RequestUserProfile;
import kh.com.psnd.network.response.ResponseUserProfile;
import kh.com.psnd.network.task.TaskUserProfile;
import lombok.val;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    private DialogProgress progress = null;

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), false, null);
        binding.btnLogin.setOnClickListener(__ -> doLogin());
        binding.forgetPassword.setOnClickListener(__ -> Toast.makeText(getContext(), "Clicked on Forget Password", Toast.LENGTH_LONG).show());
        binding.username.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidateUsername();
            }
        });
        binding.password.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidatePassword();
            }
        });
        binding.username.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                binding.password.requestFocus();
                return true;
            }
            return false;
        });
        binding.password.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                doLogin();
                return true;
            }
            return false;
        });
        binding.signup.setOnClickListener(__ -> ActivityHelper.openSignUpActivity(getContext()));
    }

    private boolean isValidateUsername() {
        binding.msg.setText("");
        if (TextUtils.isEmpty(binding.username.getText())) {
            binding.textInputLayoutUsername.setError(getString(R.string.username_error));
            return false;
        }
        binding.textInputLayoutUsername.setError(null);
        return true;
    }

    private boolean isValidatePassword() {
        binding.msg.setText("");
        if (TextUtils.isEmpty(binding.password.getText())) {
            binding.textInputLayoutPassword.setError(getString(R.string.password_error));
            return false;
        }
        binding.textInputLayoutPassword.setError(null);
        return true;
    }

    private void doLogin() {
        if (!ApplicationUtil.isOnline()) {
            binding.msg.setText(core.lib.R.string.noInternetConnection);
            return;
        }
        if (isValidateUsername() && isValidatePassword()) {
            val username = binding.username.getText().toString();
            val password = binding.password.getText().toString();
            progress.show();

            Amplify.Auth.signIn(username, password, new Consumer<AuthSignInResult>() {
                @Override
                public void accept(@NonNull AuthSignInResult value) {
                    Log.i("result : " + value);
                    if (value.isSignInComplete()) {
                        val profile = new LoginProfile(username, password, null, null);
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
                    if (value.getCause() instanceof NotAuthorizedException) {
                        binding.msg.setText(R.string.incorrect_username_or_password);
                    }
                    else {
                        binding.msg.setText(value.toString());
                    }
                    progress.dismiss();
                }
            });
        }
    }

    private void fetchAuthSession(LoginProfile profile) {
        Amplify.Auth.fetchAuthSession(
                result -> {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    Log.i("result : " + result);
                    fetchStaff(profile);
                },
                error -> {
                    Log.e("AuthQuickStart " + error.toString());
                    progress.dismiss();
                }
        );
    }

    private void fetchStaff(LoginProfile profile) {
        try {
            // val staffId = AWSMobileClient.getInstance().getUserAttributes().get("custom:staff_id");
            val task = new TaskUserProfile(new RequestUserProfile(profile.getUsername()));
            getCompositeDisposable().add(task.start(task.new SimpleObserver() {

                @Override
                public Class<?> clazzResponse() {
                    return null;
                }

                @Override
                public void onNext(@io.reactivex.annotations.NonNull retrofit2.Response result) {
                    Log.i("LOG >> onNext >> result : " + result);
                    if (result.isSuccessful()) {
                        val data        = (ResponseUserProfile) result.body();
                        val userProfile = data.getResult();

                        try {
                            profile.setUserProfile(userProfile);
                            profile.setTokens(AWSMobileClient.getInstance().getTokens());
                            LoginManager.loggedIn(profile);
                            EventBus.getDefault().post(new SingUpSuccessEventBus());
                            ActivityHelper.openMainActivity(getContext());
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    progress.dismiss();
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(e);
                    progress.dismiss();
                }
            }));
        } catch (Exception e) {
            Log.e(e);
            progress.dismiss();
        }
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }


}