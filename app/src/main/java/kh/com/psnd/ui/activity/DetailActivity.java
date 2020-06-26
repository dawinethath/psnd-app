package kh.com.psnd.ui.activity;

import android.os.Bundle;

import core.lib.base.BaseFragmentActivity;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseFragmentActivity<ActivityDetailBinding> {
    @Override
    protected int layoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}