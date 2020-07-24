package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserRole extends BaseGson {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("privileges")
    private List<UserPrivilege> privileges;

    public UserRole clone() {
        val role = new UserRole(id, name, new ArrayList<>());
        if (privileges != null) {
            for (val item : privileges) {
                role.addPrivilege(item);
            }
        }
        return role;
    }

    public UserRole addPrivilege(UserPrivilege privilege) {
        if (privileges == null) {
            privileges = new ArrayList<>();
        }
        boolean exist = false;
        for (val item : privileges) {
            if (item.getId() == privilege.getId()) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            privileges.add(privilege);
        }
        return this;
    }

    public void removePrivilege(UserPrivilege privilege) {
        if (privilege != null) {
            for (int i = 0; i < privileges.size(); i++) {
                if (privileges.get(i).getId() == privilege.getId()) {
                    privileges.remove(i);
                    break;
                }
            }
        }
    }
}
