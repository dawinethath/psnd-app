package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SectorName_label_7 extends BaseGson {

    @SerializedName("secID")
    private int    sectorId;
    @SerializedName("secName")
    private String sectorName;

    @Override
    public String toString() {
        return sectorName;
    }
}