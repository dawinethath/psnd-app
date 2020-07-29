package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.GeneralComm_label_1;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseGeneralComm_Label_1 extends BaseResponse {

    @SerializedName("result")
    private List<GeneralComm_label_1> result = new ArrayList<>();

    public GeneralComm_label_1[] getResultArrays() {
        val arrays = new GeneralComm_label_1[result.size()];
        for (val item : result) {
            arrays[result.indexOf(item)] = item;
        }
        return arrays;
    }
}
