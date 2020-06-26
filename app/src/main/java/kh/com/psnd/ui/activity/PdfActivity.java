package kh.com.psnd.ui.activity;

import android.os.Bundle;

import core.lib.base.BaseFragmentActivity;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.ActivityPdfBinding;
import lombok.val;

public class PdfActivity extends BaseFragmentActivity<ActivityPdfBinding> {
    public static String EXTRA_PDF_URI = "EXTRA_PDF_URI";

    @Override
    protected int layoutId() {
        return R.layout.activity_pdf;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        val uriPdf = getIntent().getStringExtra(EXTRA_PDF_URI);
        Log.i(uriPdf);
    }

}