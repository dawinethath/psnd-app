package kh.com.psnd.network.request;

import com.google.gson.annotations.SerializedName;

import core.lib.network.request.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestSignUpVerify extends BaseParam {

    @SerializedName("staff_number")
    private String staffNumber;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;


}
