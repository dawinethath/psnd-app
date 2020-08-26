package kh.com.psnd.network.model;

import android.content.Context;
import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.lib.base.BaseApp;
import core.lib.network.model.BaseGson;
import kh.com.psnd.R;
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

    public List<String> getAlbum() {
        val items = (album == null || album.size() == 0) ? Arrays.asList(photo) : album;
        return items;
    }

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

    public String getTextSharing() {
        val context = BaseApp.context;
        val builder = new StringBuilder();
        builder.append(context.getString(R.string.detail_full_name)).append("  :  ").append(getFullName()).append("\n\n");
        builder.append(context.getString(R.string.staff_id)).append("  :  ").append(getId()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_1)).append("  :  ").append(getRank()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_2)).append("  :  ").append(getPosition()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_0)).append("  :  ").append(getGeneralCommissariat()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_3)).append("  :  ").append(getDepartment()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_4)).append("  :  ").append(getOffice()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_5)).append("  :  ").append(getSector()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_6)).append("  :  ").append(getStatus()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_7)).append("  :  ").append(getSex()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_8)).append("  :  ").append(getDateOfBirth()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_9)).append("  :  ").append(getEducation()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_10)).append("  :  ").append(getSkill()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_11)).append("  :  ").append(getTelephone()).append("\n\n");
        builder.append(context.getString(R.string.detail_label_12)).append("  :  ").append(getAddress()).append("\n\n");
        return builder.toString();
    }

    public void doShare(Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getTextSharing());
        sendIntent.putExtra(Intent.EXTRA_TITLE, context.getString(R.string.detail_staff_info, getFullName()));
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        context.startActivity(shareIntent);
    }
}
