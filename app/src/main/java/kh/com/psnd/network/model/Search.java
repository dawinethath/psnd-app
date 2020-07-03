package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Search extends BaseGson {
    public static final String EXTRA = "Search";

    @SerializedName("staffID")
    private int    staffId;
    @SerializedName("staffNumber")
    private String staffNumber;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("department")
    private String department;
    @SerializedName("photo")
    private String photo;

}
