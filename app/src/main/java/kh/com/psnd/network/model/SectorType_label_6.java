package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SectorType_label_6 extends BaseGson {

    @SerializedName("secTypeID")
    private int    sectorTypeId;
    @SerializedName("sector_type")
    private String sectorType;

    @Override
    public String toString() {
        return sectorType;
    }
}