package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserRolePrivilege extends BaseGson {

    public static final String EXTRA = "UserRolePrivilege";

    @SerializedName("app_role_default")
    private UserRole defaultRole;

    @SerializedName("app_privileges")
    private List<UserPrivilege> privileges;

    @SerializedName("app_roles")
    private List<UserRole> roles;

}
