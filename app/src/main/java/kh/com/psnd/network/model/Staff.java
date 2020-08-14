package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@EqualsAndHashCode(callSuper = false)
public class Staff extends BaseGson {

    public static final String EXTRA = "Staff";

    @SerializedName("staffID")
    private int          staffId;
    @SerializedName("uid")
    private String       uid;
    @SerializedName("id")
    private String       id;
    @SerializedName("id_en")
    private String       idEn;
    @SerializedName("fullName")
    private String       fullName;
    @SerializedName("photo")
    private String       photo;
    @SerializedName("album_photo")
    private List<String> album;
    @SerializedName("dob")
    private String       dateOfBirth;
    @SerializedName("sex")
    private String       sex;
    @SerializedName("education")
    private String       education;
    @SerializedName("skill")
    private String       skill;
    @SerializedName("tel")
    private String       telephone;
    @SerializedName("addr")
    private String       address;
    @SerializedName("general_commissariat")
    private String       generalCommissariat;
    @SerializedName("department")
    private String       department;
    @SerializedName("office")
    private String       office;
    @SerializedName("sector")
    private String       sector;
    @SerializedName("rank")
    private String       rank;
    @SerializedName("position")
    private String       position;
    @SerializedName("status")
    private String       status;
    @SerializedName("statusColor")
    private String       statusColor;
    @SerializedName("staHistories")
    private StaffHistory staffHistories;
    @SerializedName("course")
    private List<Course> courses;
    @SerializedName("medals")
    private List<Medal>  medals;

    public static Staff getStaffTmp(SearchStaff searchStaff) {
        val staffTmp = new Staff();
        staffTmp.setGeneralCommissariat(searchStaff.getGeneralCommissariat());
        staffTmp.setPhoto(searchStaff.getPhoto());
        staffTmp.setFullName(searchStaff.getFullName());
        staffTmp.setId(searchStaff.getStaffNumber());
        staffTmp.setDepartment(searchStaff.getDepartment());
        staffTmp.setOffice(searchStaff.getOffice());
        staffTmp.setPosition(searchStaff.getPosition());
        return staffTmp;
    }

    public List<StaffRecord> getCourseRecord() {
        List<StaffRecord> list = new ArrayList<>();
        if (courses != null && courses.size() > 0) {
            for (val course : courses) {
                list.add(new StaffRecord(course.getName(), course.getPlace(), course.getDescription(), ""));
            }
        }
        return list;
    }

    public List<StaffRecord> getMedalRecord() {
        List<StaffRecord> list = new ArrayList<>();
        if (medals != null && medals.size() > 0) {
            for (val medal : medals) {
                list.add(new StaffRecord(medal.getMedalDescription(), medal.getAnnounceNumber(), medal.getBy(), ""));
            }
        }
        return list;
    }
}
