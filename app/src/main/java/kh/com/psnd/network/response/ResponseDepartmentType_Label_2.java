package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.DepartmentType_label_2;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseDepartmentType_Label_2 extends BaseResponse {

    @SerializedName("result")
    private List<DepartmentType_label_2> result = new ArrayList<>();

    public DepartmentType_label_2[] getResultArrays() {
        val arrays = new DepartmentType_label_2[result.size()];
        for (val item : result) {
            arrays[result.indexOf(item)] = item;
        }
        return arrays;
    }
}
