package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StaffHistory extends BaseGson {

    @SerializedName("rank")
    private List<StaffRecord> rank;
    @SerializedName("position")
    private List<StaffRecord> position;
    @SerializedName("department")
    private List<StaffRecord> department;

}
