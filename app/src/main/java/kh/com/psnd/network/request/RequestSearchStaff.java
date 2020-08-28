package kh.com.psnd.network.request;

import com.google.gson.annotations.SerializedName;

import core.lib.network.request.BaseParam;
import kh.com.psnd.network.model.StaffFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestSearchStaff extends BaseParam {

    @SerializedName("search")
    private String      search;
    @SerializedName("filter")
    private StaffFilter filter = null;
    @SerializedName("page")
    private int         page;

}
