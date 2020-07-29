package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.Position_label_9;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponsePosition_Label_9 extends BaseResponse {

    @SerializedName("result")
    private List<Position_label_9> result = new ArrayList<>();

    public Position_label_9[] getResultArrays() {
        val arrays = new Position_label_9[result.size()];
        for (val item : result) {
            arrays[result.indexOf(item)] = item;
        }
        return arrays;
    }
}
