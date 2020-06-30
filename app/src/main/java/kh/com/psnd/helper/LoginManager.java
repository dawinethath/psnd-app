package kh.com.psnd.helper;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import core.lib.base.BaseApp;
import core.lib.base.BaseFragmentActivity;
import core.lib.utils.CryptoUtil;
import core.lib.utils.FileManager;
import core.lib.utils.Log;
import kh.com.psnd.internal.MobileInternal;
import kh.com.psnd.network.model.UserProfile;
import lombok.val;

public class LoginManager {

    private static final String SECRET = "login.bin";


    public static void loggedIn(@NonNull UserProfile userProfile) {
        if (userProfile == null) {
            throw new RuntimeException("Login cannot null");
        }
        try {
            val encrypt = CryptoUtil.encryptMsg(userProfile.toJson(), MobileInternal.secret());
            FileManager.writeTextToFileInContext(BaseApp.context, SECRET, encrypt);
        } catch (Throwable e) {
            Log.e(e);
        }
    }

    public static void logout(BaseFragmentActivity activity) {
        FileManager.writeTextToFileInContext(BaseApp.context, SECRET, "");
        ActivityHelper.openLoginActivity(activity);
        activity.finish();
    }

    public static boolean isLoggedIn() {
        return getUserProfile() != null;
    }

    public static void updateUserProfile(@NonNull UserProfile userProfile) {
        loggedIn(userProfile);
    }

    public static UserProfile getUserProfile() {
        try {
            val encrypt = FileManager.readFileFromContextToBytes(BaseApp.context, SECRET);
            val decrypt = CryptoUtil.decryptMsg(encrypt, MobileInternal.secret());
            val json    = decrypt;
            val login   = new Gson().fromJson(json, UserProfile.class);
            if (login != null && !TextUtils.isEmpty(login.getToken())) {
                return login;
            }
        } catch (Throwable e) {
            Log.e(e);
        }
        return null;
    }

}
