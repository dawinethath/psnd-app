package kh.com.psnd.helper;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

import kh.com.psnd.network.model.SearchStaff;
import kh.com.psnd.ui.activity.DetailActivity;
import kh.com.psnd.ui.activity.ImagePreviewActivity;
import kh.com.psnd.ui.activity.LoginActivity;
import kh.com.psnd.ui.activity.MainActivity;
import kh.com.psnd.ui.activity.PdfActivity;
import kh.com.psnd.ui.activity.ProfileActivity;
import kh.com.psnd.ui.activity.SignUpActivity;
import lombok.val;

public class ActivityHelper {

    public static void openMainActivity(Context context) {
        val intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void openDetailActivity(@NonNull Context context, @NonNull List<SearchStaff> items, int position) {
        val intent = new Intent(context, DetailActivity.class);
        intent.putExtra(SearchStaff.EXTRA_LIST, (Serializable) items);
        intent.putExtra(SearchStaff.EXTRA_POSITION, position);
        context.startActivity(intent);
    }

    public static void openLoginActivity(@NonNull Context context) {
        val intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void openSignupActivity(@NonNull Context context) {
        val intent = new Intent(context, SignUpActivity.class);
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
