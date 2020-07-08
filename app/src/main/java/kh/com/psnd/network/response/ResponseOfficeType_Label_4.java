package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.OfficeType_label_4;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseOfficeType_Label_4 extends BaseResponse {

    @SerializedName("result")
    private List<OfficeType_label_4> result;
}
