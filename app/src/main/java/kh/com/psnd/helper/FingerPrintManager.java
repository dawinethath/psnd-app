package kh.com.psnd.helper;

import co.infinum.goldfinger.Goldfinger;
import core.lib.base.BaseFragmentActivity;
import core.lib.utils.Log;
import kh.com.psnd.R;
import kh.com.psnd.base.App;
import lombok.val;

public class FingerPrintManager {

    public static Goldfinger.PromptParams getPromptParams(BaseFragmentActivity activity) {
        Goldfinger.PromptParams params = new Goldfinger.PromptParams.Builder(activity)
                .title(R.string.fingerprint_title)
                .description(R.string.fingerprint_description)
                .negativeButtonText(R.string.cancel)
                .build();
        return params;
    }

    public static boolean checkFingerprintTimer() {
        val userProfile = LoginManager.getUserProfile();
        if (userProfile == null) {
            return false;
        }
        val autoLogoutValue  = App.context.getResources().getIntArray(R.array.autoLogoutValue);
        val userSelectedTime = autoLogoutValue[userProfile.getAutoLogout()];

        val interval = System.currentTimeMillis() - userProfile.getTimeLeave();
        Log.i("interval : " + interval + "     userSelectedTime : " + userSelectedTime);
        return interval > userSelectedTime;
    }
}
