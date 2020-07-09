package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.OfficeName_label_5;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseOfficeName_Label_5 extends BaseResponse {

    @SerializedName("result")
    private List<OfficeName_label_5> result = new ArrayList<>();
}
