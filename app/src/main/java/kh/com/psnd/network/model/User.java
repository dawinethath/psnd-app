package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends BaseGson {

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("key_name_role")
    private String role;

    @SerializedName("privileges")
    private List<UserPrivilege> privileges;

    @SerializedName("active")
    private boolean active;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("created_by")
    private String createdBy;

    @SerializedName("modified_at")
    private Date modifiedAt;

    @SerializedName("modified_by")
    private String modified_by;

    @SerializedName("staff")
    private Staff staff;

}
