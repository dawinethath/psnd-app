package core.lib.network.response;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse extends BaseGson {

    private final String STATUS_OK        = "200";
    private final String STATUS_NOT_FOUND = "404";
    private final String STATUS_BAD       = "400";

    @SerializedName("$m")
    private String message;
    @SerializedName("$s")
    private String status;

    public boolean isRequestOK() {
        return status.equals(STATUS_OK);
    }

    public boolean isRequestBad() {
        return status.equals(STATUS_BAD);
    }

    public boolean isRequestNotFound() {
        return status.equals(STATUS_NOT_FOUND);
    }
}
