package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.Department_label_3;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseDepartment_Label_3 extends BaseResponse {

    @SerializedName("result")
    private List<Department_label_3> result;
}
