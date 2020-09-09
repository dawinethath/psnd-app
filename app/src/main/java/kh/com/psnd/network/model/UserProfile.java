package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfile extends BaseGson {

    @SerializedName("id")
    private String id;

    @SerializedName("username")
    private String username;

    @SerializedName("role")
    private UserRole role;

    @SerializedName("privileges")
    private List<UserPrivilege> privileges;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("created_by")
    private String createBy;

    @SerializedName("modified_at")
    private String modifiedAt;

    @SerializedName("modified_by")
    private String modifiedBy;

    @SerializedName("active")
    private boolean active;

    @SerializedName("staff")
    private SearchStaff staff;

}
