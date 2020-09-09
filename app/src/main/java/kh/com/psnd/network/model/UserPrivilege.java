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

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    public UserPrivilege clone() {
        return new UserPrivilege(id, name, type);
    }

    @Override
    public String toString() {
        return name;
    }
}
