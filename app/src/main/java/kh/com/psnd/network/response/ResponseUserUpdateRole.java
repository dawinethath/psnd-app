package kh.com.psnd.network.response;


import core.lib.network.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseUserUpdateRole extends BaseResponse {
    public boolean isSuccess() {
        return getStatusCode().equals("200");
    }
}
