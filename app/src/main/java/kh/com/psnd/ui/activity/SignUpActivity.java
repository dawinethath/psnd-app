package kh.com.psnd.ui.activity;

import android.os.Bundle;

import core.lib.base.BaseFragmentActivity;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivityFragmentBinding;
import kh.com.psnd.ui.fragment.SignUpFragment;

public class SignUpActivity extends BaseFragmentActivity<ActivityFragmentBinding> {

    @Override
    protected int layoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentFragment, new SignUpFragment(), "SignUpStep1Fragment")
                    .commit();
        }
    }

}