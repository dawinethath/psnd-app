package kh.com.psnd.network.request;

import com.google.gson.annotations.SerializedName;

import core.lib.network.request.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestLogin extends BaseParam {

    @SerializedName("username")
    private String username;

    @SerializedName("pwd")
    private String pwd;

}
