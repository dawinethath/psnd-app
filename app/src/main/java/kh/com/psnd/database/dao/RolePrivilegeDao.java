package kh.com.psnd.database.dao;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.base.BaseApp;
import core.lib.network.model.BaseGson;
import core.lib.utils.FileManager;
import kh.com.psnd.network.model.UserRolePrivilege;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.var;

@Data
@EqualsAndHashCode(callSuper = false)
public class RolePrivilegeDao extends BaseGson {

    private static final String CACHE_NAME              = "RolePrivilegeDao";
    private static final long   INTERVAL_CHECK_NEW_DATA = 1_000 * 60 * 1; // 1mn

    @SerializedName("recent")
    public  long                    recent = System.currentTimeMillis();
    @SerializedName("UserRolePrivilege")
    private List<UserRolePrivilege> result;

    public static RolePrivilegeDao getCache() {
        try {
            String json        = FileManager.readTextFileInContext(BaseApp.context, CACHE_NAME);
            var    obj         = new Gson().fromJson(json, RolePrivilegeDao.class);
            long   currentTime = System.currentTimeMillis();
            if (currentTime - obj.getRecent() > RolePrivilegeDao.INTERVAL_CHECK_NEW_DATA) {
                // todo
            }
            return obj;
        } catch (Exception e) {
        }
        return new RolePrivilegeDao();
    }

    public static void clearCache() {
        FileManager.writeTextToFileInContext(BaseApp.context, CACHE_NAME, "");
    }

    public void saveToCache() {
        FileManager.writeTextToFileInContext(BaseApp.context, CACHE_NAME, toJson());
    }

}
