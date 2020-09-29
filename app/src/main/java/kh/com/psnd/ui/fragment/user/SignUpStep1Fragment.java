package kh.com.psnd.ui.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.listener.MyTextWatcher;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSignupStep1Binding;
import kh.com.psnd.network.model.Staff;
import kh.com.psnd.network.request.RequestQRCode;
import kh.com.psnd.network.request.RequestUserCheck;
import kh.com.psnd.network.response.ResponseLogin;
import kh.com.psnd.network.response.ResponseStaff;
import kh.com.psnd.network.response.ResponseUserCheck;
import kh.com.psnd.network.task.TaskQRCode;
import kh.com.psnd.network.task.TaskUserCheck;
import kh.com.psnd.ui.activity.SignUpActivity;
import kh.com.psnd.utils.PasswordGeneratorUtil;
import lombok.val;
import retrofit2.Response;

public class SignUpStep1Fragment extends BaseFragment<FragmentSignupStep1Binding> {
    private DialogProgress progress = null;
    private Staff          staff    = null;

    public static SignUpStep1Fragment newInstance() {
        val fragment = new SignUpStep1Fragment();
        val bundle   = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), true, dialogInterface -> getCompositeDisposable().clear());
        binding.btnQRCode.setOnClickListener(__ -> scanQRCode());
        binding.btnNext.setOnClickListener(__ -> actionNext());
        binding.username.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                checkUser();
            }
        });
        binding.password.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidPassword();
            }
        });
        binding.confirmPassword.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidConfirmPassword();
            }
        });
        scanQRCode();
    }

    private void scanQRCode() {
        IntentIntegrator.forSupportFragment(this)
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .setPrompt(getString(R.string.qrcode_signup))
                .initiateScan();
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
        Log.i(result);
        if (result != null) {
            if (!TextUtils.isEmpty(result.getContents())) {
                loadDetailByQRCode(result.getContents());
                return;
            }
        }
        finish();
        // super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean isValidateUsername() {
        if (TextUtils.isEmpty(binding.username.getText())) {
            binding.textInputLayoutUserName.setError(getString(R.string.username_error));
            return false;
        }
        binding.textInputLayoutUserName.setError(null);
        return true;
    }

    public boolean isValidPassword() {
        // field password
        val pwd = binding.password.getText().toString();
        Log.i("pwd.length() -> length : " + pwd.length());
        switch (pwd.length()) {
            case PasswordGeneratorUtil.PWD_LENGTH:
                binding.textInputLayoutPassword.setError(null);
                return true;
            case 0:
                binding.textInputLayoutPassword.setError(getContext().getString(R.string.password_error));
                return false;
            default:
                binding.textInputLayoutPassword.setError(getContext().getString(R.string.password_error_incorrect));
                return false;
        }
    }

    public boolean isValidConfirmPassword() {
        // field password
        val pwd = binding.confirmPassword.getText().toString();
        Log.i("pwd.length() -> length : " + pwd.length());
        switch (pwd.length()) {
            case PasswordGeneratorUtil.PWD_LENGTH:
                binding.textInputLayoutConfirmPassword.setError(null);
                return true;
            case 0:
                binding.textInputLayoutConfirmPassword.setError(getContext().getString(R.string.password_error));
                return false;
            default:
                binding.textInputLayoutConfirmPassword.setError(getContext().getString(R.string.password_error_incorrect));
                return false;
        }
    }

    public boolean isMatchPassword() {
        if (!isValidPassword()) {
            return false;
        }
        if (!isValidConfirmPassword()) {
            return false;
        }
        val password        = binding.password.getText().toString();
        val confirmPassword = binding.confirmPassword.getText().toString();
        if (!password.equals(confirmPassword)) {
            binding.textInputLayoutConfirmPassword.setError(getContext().getString(R.string.password_not_match));
            return false;
        }
        return true;
    }

    private boolean isFormValid() {
        return isValidateUsername() && isMatchPassword();
    }

    /**
     * 1 : check username from server
     * 2 : check username from cognito server
     */
    private void actionNext() {
        if (isFormValid()) {
            val username = binding.username.getText().toString();
            val password = binding.password.getText().toString();
            ((SignUpActivity) getActivity()).goToSecondScreen(staff, username, password);
        }
    }

    private void checkUser() {
        getCompositeDisposable().clear();
        val username = binding.username.getText().toString();
        if (!TextUtils.isEmpty(username)) {
            val task = new TaskUserCheck(new RequestUserCheck(username));
            getCompositeDisposable().add(task.start(task.new SimpleObserver() {

                @Override
                public Class<?> clazzResponse() {
                    return null;
                }

                @Override
                public void onNext(@io.reactivex.annotations.NonNull Response result) {
                    Log.i("LOG >> onNext >> result : " + result);
                    if (result.isSuccessful()) {
                        val data = (ResponseUserCheck) result.body();
                        if (data.isFound()) {
                            binding.textInputLayoutUserName.setError(getString(R.string.user_already_exists));
                            showGroupViews(View.GONE);
                        }
                        else {
                            binding.textInputLayoutUserName.setError("");
                            showGroupViews(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(e);
                    binding.textInputLayoutUserName.setError("");
                    showGroupViews(View.GONE);
                    progress.dismiss();
                }
            }));
        }
        else {
            binding.textInputLayoutUserName.setError("");
        }
    }

    private void showGroupViews(int visibility) {
        binding.textInputLayoutPassword.setVisibility(visibility);
        binding.textInputLayoutConfirmPassword.setVisibility(visibility);
        binding.btnNext.setVisibility(visibility);
    }

    private void loadDetailByQRCode(String qrcode) {
        if (!ApplicationUtil.isOnline()) {
            Toast.makeText(getContext(), R.string.noInternetConnection, Toast.LENGTH_LONG).show();
            getBaseFragmentActivity().finish();
            return;
        }
        progress.show();
        val task = new TaskQRCode(new RequestQRCode(qrcode), this);
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
                    Log.i(staff);
                    if (staff != null) {
                        updateUI(staff);
                        return;
                    }
                }
                getActivity().finish();
                Toast.makeText(getContext(), R.string.msg_scan_wrong_qrcode, Toast.LENGTH_LONG).show();
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
        return R.layout.fragment_signup_step_1;
    }

}