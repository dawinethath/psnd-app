package kh.com.psnd.ui.fragment.user;

import android.os.Bundle;

import core.lib.base.BaseFragment;
import core.lib.dialog.DialogProgress;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSignupStep3Binding;
import lombok.val;

public class SignUpStep3Fragment extends BaseFragment<FragmentSignupStep3Binding> {
    private DialogProgress progress = null;

    public static SignUpStep3Fragment newInstance() {
        val fragment = new SignUpStep3Fragment();
        val bundle   = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupUI() {
        progress = new DialogProgress(getContext(), true, dialogInterface -> getCompositeDisposable().clear());

    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_signup_step_3;
    }

}