package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Medal extends BaseGson {

    @SerializedName("announce_number")
    private String announceNumber;

    @SerializedName("medal")
    private String medal;

    @SerializedName("medal_type")
    private String medalType;

    @SerializedName("by")
    private String by;

    public String getMedalDescription() {
        return medal + "\n(" + medalType + ")";
    }
}
