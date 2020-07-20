package kh.com.psnd.ui.activity;

import android.os.Bundle;

import core.lib.base.BaseFragmentActivity;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivitySignupBinding;

public class SignupActivity extends BaseFragmentActivity<ActivitySignupBinding> {

    @Override
    protected int layoutId() {
        return R.layout.activity_signup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}