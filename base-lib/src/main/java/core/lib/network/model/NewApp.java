package core.lib.network.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewApp extends BaseGson {

    @SerializedName("packageName")
    private String packageName = null;
    @SerializedName("title")
    private String title       = null;
    @SerializedName("description")
    private String description = null;
    @SerializedName("action")
    private String action      = null;
    @SerializedName("url")
    private String url         = null;
    @SerializedName("image")
    private String image       = null;

}
