package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StaffRecord extends BaseGson {

    @SerializedName("ann_number")
    private String announceNumber;
    @SerializedName("date_ann")
    private String dateAnnounce;
    @SerializedName("rank")
    private String rank;
    @SerializedName("position")
    private String position;
    @SerializedName("department")
    private String department;
    @SerializedName("announce_type")
    private String announceType;
    @SerializedName("pdfUrl")
    private String pdfUrl;

    public String getAnnounceLabel() {
        return announceType + " " + announceNumber;
    }
}
