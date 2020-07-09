package kh.com.psnd.network.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.response.BaseResponse;
import kh.com.psnd.network.model.SectorName_label_7;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseSectionName_Label_7 extends BaseResponse {

    @SerializedName("result")
    private List<SectorName_label_7> result = new ArrayList<>();

}
