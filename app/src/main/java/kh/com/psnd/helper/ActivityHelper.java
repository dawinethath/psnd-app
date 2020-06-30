package kh.com.psnd.helper;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import kh.com.psnd.network.model.Search;
import kh.com.psnd.ui.activity.DetailActivity;
import kh.com.psnd.ui.activity.ImagePreviewActivity;
import kh.com.psnd.ui.activity.LoginActivity;
import kh.com.psnd.ui.activity.MainActivity;
import kh.com.psnd.ui.activity.PdfActivity;
import kh.com.psnd.ui.activity.ProfileActivity;
import lombok.val;

public class ActivityHelper {

    public static void openMainActivity(Context context) {
        val intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void openDetailActivity(@NonNull Context context, @NonNull Search search) {
        val intent = new Intent(context, DetailActivity.class);
        context.startActivity(intent);
    }

    public static void openLoginActivity(@NonNull Context context) {
        val intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void openProfileActivity(@NonNull Context context) {
        val intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }

    public static void openPdfActivity(@NonNull Context context, @NonNull String pdfUri) {
        val intent = new Intent(context, PdfActivity.class);
        intent.putExtra(PdfActivity.EXTRA_PDF_URI, pdfUri);
        context.startActivity(intent);
    }

    public static void openImagePreviewActivity(@NonNull Context context, @NonNull String imageUri) {
        val intent = new Intent(context, ImagePreviewActivity.class);
        intent.putExtra(ImagePreviewActivity.EXTRA_IMAGE_URI, imageUri);
        context.startActivity(intent);
    }

}
