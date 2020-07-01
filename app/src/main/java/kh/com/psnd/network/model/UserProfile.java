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

    // Security
    @SerializedName("enabledFingerprint")
    private boolean enabledFingerprint;
    /**
     * <string-array name="autologout">
     * <item>@string/second_30</item>
     * <item>@string/minute_1</item>
     * <item>@string/minute_2</item>
     * <item>@string/minute_3</item>
     * </string-array>
     */
    @SerializedName("autoLogout")
    private int     autoLogout = 0; // store position of arrays.xml
    /**
     * <string-array name="languages">
     * <item>@string/language_khmer</item>
     * <item>@string/language_english</item>
     * </string-array>
     */
    @SerializedName("language")
    private int     language   = 0; // store position of arrays.xml
    @SerializedName("timeLeave")
    private long    timeLeave  = 0;
}
