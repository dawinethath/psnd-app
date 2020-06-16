package kh.com.psnd.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import kh.com.psnd.R;
import core.lib.base.BaseActivity;
import kh.com.psnd.helper.ActivityHelper;

public class SplashScreenActivity extends BaseActivity {

    private Handler  handler  = new Handler();
    private Runnable runnable = () -> {
        finish();
        ActivityHelper.openMainActivity(context);
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
