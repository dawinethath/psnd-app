package kh.com.psnd.ui.fragment;

import android.text.TextUtils;
import android.view.View;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.listener.MyTextWatcher;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.InternetUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSignupBinding;
import kh.com.psnd.network.request.RequestSignUpVerify;
import kh.com.psnd.network.response.ResponseSignUpVerify;
import kh.com.psnd.network.task.TaskSignUpVerify;
import lombok.val;
import retrofit2.Response;

public class SignupFragment extends BaseFragment<FragmentSignupBinding> {
    private DialogProgress progress = null;

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), true, dialogInterface -> getCompositeDisposable().clear());
        binding.btnVerify.setOnClickListener(__ -> doVerify());

        binding.firstName.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidateFirstName();
            }
        });
        binding.lastName.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidateLastName();
            }
        });
        binding.staffNumber.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidateStaffNumber();
            }
        });
        binding.phoneNumber.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidatePhoneNumber();
            }
        });
    }

    private void doVerify() {
        if (!ApplicationUtil.isOnline()) {
            InternetUtil.showNoInternet(binding.getRoot());
            return;
        }
        if (isValidateFirstName() && isValidateLastName() && isValidateStaffNumber() && isValidatePhoneNumber()) {
            progress.show();
            binding.msg.setVisibility(View.INVISIBLE);
            val staffNumber = binding.staffNumber.getText().toString().trim();
            val firstName   = binding.firstName.getText().toString().trim();
            val lastName    = binding.lastName.getText().toString().trim();
            val name        = firstName + " " + lastName;
            val phone       = binding.phoneNumber.getText().toString().trim();

            val request = new RequestSignUpVerify(staffNumber, name, phone);
            val task    = new TaskSignUpVerify(request);
            getCompositeDisposable().add(task.start(task.new SimpleObserver() {

                @Override
                public Class<?> clazzResponse() {
                    return null;
                }

                @Override
                public void onNext(Response result) {
                    Log.i("LOG >> onNext >> result : " + result);
                    if (result.isSuccessful()) {
                        val data = (ResponseSignUpVerify) result.body();
                        if (data.isVerified()) {
                            // todo open next screen (Verify phone number)
                        }
                        else {
                            binding.msg.setVisibility(View.VISIBLE);
                            binding.msg.setText(data.getMessage());
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
        }
    }

    private boolean isValidateFirstName() {
        if (TextUtils.isEmpty(binding.firstName.getText())) {
            binding.textInputLayoutFirstName.setError(getString(R.string.first_name_khmer_error));
            return false;
        }
        binding.textInputLayoutFirstName.setError(null);
        return true;
    }

    private boolean isValidateLastName() {
        if (TextUtils.isEmpty(binding.lastName.getText())) {
            binding.textInputLayoutLastName.setError(getString(R.string.last_name_khmer_error));
            return false;
        }
        binding.textInputLayoutLastName.setError(null);
        return true;
    }

    private boolean isValidateStaffNumber() {
        if (TextUtils.isEmpty(binding.staffNumber.getText())) {
            binding.textInputLayoutStaffNumber.setError(getString(R.string.staff_id_error));
            return false;
        }
        binding.textInputLayoutStaffNumber.setError(null);
        return true;
    }

    private boolean isValidatePhoneNumber() {
        if (TextUtils.isEmpty(binding.phoneNumber.getText())) {
            binding.textInputLayoutPhoneNumber.setError(getString(R.string.phone_number_error));
            return false;
        }
        binding.textInputLayoutPhoneNumber.setError(null);
        return true;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_signup;
    }

//    {
//        Amplify.Auth.fetchAuthSession(new Consumer<AuthSession>() {
//            @Override
//            public void accept(@NonNull AuthSession value) {
//                Log.i("Amplify : " + value);
//            }
//        }, new Consumer<AuthException>() {
//            @Override
//            public void accept(@NonNull AuthException value) {
//                Log.e("Amplify : " + value);
//            }
//        });
//    }

//        Amplify.Auth.confirmSignUp(
//                "tongheng",
//                "034372", new Consumer<AuthSignUpResult>() {
//                    @Override
//                    public void accept(@NonNull AuthSignUpResult value) {
//                        Log.i("Amplify : " + value);
//                    }
//                }, new Consumer<AuthException>() {
//                    @Override
//                    public void accept(@NonNull AuthException value) {
//                        Log.i("Amplify : " + value);
//                    }
//                });


//        {
//            val username = "tongheng";
//            val pwd      = "Tongheng123#";
//            val email    = "kongsonida168@gmail.com";
//            val phone    = "+85511210777";
//
//            val signupOption = AuthSignUpOptions.builder()
//                    .userAttribute(AuthUserAttributeKey.email(), email)
//                    .userAttribute(AuthUserAttributeKey.phoneNumber(), phone)
//                    .build();
//            Amplify.Auth.signUp(username, pwd, signupOption, new Consumer<AuthSignUpResult>() {
//                @Override
//                public void accept(@NonNull AuthSignUpResult value) {
//                    Log.i("Amplify : " + value);
//                }
//            }, new Consumer<AuthException>() {
//                @Override
//                public void accept(@NonNull AuthException value) {
//                    Log.i("Amplify : " + value);
//                }
//            });
//        }

//    {
//        val username = "tongheng";
//        val pwd      = "Tongheng123#";
//
//        val metadata = new HashMap<String, String>();
//        metadata.put("prompt", "login");
//        val options = AWSCognitoAuthSignInOptions.builder()
//                .metadata(metadata)
//                .build();
//
//        Amplify.Auth.signIn(username, pwd, options, new Consumer<AuthSignInResult>() {
//            @Override
//            public void accept(@NonNull AuthSignInResult value) {
//                Log.i("Amplify : " + value);
//            }
//        }, new Consumer<AuthException>() {
//            @Override
//            public void accept(@NonNull AuthException value) {
//                Log.e("Amplify : " + value);
//            }
//        });
//    }
}