package kh.com.psnd.network.model;

import com.amplifyframework.core.AmplifyConfiguration;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import core.lib.base.BaseApp;
import core.lib.network.model.BaseGson;
import core.lib.utils.CryptoUtil;
import core.lib.utils.FileManager;
import core.lib.utils.Log;
import kh.com.psnd.internal.MobileInternal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;
import lombok.var;

@Data
@EqualsAndHashCode(callSuper = false)
public class Config extends BaseGson {

    private static final String CACHE_NAME = "Config";
    private static       Config config;

    @SerializedName("appConfig")
    private AppConfig appConfig = new AppConfig();

    @SerializedName("cognito")
    private Object cognito;

    public static synchronized Config newInstance() {
        config = getCache();
        return config;
    }

    public static synchronized Config getInstance() {
        if (config == null) {
            config = getCache();
        }
        return config;
    }

    public AmplifyConfiguration getAmplifyConfiguration() {
        try {
            val json = BaseApp.getGson().toJson(cognito);
            return AmplifyConfiguration.fromJson(new JSONObject(json));
        } catch (Exception e) {
            Log.e(e);
        }
        return null;
    }

    private static Config getCache() {
        try {
            val encrypt = FileManager.readFileFromContextToBytes(BaseApp.context, CACHE_NAME);
            val decrypt = CryptoUtil.decryptMsg(encrypt, MobileInternal.secret());
            val json    = decrypt;
            var obj     = new Gson().fromJson(json, Config.class);
            if (obj == null) {
                obj = new Config();
            }
            return obj;
        } catch (Exception e) {
        }
        return new Config();
    }

    public void saveToCache() {
        try {
            val encrypt = CryptoUtil.encryptMsg(toJson(), MobileInternal.secret());
            FileManager.writeTextToFileInContext(BaseApp.context, CACHE_NAME, encrypt);
        } catch (Throwable e) {
            Log.e(e);
        }
    }

}
