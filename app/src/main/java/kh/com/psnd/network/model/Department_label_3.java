package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Department_label_3 extends BaseGson {

    @SerializedName("dID")
    private int    departmentId;
    @SerializedName("gID")
    private int    generalId;
    @SerializedName("dTypeID")
    private int    departmentTypeId;
    @SerializedName("department name")
    private String departmentName;

    @Override
    public String toString() {
        return departmentName;
    }
}