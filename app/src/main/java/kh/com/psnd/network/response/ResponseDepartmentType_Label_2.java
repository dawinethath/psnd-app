package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.DepartmentType_label_2;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseDepartmentType_Label_2 extends BaseResponse {

    @SerializedName("result")
    private List<DepartmentType_label_2> result;
}
