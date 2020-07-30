package kh.com.psnd.network.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import core.lib.base.BaseApp;
import core.lib.network.request.BaseParam;
import core.lib.utils.FileManager;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.var;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchFilter extends BaseParam {

    private static final String CACHE_NAME = "SearchFilter";

    @SerializedName("general_id")
    private int    generalId   = -1;
    @SerializedName("general_name")
    private String generalName = null;

    @SerializedName("department_id")
    private int    departmentId   = -1;
    @SerializedName("department_name")
    private String departmentName = null;

    @SerializedName("office_id")
    private int    officeId   = -1;
    @SerializedName("office_name")
    private String officeName = null;

    @SerializedName("sector_id")
    private int    sectorId   = -1;
    @SerializedName("sector_name")
    private String sectorName = null;

    @SerializedName("rank_id")
    private int    rankId   = -1;
    @SerializedName("rank_name")
    private String rankName = null;

    @SerializedName("position_id")
    private int    positionId   = -1;
    @SerializedName("position_name")
    private String positionName = null;

    public static SearchFilter getLastFilter() {
        try {
            String json   = FileManager.readTextFileInContext(BaseApp.context, CACHE_NAME);
            var    search = new Gson().fromJson(json, SearchFilter.class);
            return search;
        } catch (Exception e) {
        }
        return null;
    }

    public void saveLastFilter() {
        FileManager.writeTextToFileInContext(BaseApp.context, CACHE_NAME, toJson());
    }
}