package kh.com.psnd.ui.fragment.user;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import core.lib.base.BaseBottomSheetDialogFragment;
import core.lib.listener.MyTextWatcher;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentAddUserBinding;
import kh.com.psnd.network.model.SearchStaff;
import kh.com.psnd.network.model.UserProfile;
import kh.com.psnd.network.model.UserRolePrivilege;
import lombok.val;

public class AddUserFragment extends BaseBottomSheetDialogFragment<FragmentAddUserBinding> {

    private SearchAddUserFragment searchAddUserFragment = null;

    public static AddUserFragment newInstance(@NonNull UserRolePrivilege userRolePrivilege) {
        val fragment = new AddUserFragment();
        val bundle   = new Bundle();
        bundle.putSerializable(UserRolePrivilege.EXTRA, userRolePrivilege);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {
        searchAddUserFragment = new SearchAddUserFragment(getContext(), this::updateUI, getCompositeDisposable());
        searchAddUserFragment.setPercentWidthDialog(0.99f);

        val userRolePrivilege = (UserRolePrivilege) getArguments().getSerializable(UserRolePrivilege.EXTRA);

        binding.userRight.setupUI(this, userRolePrivilege, userRolePrivilege.getDefaultRole());
        loadingUserRight();

        binding.fakeInputLayoutFullName.setOnClickListener(__ -> searchAddUserFragment.show());
        binding.cardImageProfile.setOnClickListener(__ -> searchAddUserFragment.show());
        binding.btnSubmit.setOnClickListener(__ -> onClickSubmit());
        binding.username.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }
        });
    }

    private boolean isValidateUsername() {
        if (TextUtils.isEmpty(binding.username.getText())) {
            binding.textInputLayoutUserName.setError(getString(R.string.username_error));
            return false;
        }
        binding.textInputLayoutUserName.setError(null);
        return true;
    }

    private void onClickSubmit() {
        if (!ApplicationUtil.isOnline()) {
            Snackbar.make(getView(), R.string.noInternetConnection, Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (isValidateUsername() && binding.passwordView.isValidPassword()) {
            // todo call api register cognito and our api
            UserProfile userProfile = new UserProfile();
//            userProfile.set
        }
    }

    private void updateUI(SearchStaff searchStaff) {
        binding.fullName.setText(searchStaff.getFullName());
        binding.staffNumber.setText(searchStaff.getStaffNumber());
        binding.imageProfile.setImageURI(searchStaff.getPhoto());
        binding.username.setTextWithEndCursor(searchStaff.getStaffNumberEn());

        binding.username.requestFocus();
//        binding.username.postDelayed(() -> ApplicationUtil.showKeyboard(getContext(), binding.username), 600);
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

}