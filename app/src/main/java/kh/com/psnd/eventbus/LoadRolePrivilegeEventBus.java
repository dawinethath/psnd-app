package kh.com.psnd.eventbus;

import core.lib.network.model.BaseGson;
import kh.com.psnd.network.model.UserRolePrivilege;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoadRolePrivilegeEventBus extends BaseGson {
    private UserRolePrivilege rolePrivilege;
    private String            clazzName;
}
