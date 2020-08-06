package kh.com.psnd.ui.fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.listener.MyTextWatcher;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.InternetUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentLoginBinding;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.network.model.UserProfile;
import kh.com.psnd.network.request.RequestLogin;
import kh.com.psnd.network.response.ResponseLogin;
import kh.com.psnd.network.task.TaskLogin;
import lombok.val;
import retrofit2.Response;

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
        binding.signup.setOnClickListener(__ -> ActivityHelper.openSignupActivity(getContext()));
    }

    private boolean isValidateUsername() {
        if (TextUtils.isEmpty(binding.username.getText())) {
            binding.textInputLayoutUsername.setError(getString(R.string.username_error));
            return false;
        }
        binding.textInputLayoutUsername.setError(null);
        return true;
    }

    private boolean isValidatePassword() {
        if (TextUtils.isEmpty(binding.password.getText())) {
            binding.textInputLayoutPassword.setError(getString(R.string.password_error));
            return false;
        }
        binding.textInputLayoutPassword.setError(null);
        return true;
    }

    private void doLogin() {
        if (!ApplicationUtil.isOnline()) {
            InternetUtil.showNoInternet(binding.getRoot());
            return;
        }
        if (isValidateUsername() && isValidatePassword()) {
            val profile = new UserProfile();
            profile.setUsername("KONG SONIDA");

            val username = binding.username.getText().toString();
            val password = binding.password.getText().toString();
            progress.show();
            val task = new TaskLogin(new RequestLogin(username, password));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getCompositeDisposable().add(task.start(task.new SimpleObserver() {

                        @Override
                        public Class<?> clazzResponse() {
                            return ResponseLogin.class;
                        }

                        @Override
                        public void onNext(Response result) {
                            Log.i("LOG >> onNext >> result : " + result);
                            // if (result.isSuccessful()) {
                            LoginManager.loggedIn(profile);
                            ActivityHelper.openMainActivity(getContext());
                            getActivity().finish();
                            progress.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(e);
                            progress.dismiss();
                            LoginManager.loggedIn(profile);
                            ActivityHelper.openMainActivity(getContext());
                            getActivity().finish();
                        }
                    }));
                }
            }, 1000);
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