package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.Staff;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseStaff extends BaseResponse {

    @SerializedName("result")
    private Staff result;

}
