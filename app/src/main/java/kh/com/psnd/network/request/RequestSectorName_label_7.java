package kh.com.psnd.network.request;

import com.google.gson.annotations.SerializedName;

import core.lib.network.request.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestSectorName_label_7 extends BaseParam {

    @SerializedName("offID")
    private int officeId;

    @SerializedName("secTypeID")
    private int sectorTypeId;

}
