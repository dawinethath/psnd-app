package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.SectorName_label_7;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseSectionName_Label_7 extends BaseResponse {

    @SerializedName("result")
    private List<SectorName_label_7> result = new ArrayList<>();

    public SectorName_label_7[] getResultArrays() {
        val arrays = new SectorName_label_7[result.size()];
        for (val item : result) {
            arrays[result.indexOf(item)] = item;
        }
        return arrays;
    }
}
