package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.GeneralComm_label_1;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseGeneralComm_Label_1 extends BaseResponse {

    @SerializedName("result")
    private List<GeneralComm_label_1> result = new ArrayList<>();
}
