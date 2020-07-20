package kh.com.psnd.network.response;


import core.lib.network.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseSignUpVerify extends BaseResponse {

//    @SerializedName("result")
//    private List<Search> result;

    public boolean isVerified() {
        return (getStatusCode() == 200);
    }

}
