package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GeneralComm extends BaseGson {

    @SerializedName("gID")
    private int id;
    @SerializedName("gName")
    private String name;
    @SerializedName("gNameSh")
    private String shortName;
}
