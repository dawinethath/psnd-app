package kh.com.psnd.network.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amazonaws.mobile.client.results.Tokens;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginProfile extends UserProfile {

    @SerializedName("pwd")
    private String pwd;
    @SerializedName("Tokens")
    private Tokens tokens;

    public LoginProfile() {
    }

    public LoginProfile(@NonNull String username, @NonNull String pwd, @Nullable Staff staff, @Nullable Tokens tokens) {
        setUsername(username);
        this.pwd = pwd;
        this.tokens = tokens;
        if (staff == null) {
            setStaff(null);
        }
        else {
            setStaff(new SearchStaff(staff));
        }
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
