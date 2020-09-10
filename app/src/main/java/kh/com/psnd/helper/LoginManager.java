package kh.com.psnd.helper;

import androidx.annotation.NonNull;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.core.Amplify;
import com.google.gson.Gson;

import core.lib.base.BaseApp;
import core.lib.base.BaseFragmentActivity;
import core.lib.utils.CryptoUtil;
import core.lib.utils.FileManager;
import core.lib.utils.Log;
import kh.com.psnd.database.dao.SearchHistoryDao;
import kh.com.psnd.internal.MobileInternal;
import kh.com.psnd.network.model.StaffFilter;
import kh.com.psnd.network.model.LoginProfile;
import lombok.val;

public class LoginManager {

    private static final String SECRET = "login.bin";

    public static void loggedIn(@NonNull LoginProfile loginProfile) {
        Log.i("loggedIn");
        if (loginProfile == null) {
            throw new RuntimeException("Login cannot null");
        }
        try {
            val json = loginProfile.toJson();
            Log.i(json);
            val encrypt = CryptoUtil.encryptMsg(json, MobileInternal.secret());
            FileManager.writeTextToFileInContext(BaseApp.context, SECRET, encrypt);
        } catch (Throwable e) {
            Log.e(e);
        }
    }

    public static void logout(BaseFragmentActivity activity) {
        Log.i("logout");
        FileManager.writeTextToFileInContext(BaseApp.context, SECRET, "");
        // clear cache
        SearchHistoryDao.clearCache();
        StaffFilter.clearLastFilter();
        ActivityHelper.openLoginActivity(activity);
        activity.finish();

        Amplify.Auth.signOut(
                () -> Log.i("AuthQuickstart Signed out successfully"),
                error -> Log.e("AuthQuickstart " + error.toString())
        );
    }

    public static boolean isLoggedIn() {
        return getUserProfile() != null;
    }

    public static void updateUserProfile(@NonNull LoginProfile loginProfile) {
        loggedIn(loginProfile);
    }

    public static LoginProfile getUserProfile() {
        try {
            val encrypt = FileManager.readFileFromContextToBytes(BaseApp.context, SECRET);
            val decrypt = CryptoUtil.decryptMsg(encrypt, MobileInternal.secret());
            val json    = decrypt;
            val login   = new Gson().fromJson(json, LoginProfile.class);
            if (login != null && login.getTokens() != null) {
                return login;
            }
        } catch (Throwable e) {
            Log.e(e);
        }
        return null;
    }

    public static String getUserToken() {
        try {
            return AWSMobileClient.getInstance().getTokens().getIdToken().getTokenString();
        } catch (Throwable e) {
            return "";
        }
    }

    public static String getUserId() {
        return "";
    }
}
