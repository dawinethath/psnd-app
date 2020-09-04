package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.UserProfile;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseUserAdd extends BaseResponse {

    @SerializedName("result")
    private UserProfile result;

}
