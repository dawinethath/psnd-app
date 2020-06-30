package kh.com.psnd.helper;

import co.infinum.goldfinger.Goldfinger;
import core.lib.base.BaseFragment;
import kh.com.psnd.R;

public class FingerPrintManager {

    public static Goldfinger.PromptParams getPromptParams(BaseFragment fragment) {
        Goldfinger.PromptParams params = new Goldfinger.PromptParams.Builder(fragment)
                .title(R.string.fingerprint_title)
                .description(R.string.fingerprint_description)
                .negativeButtonText(R.string.cancel)
                .build();
        return params;
    }

}
