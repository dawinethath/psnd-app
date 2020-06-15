package kh.com.psnd.helper;

import android.content.Context;
import android.content.Intent;

import kh.com.psnd.ui.activity.MainActivity;
import lombok.val;

public class ActivityHelper {
    public static void openMainActivity(Context context) {
        val intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
