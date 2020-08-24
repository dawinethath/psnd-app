package kh.com.psnd.helper;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.Serializable;
import java.util.List;

import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.network.model.SearchStaff;
import kh.com.psnd.network.model.Staff;
import kh.com.psnd.ui.activity.DetailActivity;
import kh.com.psnd.ui.activity.ImagePreviewActivity;
import kh.com.psnd.ui.activity.LoginActivity;
import kh.com.psnd.ui.activity.MainActivity;
import kh.com.psnd.ui.activity.PdfActivity;
import kh.com.psnd.ui.activity.ProfileActivity;
import kh.com.psnd.ui.activity.SignUpActivity;
import kh.com.psnd.ui.fragment.ImageStaffFragment;
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

    public static void openSignUpActivity(@NonNull Context context) {
        new MaterialAlertDialogBuilder(context)
                .setIcon(R.drawable.ic_outline_perm_identity_24)
                .setTitle(R.string.sign_up_guide_title)
                .setMessage(R.string.sign_up_guide_desc)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.next, (dialogInterface, i) -> {
                    val intent = new Intent(context, SignUpActivity.class);
                    context.startActivity(intent);
                }).show();
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

    public static void openImagePreviewActivity(@NonNull Context context, @NonNull Staff staff, int position) {
        Log.i("Selected position : " + position);
        val intent = new Intent(context, ImagePreviewActivity.class);
        intent.putExtra(Staff.EXTRA, staff);
        intent.putExtra(ImageStaffFragment.POSITION, position);
        context.startActivity(intent);
    }

}
