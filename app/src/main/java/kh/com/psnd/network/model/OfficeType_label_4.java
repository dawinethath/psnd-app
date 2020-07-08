package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OfficeType_label_4 extends BaseGson {

    @SerializedName("office_type_id")
    private int    officeTypeId;
    @SerializedName("office_type")
    private String officeName;

    @Override
    public String toString() {
        return officeName;
    }
}