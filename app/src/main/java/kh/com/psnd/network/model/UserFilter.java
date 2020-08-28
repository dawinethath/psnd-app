package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.request.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserFilter extends BaseParam {

    @SerializedName("key_name_role")
    private String roleName;

}