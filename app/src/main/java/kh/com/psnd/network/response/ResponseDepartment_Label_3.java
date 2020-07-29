package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.Department_label_3;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseDepartment_Label_3 extends BaseResponse {

    @SerializedName("result")
    private List<Department_label_3> result = new ArrayList<>();

    public Department_label_3[] getResultArrays() {
        val arrays = new Department_label_3[result.size()];
        for (val item : result) {
            arrays[result.indexOf(item)] = item;
        }
        return arrays;
    }
}
