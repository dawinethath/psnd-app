package kh.com.psnd.network.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.request.BaseParam;
import kh.com.psnd.network.model.UserPrivilege;
import kh.com.psnd.network.model.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestUserAdd extends BaseParam {

    @SerializedName("staff_id")
    private int staff_id;

    @SerializedName("username")
    private String username;

    @SerializedName("key_name_role")
    private String roleName;

    @SerializedName("privileges")
    private List<UserPrivilege> privileges;

    @SerializedName("created_by")
    private String createdBy;

    public void setCreatedBy(UserProfile userProfile) {
        this.createdBy = userProfile.getId();
    }
}
