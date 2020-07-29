package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.OfficeName_label_5;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseOfficeName_Label_5 extends BaseResponse {

    @SerializedName("result")
    private List<OfficeName_label_5> result = new ArrayList<>();

    public OfficeName_label_5[] getResultArrays() {
        val arrays = new OfficeName_label_5[result.size()];
        for (val item : result) {
            arrays[result.indexOf(item)] = item;
        }
        return arrays;
    }
}
