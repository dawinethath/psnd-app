package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.SectorType_label_6;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseSectorType_Label_6 extends BaseResponse {

    @SerializedName("result")
    private List<SectorType_label_6> result;
}
