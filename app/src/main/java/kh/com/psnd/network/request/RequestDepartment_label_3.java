package kh.com.psnd.network.request;

import com.google.gson.annotations.SerializedName;

import core.lib.network.request.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestDepartment_label_3 extends BaseParam {

    @SerializedName("gID")
    private int    id;
    @SerializedName("dTypeID")
    private int    departmentTypeId;

}
