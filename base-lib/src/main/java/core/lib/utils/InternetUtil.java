package core.lib.utils;

import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import core.lib.base.BaseApp;
import core.lib.R;

public class InternetUtil {
    /**
     * Using Snackbar view
     */
    public static void showNoInternet(View view) {
        Snackbar.make(view, R.string.noInternetConnection, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Using Toast view
     */
    public static void showNoInternet() {
        Toast.makeText(BaseApp.context, R.string.noInternetConnection, Toast.LENGTH_LONG).show();
    }
}
