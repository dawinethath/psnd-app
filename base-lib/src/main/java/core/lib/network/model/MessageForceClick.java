package core.lib.network.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class MessageForceClick extends BaseGson {
    @SerializedName("msg")
    private String msg;
}
