package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchStaff extends BaseGson {
    public static final String EXTRA          = "SearchStaff";
    public static final String EXTRA_LIST     = "SearchStaff_List";
    public static final String EXTRA_POSITION = "Position";

    @SerializedName("staffID")
    private int staffId;

    @SerializedName("staffNumber")
    private String staffNumber;

    @SerializedName("staffNumber_en")
    private String staffNumberEn;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("department")
    private String department;

    @SerializedName("photo")
    private String photo;

    @SerializedName("general_commissariat")
    private String generalCommissariat;

    @SerializedName("office")
    private String office;

    @SerializedName("position")
    private String position;

    public SearchStaff() {

    }

    public SearchStaff(Staff staff) {
        setStaffId(staff.getStaffId());
        setDepartment(staff.getDepartment());
        setFullName(staff.getFullName());
        setGeneralCommissariat(staff.getGeneralCommissariat());
        setPhoto(staff.getPhoto());
        setStaffNumber(staff.getId());
    }
}
