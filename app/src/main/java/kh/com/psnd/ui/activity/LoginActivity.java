package kh.com.psnd.ui.activity;

import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import core.lib.base.BaseFragmentActivity;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivityLoginBinding;
import kh.com.psnd.eventbus.SingUpSuccessEventBus;

public class LoginActivity extends BaseFragmentActivity<ActivityLoginBinding> {
    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnabledEventBus(false);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSingUpSuccessEventBus(SingUpSuccessEventBus event) {
        Log.i(event);
        finish();
    }
}