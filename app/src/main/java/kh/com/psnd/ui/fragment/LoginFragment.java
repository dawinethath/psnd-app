package kh.com.psnd.ui.fragment;

import core.lib.base.BaseFragment;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentLoginBinding;
import kh.com.psnd.helper.ActivityHelper;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    @Override
    public void setupUI() {
        binding.btnLogin.setOnClickListener(__ -> ActivityHelper.openMainActivity(getContext()));
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }


}