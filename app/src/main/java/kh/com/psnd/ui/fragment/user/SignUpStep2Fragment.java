package kh.com.psnd.ui.fragment.user;

import android.os.Bundle;
import android.text.TextUtils;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSignupStep2Binding;
import kh.com.psnd.network.model.SignUpStep1;
import lombok.val;

public class SignUpStep2Fragment extends BaseFragment<FragmentSignupStep2Binding> {
    private DialogProgress progress = null;

    public static SignUpStep2Fragment newInstance(SignUpStep1 signUpStep1) {
        val fragment = new SignUpStep2Fragment();
        val bundle   = new Bundle();
        bundle.putSerializable(SignUpStep1.EXTRA, signUpStep1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), true, dialogInterface -> getCompositeDisposable().clear());
        val signUpStep1 = (SignUpStep1) getArguments().getSerializable(SignUpStep1.EXTRA);
        binding.staffName.setText(signUpStep1.getFullName());
        binding.staffNumber.setText(signUpStep1.getId());
        binding.imageProfile.setImageURI(signUpStep1.getPhoto());
    }

//    private void doVerify() {
//        if (!ApplicationUtil.isOnline()) {
//            InternetUtil.showNoInternet(binding.getRoot());
//            return;
//        }
//        if (isValidateStaffNumber() && isValidatePhoneNumber()) {
//            progress.show();
//            binding.msg.setVisibility(View.INVISIBLE);
//            val staffNumber = binding.staffNumber.getText().toString().trim();
//            val lastName    = binding.lastName.getText().toString().trim();
//            val phone       = binding.phoneNumber.getText().toString().trim();
//
//            val request = new RequestSignUpVerify(staffNumber, name, phone);
//            val task    = new TaskSignUpVerify(request);
//            getCompositeDisposable().add(task.start(task.new SimpleObserver() {
//
//                @Override
//                public Class<?> clazzResponse() {
//                    return null;
//                }
//
//                @Override
//                public void onNext(Response result) {
//                    Log.i("LOG >> onNext >> result : " + result);
//                    if (result.isSuccessful()) {
//                        val data = (ResponseSignUpVerify) result.body();
//                        if (data.isVerified()) {
//                            // todo open next screen (Verify phone number)
//                        }
//                        else {
//                            binding.msg.setVisibility(View.VISIBLE);
//                            binding.msg.setText(data.getMessage());
//                        }
//                    }
//                    progress.dismiss();
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    Log.e(e);
//                    progress.dismiss();
//                }
//            }));
//        }
//    }

    private boolean isValidateStaffNumber() {
        if (TextUtils.isEmpty(binding.staffNumber.getText())) {
            binding.textInputLayoutStaffNumber.setError(getString(R.string.staff_id_error));
            return false;
        }
        binding.textInputLayoutStaffNumber.setError(null);
        return true;
    }

    private boolean isValidatePhoneNumber() {
//        if (TextUtils.isEmpty(binding.phoneNumber.getText())) {
//            binding.textInputLayoutPhoneNumber.setError(getString(R.string.phone_number_error));
//            return false;
//        }
//        binding.textInputLayoutPhoneNumber.setError(null);
        return true;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_signup_step_2;
    }

}