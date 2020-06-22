package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ItemDetail extends BaseGson {

    @SerializedName("label")
    private String label;
    @SerializedName("docNumber")
    private String docNumber;
    @SerializedName("date")
    private String date;
}
