package kh.com.psnd.network.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.request.BaseParam;
import kh.com.psnd.network.model.UserPrivilege;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestUserUpdateRole extends BaseParam {

    @SerializedName("id")
    private String id;

    @SerializedName("key_name_role")
    private String roleName;

    @SerializedName("privileges")
    private List<UserPrivilege> privileges;

    @SerializedName("modified_by")
    private String modifiedBy;
}
