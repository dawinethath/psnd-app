package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OfficeName_label_5 extends BaseGson {

    @SerializedName("offID")
    private int    officeId;
    @SerializedName("offName")
    private String officeName;

    @Override
    public String toString() {
        return officeName;
    }
}