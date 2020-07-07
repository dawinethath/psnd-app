package kh.com.psnd.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import co.infinum.goldfinger.Goldfinger;
import core.lib.base.BaseApp;
import core.lib.base.BaseFragmentActivity;
import core.lib.utils.Log;
import kh.com.psnd.BuildConfig;
import kh.com.psnd.helper.FingerPrintManager;
import kh.com.psnd.helper.LoginManager;
import kh.com.psnd.internal.MobileInternal;
import kh.com.psnd.ui.activity.DetailActivity;
import kh.com.psnd.ui.activity.ImagePreviewActivity;
import kh.com.psnd.ui.activity.MainActivity;
import kh.com.psnd.ui.activity.PdfActivity;
import lombok.val;

public class App extends BaseApp implements Application.ActivityLifecycleCallbacks {

    private Goldfinger goldfinger = null;

    @Override
    public String getBaseUrl() {
        return MobileInternal.url();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        goldfinger = new Goldfinger.Builder(this).logEnabled(BuildConfig.DEBUG).build();
        registerActivityLifecycleCallbacks(this);
    }

    public void checkFingerprintSecurity(@NonNull Activity activity) {
        Log.i("checkFingerprintSecurity");
        if (checkActivitiesToDetectFingerprint(activity)) {
            val userProfile      = LoginManager.getUserProfile();
            val fingerprintTimer = FingerPrintManager.checkFingerprintTimer();
            Log.i("testTime : " + fingerprintTimer);
            Log.i("goldfinger.canAuthenticate() : " + goldfinger.canAuthenticate());
            if (userProfile != null
                    && userProfile.isEnabledFingerprint()
                    && goldfinger.canAuthenticate()
                    && activity instanceof BaseFragmentActivity
                    && fingerprintTimer) {


                val baseActivity = ((BaseFragmentActivity) activity);
                goldfinger.authenticate(FingerPrintManager.getPromptParams(baseActivity), new Goldfinger.Callback() {
                    @Override
                    public void onResult(@NonNull Goldfinger.Result result) {
                        Log.i(new Gson().toJson(result));
                        switch (result.reason()) {
                            case CANCELED:
                            case USER_CANCELED:
                            case TIMEOUT:
                            case NEGATIVE_BUTTON:
                            case LOCKOUT:
                                goldfinger.cancel();

                                val userProfile = LoginManager.getUserProfile();
                                if (userProfile != null) {
                                    userProfile.setTimeLeave(0);
                                    LoginManager.updateUserProfile(userProfile);
                                }
//                                activity.finishAffinity();
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                                break;
                        }
                    }

                    @Override
                    public void onError(@NonNull Exception e) {
                        activity.finish();
                    }
                });
            }
        }
    }

    private boolean checkActivitiesToDetectFingerprint(@NonNull Activity activity) {
        return activity instanceof PdfActivity
                || activity instanceof MainActivity
                || activity instanceof DetailActivity
                || activity instanceof ImagePreviewActivity;
    }

    private boolean checkActivitiesToResetTime(@NonNull Activity activity) {
        return activity instanceof PdfActivity
                || activity instanceof MainActivity
                || activity instanceof DetailActivity
                || activity instanceof ImagePreviewActivity;
    }

    private void updateTimeInOut(@NonNull Activity activity) {
        val userProfile = LoginManager.getUserProfile();
        if (userProfile != null && checkActivitiesToDetectFingerprint(activity)) {
            userProfile.setTimeLeave(System.currentTimeMillis());
            LoginManager.updateUserProfile(userProfile);
        }
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        Log.i(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.i(activity);
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.i(activity + "     updateTimeInOut");
        checkFingerprintSecurity(activity);
        updateTimeInOut(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.i(activity + "     updateTimeInOut");
        updateTimeInOut(activity);
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.i(activity);
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        Log.i(activity);
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.i(activity);
        updateTimeInOut(activity);
    }

}
