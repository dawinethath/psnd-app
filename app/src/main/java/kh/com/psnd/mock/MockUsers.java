package kh.com.psnd.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kh.com.psnd.network.model.UserPrivilege;
import kh.com.psnd.network.model.UserRole;

public class MockUsers {

    private static final String PRIVILEGE_TYPE_FIELD            = "field";
    private static final String PRIVILEGE_TYPE_POLICY           = "policy";
    public static final  String ROLE_CUSTOM_ID                  = "custom";
    public static final  String PRIVILEGE_DEFAULT_BASIC_INFO_ID = "basic_info";
    public static final  String ROLE_DEFAULT_ID                 = "user";

    private static final UserPrivilege privilege_basic_info    = new UserPrivilege(PRIVILEGE_DEFAULT_BASIC_INFO_ID, "Basic Info", PRIVILEGE_TYPE_FIELD);
    private static final UserPrivilege privilege_date_of_birth = new UserPrivilege("date_of_birth", "Date of Birth", PRIVILEGE_TYPE_FIELD);
    private static final UserPrivilege privilege_phone         = new UserPrivilege("phone", "Phone", PRIVILEGE_TYPE_FIELD);
    private static final UserPrivilege privilege_history       = new UserPrivilege("history", "History", PRIVILEGE_TYPE_FIELD);
    private static final UserPrivilege privilege_add_user      = new UserPrivilege("add_user", "Add User", PRIVILEGE_TYPE_POLICY);
    private static final UserPrivilege privilege_edit_user     = new UserPrivilege("edit_user", "Edit User", PRIVILEGE_TYPE_POLICY);
    private static final UserPrivilege privilege_disable_user  = new UserPrivilege("disable_user", "Disable User", PRIVILEGE_TYPE_POLICY);

    public static final  UserRole userRole_user  = new UserRole("user", "User", 1, null).addPrivilege(privilege_basic_info.clone());
    private static final UserRole userRole_admin = new UserRole("admin", "Admin", 1, null)
            .addPrivilege(privilege_basic_info.clone())
            .addPrivilege(privilege_date_of_birth.clone())
            .addPrivilege(privilege_phone.clone())
            .addPrivilege(privilege_history.clone());

    private static final UserRole userRole_super_admin = new UserRole("super_admin", "Super Admin", 1, null)
            .addPrivilege(privilege_basic_info.clone())
            .addPrivilege(privilege_date_of_birth.clone())
            .addPrivilege(privilege_phone.clone())
            .addPrivilege(privilege_history.clone())
            .addPrivilege(privilege_add_user.clone())
            .addPrivilege(privilege_edit_user.clone())
            .addPrivilege(privilege_disable_user.clone());

    private static final UserRole userRole_custom = new UserRole(ROLE_CUSTOM_ID, "Custom", 1, null).addPrivilege(privilege_basic_info.clone());

    public static final List<UserPrivilege> userPrivileges = new ArrayList<UserPrivilege>(
            Arrays.asList(
                    privilege_basic_info.clone(),
                    privilege_date_of_birth.clone(),
                    privilege_phone.clone(),
                    privilege_history.clone(),
                    privilege_add_user.clone(),
                    privilege_edit_user.clone(),
                    privilege_disable_user.clone()
            )
    );

    public static final List<UserRole> userRoles = new ArrayList<UserRole>(
            Arrays.asList(
                    userRole_user.clone(),
                    userRole_admin.clone(),
                    userRole_super_admin.clone(),
                    userRole_custom.clone()
            )
    );

}
