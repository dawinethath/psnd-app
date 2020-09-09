package kh.com.psnd.ui.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import core.lib.base.BaseFragment;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.databinding.LayoutDetailHeaderBinding;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.network.model.Staff;
import kh.com.psnd.ui.activity.MainActivity;
import kh.com.psnd.utils.PdfUtil;
import lombok.val;

public class DetailHeaderView extends FrameLayout {

    private       LayoutDetailHeaderBinding binding     = null;
    private final int                       BLUR_RADIUS = 25;

    public DetailHeaderView(@NonNull Context context) {
        super(context);
        init();
    }

    public DetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        binding = LayoutDetailHeaderBinding.inflate(LayoutInflater.from(getContext()), this, true);
        if (!isInEditMode()) {
        }
    }

    public void setCurrentImageStaff(int position) {
        binding.imagesStaffViewPager.setCurrentItem(position);
    }

    //create bitmap from the ScrollView
    private Bitmap getBitmapFromView(View view, int height, int width) {
        Bitmap   bitmap     = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas   canvas     = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        }
        else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return bitmap;
    }

    private Bitmap combineBitmaps(final Bitmap top, final Bitmap bottom) {
        // Get the size of the images combined side by side.
        int width  = top.getWidth();
        int height = top.getHeight() + bottom.getHeight();

        // Create a Bitmap large enough to hold both input images and a canvas to draw to this
        // combined bitmap.
        Bitmap combined = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas   = new Canvas(combined);

        // Render both input images into the combined bitmap and return it.
        canvas.drawBitmap(top, 0f, 0f, null);
        canvas.drawBitmap(bottom, 0f, top.getHeight(), null);

        return combined;
    }

    private void shareImageUri(Staff staff, Uri uri) {
        val title      = getContext().getString(R.string.detail_staff_info, staff.getFullName());
        val sendIntent = new Intent(android.content.Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TITLE, title);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        sendIntent.setType("application/pdf");

        val shareIntent = Intent.createChooser(sendIntent, null);
        getContext().startActivity(shareIntent);
    }

    public void setupUI(@NonNull BaseFragment baseFragment, @NonNull DetailHeaderToolbarView headerToolbarView, @NonNull Staff staff) {
        binding.imagesStaffViewPager.bind(baseFragment, headerToolbarView, staff);
        binding.firstNameKH.setText(staff.getFullName());
        binding.staffId.setText(staff.getId());
        binding.headerTitle.setText(staff.getGeneralCommissariat());
        binding.share.setOnClickListener(__ -> new MaterialAlertDialogBuilder(getContext())
                .setItems(R.array.share, (DialogInterface.OnClickListener) (dialog, which) -> {
                    switch (which) {
                        case 0:
                            makeScreenshotAndShare(baseFragment, staff);
                            break;
                        case 1:
                            staff.doShare(getContext());
                            break;
                    }
                })
                .show());

//        binding.exportPdf.setOnClickListener(__ -> Toast.makeText(getContext(), "Clicked on Export PDF", Toast.LENGTH_SHORT).show());

        {
            // Study blur image
            /*
            binding.layoutBlurImage.setVisibility(GONE);
            binding.blurImage.setVisibility(GONE);
            val bitmap = FrescoUtil.getBitmapFromCache(staff.getPhoto());
            if (bitmap != null) {
                val blurBitmap = BlurKit.getInstance().blur(bitmap, BLUR_RADIUS);
                if (blurBitmap != null) {
                    binding.blurImage.setImageBitmap(blurBitmap);
                    binding.blurImage.setVisibility(VISIBLE);
                    binding.layoutBlurImage.setVisibility(VISIBLE);
                }
            }
            */
        }

    }

    public void makeScreenshotAndShare(@NonNull BaseFragment baseFragment, @NonNull Staff staff) {
        binding.groupAction.setVisibility(GONE);
        val pdfFile = makeScreenshotPdf(baseFragment, staff);
        binding.groupAction.setVisibility(VISIBLE);
        val uri = FileProvider.getUriForFile(getContext(), getContext().getString(R.string.fileprovider), pdfFile);
        shareImageUri(staff, uri);
    }

    public File makeScreenshotPdf(@NonNull BaseFragment baseFragment, @NonNull Staff staff) {
        val                  bitmap   = getScreenshotBitmap(baseFragment);
        PdfDocument          document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page     page     = document.startPage(pageInfo);
        Canvas               canvas   = page.getCanvas();
        Paint                paint    = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawPaint(paint);
//            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        File pdfFile = PdfUtil.getScreenshotPdfStaff(staff);
        try {
            document.writeTo(new FileOutputStream(pdfFile));
        } catch (IOException e) {
            Log.e(e);
        }
        document.close();
        return pdfFile;
    }

    public Bitmap getScreenshotBitmap(@NonNull BaseFragment baseFragment) {
        NestedScrollView scrollView = baseFragment.getBinding().getRoot().findViewById(R.id.scrollView);
        val              bitmapBody = getBitmapFromView(scrollView, scrollView.getChildAt(0).getHeight(), scrollView.getChildAt(0).getWidth());

        AppBarLayout appBarLayout = baseFragment.getBinding().getRoot().findViewById(R.id.appBarLayout);
        val          bitmapHeader = getBitmapFromView(appBarLayout, appBarLayout.getHeight(), appBarLayout.getWidth());

        return combineBitmaps(bitmapHeader, bitmapBody);
    }

    public void showProgress() {
        binding.progressBar.setVisibility(VISIBLE);
    }

    public void hideProgress() {
        binding.progressBar.setVisibility(GONE);
    }

}