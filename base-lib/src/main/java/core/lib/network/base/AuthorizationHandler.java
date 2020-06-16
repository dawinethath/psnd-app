package core.lib.network.base;

import okhttp3.Request;

public interface AuthorizationHandler {

    public Request applyAuthorizationHeader(Request request);

    public void onUnAuthorized();

    public String getTrackingUUID();

}
