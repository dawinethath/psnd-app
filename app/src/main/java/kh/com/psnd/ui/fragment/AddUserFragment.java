package kh.com.psnd.ui.fragment;

import android.text.TextUtils;

import core.lib.base.BaseBottomSheetDialogFragment;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import core.lib.utils.PasswordGenerator;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentAddUserBinding;
import kh.com.psnd.mock.MockUsers;
import kh.com.psnd.network.model.Search;
import lombok.val;

public class AddUserFragment extends BaseBottomSheetDialogFragment<FragmentAddUserBinding> {
    private SearchAddUserFragment searchAddUserFragment = null;

    @Override
    public void setupUI() {
        searchAddUserFragment = new SearchAddUserFragment(getContext(), this::updateUI, getCompositeDisposable());
        searchAddUserFragment.setPercentWidthDialog(0.99f);

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

        binding.fakeInputLayoutFullName.setOnClickListener(__ -> searchAddUserFragment.show());
        binding.cardImageProfile.setOnClickListener(__ -> searchAddUserFragment.show());
    }

    private void updateUI(Search search) {
        binding.fullName.setText(search.getFullName());
        binding.staffNumber.setText(search.getStaffNumber());
        binding.imageProfile.setImageURI(search.getPhoto());

        binding.username.requestFocus();
        binding.username.postDelayed(() -> ApplicationUtil.showKeyboard(getContext(), binding.username), 600);
    }

    private void loadingUserRight() {

    }

    @Override
    public void onDestroyView() {
        searchAddUserFragment.onDestroy();
        super.onDestroyView();
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