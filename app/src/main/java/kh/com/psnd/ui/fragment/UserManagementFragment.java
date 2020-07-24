package kh.com.psnd.ui.fragment;

import core.lib.base.BaseFragment;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentUserManagementBinding;
import lombok.val;

public class UserManagementFragment extends BaseFragment<FragmentUserManagementBinding> {

    @Override
    public void setupUI() {
        binding.addNewUser.setOnClickListener(__ -> {
            val fragment = new AddUserFragment();
            fragment.show(getParentFragmentManager(), "");
        });
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_user_management;
    }


}