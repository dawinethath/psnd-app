package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserRolePrivilege extends BaseGson {

    @SerializedName("app_privileges")
    private List<UserPrivilege> privileges;

    @SerializedName("app_roles")
    private List<UserRole> roles;

}
