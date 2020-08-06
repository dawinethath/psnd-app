package kh.com.psnd.network.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amazonaws.mobile.client.results.Tokens;
import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfile extends BaseGson {

    @SerializedName("username")
    private String username;
    @SerializedName("pwd")
    private String pwd;
    @SerializedName("Tokens")
    private Tokens tokens;
    @SerializedName("staff")
    private Staff  staff;

    public UserProfile() {
    }

    public UserProfile(@NonNull String username, @NonNull String pwd, @Nullable Staff staff, @Nullable Tokens tokens) {
        this.username = username;
        this.pwd = pwd;
        this.staff = staff;
        this.tokens = tokens;
    }

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
