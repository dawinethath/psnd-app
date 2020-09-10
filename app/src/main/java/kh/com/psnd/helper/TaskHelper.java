package kh.com.psnd.helper;

import core.lib.network.base.BaseNetwork;
import core.lib.network.base.HttpHeader;

public class TaskHelper {
    public static HttpHeader getHttpHeader() {
        return new HttpHeader(BaseNetwork.getToken(), LoginManager.getUserToken(), LoginManager.getUserId());
    }
}
