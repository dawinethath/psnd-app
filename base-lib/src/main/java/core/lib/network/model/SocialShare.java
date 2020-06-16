package core.lib.network.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import kmobile.library.utils.MyBase64Utils;
import lombok.Getter;

@Getter
public class SocialShare extends BaseGson {

    @SerializedName("title")
    private String title;
    @SerializedName("msg")
    private String msg;
    @SerializedName("image")
    private String image;
    @SerializedName("inviteLink")
    private String inviteLink;
    @SerializedName("inviteLinkV2")
    private String inviteLinkV2;
    @SerializedName("facebookPage")
    private String facebookPage;
    @SerializedName("facebookPageId")
    private String facebookPageId;
    @SerializedName("website")
    private String website;
    @SerializedName("shareSongUrl")
    private String shareSongUrl;
    @SerializedName("shareSongTitle")
    private String shareSongTitle;

    /**
     * Use for Khmer Music
     *
     * @param title
     * @return
     */
    public String getShareSongTitle(String title) {
        return shareSongTitle + " - " + title;
    }

    /**
     * Use for Khmer Music
     *
     * @param id
     * @return
     */
    public String getShareSongUrl(String id) {
        id = MyBase64Utils.encode(String.valueOf(id));
        String idEncoded = Uri.encode(id);
        return shareSongUrl + idEncoded;
    }
}
