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

    @SerializedName("Tokens")
    private Tokens tokens;

    public LoginProfile() {
    }

    public LoginProfile(@NonNull UserProfile userProfile) {
        setUserProfile(userProfile);
    }

    public LoginProfile(@NonNull String username, @NonNull String pwd, @Nullable Staff staff, @Nullable Tokens tokens) {
        setUsername(username);
        setPassword(pwd);
        this.tokens = tokens;
        if (staff == null) {
            setStaff(null);
        }
        else {
            setStaff(new SearchStaff(staff));
        }
    }

    public void setUserProfile(UserProfile userProfile) {
        // setPassword(userProfile.getPassword());
        setId(userProfile.getId());
        setUsername(userProfile.getUsername());
        setRole(userProfile.getRole());
        setPrivileges(userProfile.getPrivileges());
        setCreatedAt(userProfile.getCreatedAt());
        setCreateBy(userProfile.getCreateBy());
        setModifiedAt(userProfile.getModifiedAt());
        setModifiedBy(userProfile.getModifiedBy());
        setActive(userProfile.isActive());
        setStaff(userProfile.getStaff());
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
