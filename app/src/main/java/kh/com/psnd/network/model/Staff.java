package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Staff extends BaseGson {

    public static final String EXTRA = "Staff";

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

}
