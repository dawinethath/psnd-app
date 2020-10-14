package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserRolePrivilege extends BaseGson {

    public static final String EXTRA                  = "UserRolePrivilege";
    public static final String EXTRA_CREATE_USER_TYPE = "EXTRA_CREATE_USER_TYPE";
    public static final String UserType_normal        = "normal";
    public static final String UserType_vip           = "vip";

    @SerializedName("app_role_default")
    private UserRole defaultRole;

    @SerializedName("app_role_default_vip")
    private UserRole defaultRoleVip;

//    @SerializedName("app_privileges")
//    private List<UserPrivilege> privileges;

    @SerializedName("app_roles")
    private List<UserRole> roles;

    public UserRole getUserRoleVIP() {
        for (val role : roles) {
            if (role.getKeyName().toLowerCase().equals("vip")) {
                return role;
            }
        }
        return null;
    }

    public void removeUserRoleVip() {
        for (val role : roles) {
            if (role.getKeyName().toLowerCase().equals("vip")) {
                roles.remove(role);
                break;
            }
        }
    }
}
