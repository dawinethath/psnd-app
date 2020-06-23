package kh.com.psnd.ui.activity;

import android.os.Bundle;

import core.lib.base.BaseFragmentActivity;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseFragmentActivity<ActivityLoginBinding> {
    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

}