package kh.com.psnd.base;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import kh.com.psnd.R;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context context = this;

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.fade_in_quick, R.anim.fade_out_quick);
    }
}
