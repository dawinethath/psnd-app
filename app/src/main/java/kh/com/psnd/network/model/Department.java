package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.Data;

@Data
public class Department extends BaseGson {

    @SerializedName("id")
    private String id;
    @SerializedName("label")
    private String label;
    @SerializedName("child")
    private Child child;

    @Data
    public class Child extends BaseGson {
        @SerializedName("id")
        private String id;
        @SerializedName("label")
        private String label;
    }
}
