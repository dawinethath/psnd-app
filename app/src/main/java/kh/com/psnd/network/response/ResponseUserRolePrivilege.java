package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.UserRolePrivilege;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseUserRolePrivilege extends BaseResponse {

    @SerializedName("result")
    private UserRolePrivilege result;

}
