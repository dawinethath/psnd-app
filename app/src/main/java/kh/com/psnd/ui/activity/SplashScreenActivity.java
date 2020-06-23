package kh.com.psnd.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import kh.com.psnd.R;
import core.lib.base.BaseFragmentActivity;
import kh.com.psnd.databinding.ActivitySplashscreenBinding;
import kh.com.psnd.helper.ActivityHelper;

public class SplashScreenActivity extends BaseFragmentActivity<ActivitySplashscreenBinding> {
    @Override
    protected int layoutId() {
        return R.layout.activity_splashscreen;
    }

    private Handler  handler  = new Handler();
    private Runnable runnable = () -> {
        finish();
//        ActivityHelper.openMainActivity(context);
        ActivityHelper.openLoginActivity(context);
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
