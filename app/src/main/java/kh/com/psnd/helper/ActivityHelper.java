package kh.com.psnd.helper;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import kh.com.psnd.network.model.Search;
import kh.com.psnd.ui.activity.DetailActivity;
import kh.com.psnd.ui.activity.MainActivity;
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

}
