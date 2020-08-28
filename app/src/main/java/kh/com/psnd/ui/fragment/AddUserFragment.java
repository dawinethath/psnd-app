package kh.com.psnd.ui.fragment;

import core.lib.base.BaseBottomSheetDialogFragment;
import core.lib.utils.ApplicationUtil;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentAddUserBinding;
import kh.com.psnd.mock.MockUsers;
import kh.com.psnd.network.model.SearchStaff;

public class AddUserFragment extends BaseBottomSheetDialogFragment<FragmentAddUserBinding> {
    private SearchAddUserFragment searchAddUserFragment = null;

    @Override
    public void setupUI() {
        searchAddUserFragment = new SearchAddUserFragment(getContext(), this::updateUI, getCompositeDisposable());
        searchAddUserFragment.setPercentWidthDialog(0.99f);

        binding.userRight.setupUI(this, MockUsers.userRole_user);
        loadingUserRight();

        binding.fakeInputLayoutFullName.setOnClickListener(__ -> searchAddUserFragment.show());
        binding.cardImageProfile.setOnClickListener(__ -> searchAddUserFragment.show());
    }

    private void updateUI(SearchStaff searchStaff) {
        binding.fullName.setText(searchStaff.getFullName());
        binding.staffNumber.setText(searchStaff.getStaffNumber());
        binding.imageProfile.setImageURI(searchStaff.getPhoto());
        binding.username.setText(searchStaff.getStaffNumberEn());

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

}