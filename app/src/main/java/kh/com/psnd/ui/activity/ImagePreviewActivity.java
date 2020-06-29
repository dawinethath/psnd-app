package kh.com.psnd.ui.activity;

import android.os.Bundle;

import core.lib.base.BaseFragmentActivity;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivityImagePreviewBinding;
import lombok.val;

public class ImagePreviewActivity extends BaseFragmentActivity<ActivityImagePreviewBinding> {
    public static String EXTRA_IMAGE_URI = "EXTRA_IMAGE_URI";

    @Override
    protected int layoutId() {
        return R.layout.activity_image_preview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        val uriPdf = getIntent().getStringExtra(EXTRA_IMAGE_URI);
        Log.i(uriPdf);
    }

}