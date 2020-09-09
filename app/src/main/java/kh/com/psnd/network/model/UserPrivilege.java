package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserPrivilege extends BaseGson {

    @SerializedName("key_name")
    private String keyName;

    @SerializedName("type")
    private String type;

    @SerializedName("name")
    private String name;

    public UserPrivilege clone() {
        return new UserPrivilege(keyName, type, name);
    }

    @Override
    public String toString() {
        return name;
    }
}
