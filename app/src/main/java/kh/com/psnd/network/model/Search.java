package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.Data;

@Data
public class Search extends BaseGson {
    @SerializedName("search")
    private String search;
}
