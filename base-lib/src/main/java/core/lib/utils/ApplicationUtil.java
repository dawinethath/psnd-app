package core.lib.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.util.TimeZone;

import kmobile.logger.BuildConfig;
import lombok.val;

public class ApplicationUtil {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static String getCpuType() {
        return System.getProperty("os.arch");
    }

    public static boolean isInstalledPackage(@NonNull Context context, @NonNull String packageId) {
        val applicationInfos = context.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        for (val applicationInfo : applicationInfos) {
            if (applicationInfo.packageName.equalsIgnoreCase(packageId)) {
                return true;
            }
        }
        return false;
    }

    public static String getNetworkType(Context context) {
        try {
            TelephonyManager teleMan     = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int              networkType = teleMan.getNetworkType();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return "1xRTT";
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return "CDMA";
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return "EDGE";
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                    return "eHRPD";
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return "EVDO rev. 0";
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return "EVDO rev. A";
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    return "EVDO rev. B";
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return "GPRS";
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return "HSDPA";
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return "HSPA";
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return "HSPA+";
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return "HSUPA";
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return "iDen";
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return "LTE";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return "UMTS";
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    return "Unknown";
            }
        } catch (Exception e) {
        }
        return "New type of network";
    }

    public static void enableFullScreen(Activity activity, boolean isFullScreen) {
        View mDecorView = activity.getWindow().getDecorView();
        if (isFullScreen) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
        else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public static void hideSystemUI(Activity activity) {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    public static void showSystemUI(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    public static boolean isHeadsetConnected(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.isWiredHeadsetOn();
    }

    public static void keepScreenOff(Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public static void keepScreenOn(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public static String getApplicationName(Context context) {
        int stringId = context.getApplicationInfo().labelRes;
        return context.getString(stringId);
    }

    public static boolean isPackageInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static int getMnc(Context context) {
        TelephonyManager tel             = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String           networkOperator = tel.getNetworkOperator();
        try {
            if (networkOperator != null) {
                int mnc = Integer.parseInt(networkOperator.substring(3));
                return mnc;
            }
        } catch (Exception e) {
        }
        return context.getResources().getConfiguration().mnc;
    }

    public static int getMcc(Context context) {
        TelephonyManager tel             = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String           networkOperator = tel.getNetworkOperator();
        try {
            if (networkOperator != null) {
                int mcc = Integer.parseInt(networkOperator.substring(0, 3));
                return mcc;
            }
        } catch (Exception e) {
        }
        return context.getResources().getConfiguration().mcc;
    }

    public static String getUrlMarketOfApp(Context context) {
        return "https://play.google.com/store/apps/details?id=" + context.getPackageName();
    }

    public static String getTimeZone() {
        try {
            TimeZone tz = TimeZone.getDefault();
            return tz.getDisplayName(false, TimeZone.SHORT) + " " + tz.getID();
        } catch (AssertionError e) {
            return "";
        }
    }

    public static String getLanguage(Context context) {
        return context.getResources().getConfiguration().locale.getDisplayLanguage();
    }

    public static void CancelNotification(Context ctx, long notifyId) {
        val ns   = Context.NOTIFICATION_SERVICE;
        val nMgr = (NotificationManager) ctx.getSystemService(ns);
        nMgr.cancel((int) notifyId);
    }

    public static String getCurrentActivityName(Activity activity) {
        val am               = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
        val taskInfo         = am.getRunningTasks(1);
        val currentClassName = taskInfo.get(0).topActivity.getClassName();
        return currentClassName;
    }

    public static void dismissKeyboard(Activity activity) {
        try {
            val inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
        }
    }

    public static void dismissKeyboard(Context context, View view) {
        try {
            val inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
        }
    }

    public static void showKeyboard(Context context, EditText editText) {
        try {
            val inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception e) {
            Log.e(e);
        }
    }

    public static boolean isOnline(Context context) {
        val connMgr     = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        val networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static boolean isOnlineMobile(Context context) {
        val connMgr     = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        val networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static boolean isOnlineWiFi(Context context) {
        val connMgr     = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        val networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static String getVersionApp(Context context) {
        String appVersion = "";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            appVersion = pInfo.versionName;
        } catch (NameNotFoundException e) {
        }
        return appVersion;
    }

    public static int getVersionCodeApp(Context context) {
        int versionCode = 0;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = pInfo.versionCode;
        } catch (NameNotFoundException e) {
        }
        return versionCode;
    }

    public static int getWidthScreen(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int getHeightScreen(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        val displayMetrics = new DisplayMetrics();
        val wm             = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static boolean isApplicationRunning(Context context) {
        val activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        val tasks           = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (RunningTaskInfo task : tasks) {
            if (context.getPackageName().equalsIgnoreCase(task.baseActivity.getPackageName())) {
                return true;
            }
        }

        return false;
    }

    public static void openWebBrowser(Context context, String url) {
        // if (!(url.indexOf("http://") > -1)) {
        // url = "http://" + url;
        // }
        Log.i("[Donation URL_REQUEST_LINK]" + url);
        val browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(browserIntent);
    }

    public static void launchApp(Context context, String packageId) {
        try {
            val launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageId);
            context.startActivity(launchIntent);
        } catch (Exception e) {
            Log.e(e);
        }
    }

    public static void openGoogleMap(Context context, String name, double latitude, double longitude) {
        String url = "";
        if (name == null) {
            name = "";
        }
        if (latitude != 0 && longitude != 0) {
            url = "geo:0,0?q=%s,%s";
            url = String.format(url, String.valueOf(latitude).replace(",", "."), String.valueOf(longitude).replace(",", "."));
            Log.i("[url]" + url);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        }
    }// Concatenate 2 arrays of String. elements of s1 will be at first of s2

    public static void openPlaystore(Context context, String appPackageName) {
        try {
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
            marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            context.startActivity(marketIntent);
        } catch (ActivityNotFoundException e) {
            Log.i("LOG >> No store app : " + e.toString());
            // User is not in China
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static void openPlaystore(Context context) {
        openPlaystore(context, context.getPackageName());
    }

    public static void openPlayStoreOtherApp(Context context, String publisherName) {
        val marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://market.android.com/search?q=pub:" + publisherName));
        marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        context.startActivity(marketIntent);
    }

    @SuppressLint("MissingPermission")
    public static void phoneCall(Context context, String number) {
        val callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        context.startActivity(callIntent);
    }

    public static void phoneDial(Context context, String number) {
        val callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + number));
        context.startActivity(callIntent);
    }

    protected boolean hasMicrophone(Context context) {
        val packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE);
    }

    public static Drawable getIconApp(Context context) {
        return context.getPackageManager().getApplicationIcon(context.getApplicationInfo());
    }

    public static void openSystemPermission(Context context) {
        if (MIUIUtils.isMIUI()) {
            MIUIUtils.openSystemPermission(context);
        }
        else {
            context.startActivity(getIntentSystemPermission(context));
        }
    }

    public static void openSystemWriteSetting(Context context) {
        context.startActivity(new Intent("android.settings.action.MANAGE_WRITE_SETTINGS"));
    }

    public static boolean checkIntentHandleByActivity(Context context, String action) {
        val            intent         = new Intent(action);
        PackageManager packageManager = context.getPackageManager();
        if (intent.resolveActivity(packageManager) != null) {
            return true;
        }
        return false;
    }

    public static void openInternalStorageSetting(Context context) {
        try {
            context.startActivity(new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS));
        } catch (Throwable e) {
            Log.e(e);
        }
    }

    public static Intent getIntentSystemPermission(Context context) {
        val intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public static void lockRotation(Activity activity) {
        int orientation = activity.getRequestedOrientation();
        int rotation = ((WindowManager) activity.getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
                orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                break;
            case Surface.ROTATION_90:
                orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                break;
            case Surface.ROTATION_180:
                orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                break;
            default:
                orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                break;
        }

        activity.setRequestedOrientation(orientation);
    }

    public static void unlockRotation(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    public static String getDensityName(Context context) {
//        float density = context.getResources().getDisplayMetrics().density;
//        if (density >= 4.0) {
//            return "xxxhdpi";
//        }
//        if (density >= 3.0) {
//            return "xxhdpi";
//        }
//        if (density >= 2.0) {
//            return "xhdpi";
//        }
//        if (density >= 1.5) {
//            return "hdpi";
//        }
//        if (density >= 1.0) {
//            return "mdpi";
//        }
//        return "ldpi";

        int densityDpi = context.getResources().getDisplayMetrics().densityDpi;
        switch (densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                return "ldpi";
            case DisplayMetrics.DENSITY_MEDIUM:
                return "mdpi";
            case DisplayMetrics.DENSITY_TV:
            case DisplayMetrics.DENSITY_HIGH:
                return "hdpi";
            case DisplayMetrics.DENSITY_XHIGH:
            case DisplayMetrics.DENSITY_280:
                return "xhdpi";
            case DisplayMetrics.DENSITY_XXHIGH:
            case DisplayMetrics.DENSITY_360:
            case DisplayMetrics.DENSITY_400:
            case DisplayMetrics.DENSITY_420:
                return "xxhdpi";
            case DisplayMetrics.DENSITY_XXXHIGH:
            case DisplayMetrics.DENSITY_560:
                return "xxxhdpi";
            default:
                return "unknown";
        }
    }

    public static String getAppVersionName(Context context) {
        String version = String.valueOf(getApplicationName(context) + " " + getVersionApp(context));
        if (BuildConfig.DEBUG_MODE) {
            version = getApplicationName(context) + " " + getVersionApp(context) + "(" + getVersionCodeApp(context) + ") " + context.getPackageName();
        }
        return version;
    }
}
