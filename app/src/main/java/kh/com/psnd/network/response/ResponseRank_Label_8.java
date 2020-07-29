package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.Rank_label_8;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseRank_Label_8 extends BaseResponse {

    @SerializedName("result")
    private List<Rank_label_8> result = new ArrayList<>();

    public Rank_label_8[] getResultArrays() {
        val arrays = new Rank_label_8[result.size()];
        for (val item : result) {
            arrays[result.indexOf(item)] = item;
        }
        return arrays;
    }
}
