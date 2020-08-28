package kh.com.psnd.network.request;

import com.google.gson.annotations.SerializedName;

import core.lib.network.request.BaseParam;
import kh.com.psnd.network.model.UserFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestSearchUser extends BaseParam {

    @SerializedName("search")
    private String     search;
    @SerializedName("filter")
    private UserFilter filter = null;
    @SerializedName("page")
    private int        page;

}
