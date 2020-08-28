package kh.com.psnd.network.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import core.lib.base.BaseApp;
import core.lib.network.request.BaseParam;
import core.lib.utils.FileManager;
import kh.com.psnd.database.dao.SearchHistoryDao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.var;

@Data
@EqualsAndHashCode(callSuper = false)
public class StaffFilter extends BaseParam {

    private static final String CACHE_NAME = "SearchFilter";

    @SerializedName("general_id")
    private int                 generalId           = -1;
    @SerializedName("GeneralComm_label_1")
    private GeneralComm_label_1 generalComm_label_1 = null;

    @SerializedName("department_type_id")
    private int                    departmentTypeId       = -1;
    @SerializedName("DepartmentType_label_2")
    private DepartmentType_label_2 departmentType_label_2 = null;

    @SerializedName("department_id")
    private int                departmentId       = -1;
    @SerializedName("Department_label_3")
    private Department_label_3 department_label_3 = null;

    @SerializedName("office_type_id")
    private int                officeTypeId       = -1;
    @SerializedName("OfficeType_label_4")
    private OfficeType_label_4 officeType_label_4 = null;

    @SerializedName("office_id")
    private int                officeId           = -1;
    @SerializedName("OfficeName_label_5")
    private OfficeName_label_5 officeName_label_5 = null;

    @SerializedName("sector_type_id")
    private int                sectorTypeId       = -1;
    @SerializedName("SectorType_label_6")
    private SectorType_label_6 sectorType_label_6 = null;

    @SerializedName("sector_id")
    private int                sectorId           = -1;
    @SerializedName("SectorName_label_7")
    private SectorName_label_7 sectorName_label_7 = null;

    @SerializedName("rank_id")
    private int          rankId       = -1;
    @SerializedName("Rank_label_8")
    private Rank_label_8 rank_label_8 = null;

    @SerializedName("position_id")
    private int              positionId       = -1;
    @SerializedName("Position_label_9")
    private Position_label_9 position_label_9 = null;

    public void setGeneralComm_label_1(GeneralComm_label_1 generalComm) {
        this.generalId = generalComm.getId();
        this.generalComm_label_1 = generalComm;
    }

    public void setDepartmentType_label_2(DepartmentType_label_2 departmentType) {
        this.departmentTypeId = departmentType.getDepartmentTypeId();
        this.departmentType_label_2 = departmentType;
    }

    public void setDepartment_label_3(Department_label_3 department) {
        this.departmentId = department.getDepartmentId();
        this.department_label_3 = department;
    }

    public void setOfficeType_label_4(OfficeType_label_4 officeType) {
        this.officeTypeId = officeType.getOfficeTypeId();
        this.officeType_label_4 = officeType;
    }

    public void setOfficeName_label_5(OfficeName_label_5 officeName) {
        this.officeId = officeName.getOfficeId();
        this.officeName_label_5 = officeName;
    }

    public void setSectorType_label_6(SectorType_label_6 sectorType) {
        this.sectorTypeId = sectorType.getSectorTypeId();
        this.sectorType_label_6 = sectorType;
    }

    public void setSectorName_label_7(SectorName_label_7 sectorName) {
        this.sectorId = sectorName.getSectorId();
        this.sectorName_label_7 = sectorName;
    }

    public void setRank_label_8(Rank_label_8 rank) {
        this.rankId = rank.getId();
        this.rank_label_8 = rank;
    }

    public void setPosition_label_9(Position_label_9 position) {
        this.positionId = position.getId();
        this.position_label_9 = position;
    }

    public static StaffFilter getLastFilter() {
        try {
            String json   = FileManager.readTextFileInContext(BaseApp.context, CACHE_NAME);
            var    search = new Gson().fromJson(json, StaffFilter.class);
            return search;
        } catch (Exception e) {
        }
        return null;
    }

    public static void clearLastFilter() {
        FileManager.writeTextToFileInContext(BaseApp.context, CACHE_NAME, "");
    }

    public void saveLastFilter() {
        FileManager.writeTextToFileInContext(BaseApp.context, CACHE_NAME, toJson());
        SearchHistoryDao.addSearch(SearchHistoryDao.LAST_FILTER);
    }

    public String getLabelChip() {
        try {
            return "@" + getOfficeName_label_5().getOfficeNameShort();
        } catch (Exception e) {
            return null;
        }
    }
}