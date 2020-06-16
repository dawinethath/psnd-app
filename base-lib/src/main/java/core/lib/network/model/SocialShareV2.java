package core.lib.network.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class SocialShareV2 extends BaseGson {

    @SerializedName("title")
    private String title;
    @SerializedName("inviteLink")
    private String inviteLink;

}
