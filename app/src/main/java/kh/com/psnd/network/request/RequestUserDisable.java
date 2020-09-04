package kh.com.psnd.network.request;

import com.google.gson.annotations.SerializedName;

import core.lib.network.request.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestUserDisable extends BaseParam {

    @SerializedName("id")
    private String id;

    @SerializedName("active")
    private boolean active;

    @SerializedName("modified_by")
    private String modifiedBy;

}
