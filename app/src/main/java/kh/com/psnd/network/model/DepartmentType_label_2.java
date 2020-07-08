package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DepartmentType_label_2 extends BaseGson {

    @SerializedName("dTypeID")
    private int    departmentTypeId;
    @SerializedName("dType")
    private String departmentType;

    @Override
    public String toString() {
        return departmentType;
    }
}