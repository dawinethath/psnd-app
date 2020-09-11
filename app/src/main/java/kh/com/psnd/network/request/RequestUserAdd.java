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
public class RequestUserAdd extends BaseParam {

    @SerializedName("staff_id")
    private int staff_id;

    @SerializedName("username")
    private String username;

    @SerializedName("key_name_role")
    private String keyRole;

    @SerializedName("privileges")
    private List<UserPrivilege> userPrivileges;

    public RequestUserAdd(int staff_id, String username) {
        this.staff_id = staff_id;
        this.username = username;
    }
}
