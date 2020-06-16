package core.lib.network.base;

import java.util.Locale;

import core.lib.base.BaseApp;
import core.lib.utils.ApplicationUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;
import okhttp3.Request;

@Data
@AllArgsConstructor
public class HttpHeader implements AuthorizationHandler {

    private static final String KEY_TOKEN        = "x-api-token";
    private static final String KEY_LANG         = "x-user-lang";
    private static final String KEY_APP_ID       = "x-app-id";
    private static final String KEY_VERSION_CODE = "x-version-code";
    private static final String KEY_CONTENT_TYPE = "Content-Type";
    private static final String KEY_LOGIN_TOKEN  = "x-login-token";

    private static final String DEFAULT_CONTENT_TYPE = "application/json";

    private String token;
    private String lang;
    private String contentType;
    private String loginToken;

    public HttpHeader(String token) {
        this(token, "", Locale.getDefault().getLanguage(), DEFAULT_CONTENT_TYPE);
    }

    public HttpHeader(String token, String loginToken) {
        this(token, loginToken, "en", DEFAULT_CONTENT_TYPE);
    }

    @Override
    public Request applyAuthorizationHeader(Request request) {
        val versionCode = ApplicationUtil.getVersionCodeApp(BaseApp.context);
        return request.newBuilder()
                .addHeader(KEY_TOKEN, token)
                .addHeader(KEY_LANG, lang)
                .addHeader(KEY_CONTENT_TYPE, contentType)
                .addHeader(KEY_LOGIN_TOKEN, loginToken)
                .addHeader(KEY_APP_ID, BaseApp.context.getPackageName())
                .addHeader(KEY_VERSION_CODE, String.valueOf(versionCode))
                .build();
    }

    @Override
    public void onUnAuthorized() {

    }

    @Override
    public String getTrackingUUID() {
        return null;
    }
}
