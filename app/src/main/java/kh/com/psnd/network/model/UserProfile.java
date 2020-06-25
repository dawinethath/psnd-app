package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfile extends BaseGson {
    @SerializedName("username")
    private String username;
    @SerializedName("id")
    private String id;
    @SerializedName("token")
    private String token;
    @SerializedName("image")
    private String image;
}
