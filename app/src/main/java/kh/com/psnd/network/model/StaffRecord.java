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

    @SerializedName("text")
    private String name;
    @SerializedName("announce_number")
    private String announceNumber;
    @SerializedName("date_ann")
    private String dateAnnounce;
    @SerializedName("pdfUrl")
    private String pdfUrl;
}
