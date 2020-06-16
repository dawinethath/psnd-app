package core.lib.network.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScriptWebView extends BaseGson {
    public static final String EXTRA = "ScriptWebView";

    @SerializedName("enabled")
    private boolean enabled = false;
    @SerializedName("script")
    private String  script  = null;

}
