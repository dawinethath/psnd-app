package kh.com.psnd.ui.fragment.user;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSignupStep2Binding;
import kh.com.psnd.network.model.Staff;
import lombok.val;

public class SignUpStep2Fragment extends BaseFragment<FragmentSignupStep2Binding> {
    private DialogProgress progress = null;

    public static SignUpStep2Fragment newInstance() {
        val fragment = new SignUpStep2Fragment();
        val bundle   = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), true, dialogInterface -> getCompositeDisposable().clear());
    }

    public void updateUI(@NonNull Staff staff, @NonNull String username, @NonNull String password) {
        Log.i("username : " + username + "    password : " + password);
    }

    private boolean isValidatePhoneNumber() {
        binding.msg.setText("");
        if (TextUtils.isEmpty(binding.phoneNumber.getText())) {
            binding.textInputLayoutPhoneNumber.setError(getString(R.string.phone_number_error));
            return false;
        }
        binding.textInputLayoutPhoneNumber.setError(null);
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

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_signup_step_2;
    }

}