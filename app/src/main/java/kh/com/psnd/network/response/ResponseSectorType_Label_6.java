package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.SectorType_label_6;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseSectorType_Label_6 extends BaseResponse {

    @SerializedName("result")
    private List<SectorType_label_6> result = new ArrayList<>();

    public SectorType_label_6[] getResultArrays() {
        val arrays = new SectorType_label_6[result.size()];
        for (val item : result) {
            arrays[result.indexOf(item)] = item;
        }
        return arrays;
    }
}
