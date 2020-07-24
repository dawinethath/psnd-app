package kh.com.psnd.ui.fragment;

import android.text.TextUtils;

import core.lib.base.BaseBottomSheetDialogFragment;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import core.lib.utils.PasswordGenerator;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentAddUserBinding;
import kh.com.psnd.mock.MockUsers;
import lombok.val;

public class AddUserFragment extends BaseBottomSheetDialogFragment<FragmentAddUserBinding> {

    @Override
    public void setupUI() {
        binding.btnGenerate.setOnClickListener(__ -> {
            val pwd = getPasswordGenerator().generate(6);
            binding.password.setText(pwd);
        });
        binding.textInputLayoutPassword.setEndIconOnClickListener(__ -> {
            val pwd = binding.password.getText().toString();
            if (!TextUtils.isEmpty(pwd)) {
                ApplicationUtil.copyPassword(pwd);
            }
        });
        binding.userRight.setupUI(this, MockUsers.userRole_user);
        loadingUserRight();

        binding.fullName.setText("Hello");
        binding.textInputLayoutFullName.setOnClickListener(__ -> {
            Log.i("textInputLayoutFullName");
        });
        binding.textInputLayoutStaffNumber.setOnClickListener(__ -> {
            Log.i("textInputLayoutStaffNumber");
        });
        binding.cardImageProfile.setOnClickListener(__ -> {
            Log.i("cardImageProfile");
        });
    }

    private void loadingUserRight() {

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

    private PasswordGenerator getPasswordGenerator() {
        return new PasswordGenerator.Builder()
                .useLower(true)
                .useUpper(true)
                .useDigits(true)
                .usePunctuation(false)
                .build();
    }

//    private PasswordGenerator getPasswordGenerator() {
//        return new PasswordGenerator.Builder()
//                .useLower(lowerLettersSwitch.isChecked())
//                .useUpper(upperLettersSwitch.isChecked())
//                .useDigits(digitsSwitch.isChecked())
//                .usePunctuation(symbolsSwitch.isChecked())
//                .build();
//    }

}