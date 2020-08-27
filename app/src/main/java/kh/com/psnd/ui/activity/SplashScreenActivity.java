package kh.com.psnd.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import core.lib.base.BaseFragmentActivity;
import core.lib.utils.ApplicationUtil;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivitySplashscreenBinding;
import kh.com.psnd.helper.ActivityHelper;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.service.ConfigService;

public class SplashScreenActivity extends BaseFragmentActivity<ActivitySplashscreenBinding> {
    @Override
    protected int layoutId() {
        return R.layout.activity_splashscreen;
    }

    private Handler  handler  = new Handler();
    private Runnable runnable = () -> {
        finish();
        if (LoginManager.isLoggedIn()) {
            ActivityHelper.openMainActivity(context);
        }
        else {
            ActivityHelper.openLoginActivity(context);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConfigService.start(context);

        String version = "v" + ApplicationUtil.getVersionApp(context);
        binding.version.setText(version);

        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
