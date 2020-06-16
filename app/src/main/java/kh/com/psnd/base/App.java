package kh.com.psnd.base;

import core.lib.base.BaseApp;
import kh.com.psnd.internal.MobileInternal;

public class App extends BaseApp {
    @Override
    public String getBaseUrl() {
        return MobileInternal.url();
    }
}
