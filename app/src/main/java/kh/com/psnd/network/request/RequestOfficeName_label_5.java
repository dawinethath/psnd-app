package kh.com.psnd.network.request;

import com.google.gson.annotations.SerializedName;

import core.lib.network.request.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestOfficeName_label_5 extends BaseParam {

    @SerializedName("dID")
    private int departmentId;

    @SerializedName("offTypeID")
    private int officeTypeId;

}
