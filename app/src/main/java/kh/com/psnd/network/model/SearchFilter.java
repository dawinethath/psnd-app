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

    @SerializedName("department_type_id")
    private int    departmentTypeId   = -1;
    @SerializedName("department_type_name")
    private String departmentTypeName = null;

    @SerializedName("department_id")
    private int    departmentId   = -1;
    @SerializedName("department_name")
    private String departmentName = null;

    @SerializedName("office_type_id")
    private int    officeTypeId   = -1;
    @SerializedName("office_type_name")
    private String officeTypeName = null;

    @SerializedName("office_id")
    private int    officeId   = -1;
    @SerializedName("office_name")
    private String officeName = null;

    @SerializedName("sector_type_id")
    private int    sectorTypeId   = -1;
    @SerializedName("sector_type_name")
    private String sectorTypeName = null;

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

    public void setGeneralComm_label_1(GeneralComm_label_1 generalComm) {
        setGeneralId(generalComm.getId());
        setGeneralName(generalComm.getName());
    }

    public void setDepartmentType_label_2(DepartmentType_label_2 departmentType) {
        setDepartmentTypeId(departmentType.getDepartmentTypeId());
        setDepartmentName(departmentType.getDepartmentType());
    }

    public void setDepartment_label_3(Department_label_3 department) {
        setDepartmentId(department.getDepartmentId());
        setDepartmentName(department.getDepartmentName());
    }

    public void setOfficeType_label_4(OfficeType_label_4 officeType) {
        setOfficeTypeId(officeType.getOfficeTypeId());
        setOfficeTypeName(officeType.getOfficeName());
    }

    public void setOfficeName_label_5(OfficeName_label_5 officeName) {
        setOfficeId(officeName.getOfficeId());
        setOfficeName(officeName.getOfficeName());
    }

    public void setSectorType_label_6(SectorType_label_6 sectorType) {
        setSectorTypeId(sectorType.getSectorTypeId());
        setSectorTypeName(sectorType.getSectorType());
    }

    public void setSectorName_label_7(SectorName_label_7 sectorName) {
        setSectorId(sectorName.getSectorId());
        setSectorName(sectorName.getSectorName());
    }

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