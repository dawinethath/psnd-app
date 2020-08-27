package kh.com.psnd.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.amazonaws.services.cognitoidentityprovider.model.LimitExceededException;
import com.amazonaws.services.cognitoidentityprovider.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidentityprovider.model.TooManyRequestsException;
import com.amplifyframework.core.Amplify;

import core.lib.dialog.BaseDialogFragment;
import core.lib.dialog.DialogProgress;
import core.lib.listener.MyTextWatcher;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.InternetUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentChangePasswordBinding;
import lombok.val;

public class StaffChangePasswordFragment extends BaseDialogFragment<FragmentChangePasswordBinding> {

    public static StaffChangePasswordFragment newInstance() {
        val fragment = new StaffChangePasswordFragment();
        val bundle   = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    private DialogProgress progress = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPercentWidthDialog(0.98f);
    }

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), true, dialogInterface -> getCompositeDisposable().clear());
        progress.setCanceledOnTouchOutside(false);

        binding.btnCancel.setOnClickListener(__ -> dismiss());
        binding.btnChange.setOnClickListener(__ -> doSubmit());
        binding.currentPassword.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidateCurrentPassword();
            }
        });
        binding.newPassword.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidateNewPassword();
            }
        });
        binding.confirmPassword.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                isValidateConfirmPassword();
            }
        });
    }


    private boolean isValidateCurrentPassword() {
        binding.msg.setText("");
        if (TextUtils.isEmpty(binding.currentPassword.getText())) {
            binding.textInputLayoutCurrentPassword.setError(getContext().getString(R.string.required));
            return false;
        }
        binding.textInputLayoutCurrentPassword.setError(null);
        return true;
    }

    private boolean isValidateNewPassword() {
        binding.msg.setText("");
        if (TextUtils.isEmpty(binding.newPassword.getText())) {
            binding.textInputLayoutNewPassword.setError(getContext().getString(R.string.required));
            return false;
        }
        binding.textInputLayoutNewPassword.setError(null);
        return true;
    }

    private boolean isValidateConfirmPassword() {
        binding.msg.setText("");
        if (TextUtils.isEmpty(binding.confirmPassword.getText())) {
            binding.textInputLayoutConfirmPassword.setError(getContext().getString(R.string.required));
            return false;
        }
        if (!binding.newPassword.getText().toString().equals(binding.confirmPassword.getText().toString())) {
            binding.textInputLayoutConfirmPassword.setError(getContext().getString(R.string.not_match));
            return false;
        }
        binding.textInputLayoutConfirmPassword.setError(null);
        return true;
    }

    private boolean checkValidForm() {
        return isValidateCurrentPassword() && isValidateNewPassword() && isValidateConfirmPassword();
    }

    private void doSubmit() {
        if (!ApplicationUtil.isOnline()) {
            InternetUtil.showNoInternet(binding.getRoot());
            return;
        }
        if (checkValidForm()) {
            Log.i("call api cognito to change password");
            progress.show();
            val oldPassword = binding.currentPassword.getText().toString();
            val newPassword = binding.newPassword.getText().toString();
            Log.i("Old password : " + oldPassword + "     New password : " + newPassword);
            Amplify.Auth.updatePassword(oldPassword, newPassword,
                    () -> {
                        Log.i("AuthQuickstart + Updated password successfully");
                        getActivity().runOnUiThread(() -> {
                            Toast.makeText(getContext(), R.string.success_changed_password, Toast.LENGTH_LONG).show();
                            progress.dismiss();
                            dismiss();
                        });
                    },
                    error -> {
                        Log.e("AuthQuickstart " + error.toString());
                        getActivity().runOnUiThread(() -> {
                            val cause = error.getCause();
                            if (cause instanceof NotAuthorizedException) {
                                binding.msg.setText(R.string.failed_changed_password);
                            }
                            else if (cause instanceof LimitExceededException ||
                                    cause instanceof TooManyRequestsException ||
                                    ((!TextUtils.isEmpty(error.getMessage())) || "Failed to change password".equals(error.getMessage()))) {
                                binding.msg.setText(R.string.failed_limit_changed_password);
                            }
                            else {
                                binding.msg.setText(error.toString());
                            }
                            binding.msg.invalidate();
                            progress.dismiss();
                        });
                    }

            );

        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_change_password;
    }

}