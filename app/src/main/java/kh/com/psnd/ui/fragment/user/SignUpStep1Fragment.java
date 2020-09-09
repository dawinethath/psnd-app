package kh.com.psnd.ui.fragment.user;

import android.text.TextUtils;
import android.view.View;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.listener.MyTextWatcher;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.InternetUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSignupStep1Binding;
import kh.com.psnd.network.model.SignUpStep1;
import kh.com.psnd.network.request.RequestSignUpVerify;
import kh.com.psnd.network.response.ResponseSignUpVerify;
import kh.com.psnd.network.task.TaskSignUpVerify;
import lombok.val;
import retrofit2.Response;

public class SignUpStep1Fragment extends BaseFragment<FragmentSignupStep1Binding> {
    private DialogProgress progress = null;

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), true, dialogInterface -> getCompositeDisposable().clear());
        binding.next.setOnClickListener(__ -> doVerify());

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

        // todo Mock up
        {
            binding.firstName.setText("អាង");
            binding.lastName.setText("ធី");
            binding.staffNumber.setText("41527");
            binding.phoneNumber.setText("0978718137");
        }
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
                            val fragment = SignUpStep2Fragment.newInstance(new SignUpStep1(staffNumber, name, null, phone));
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .add(R.id.contentFragment, fragment, "SignUpStep2Fragment")
                                    .addToBackStack("SignUpStep2Fragment")
                                    .commit();
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
        return R.layout.fragment_signup_step_1;
    }

}