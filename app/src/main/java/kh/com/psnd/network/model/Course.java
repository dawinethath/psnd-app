package kh.com.psnd.network.model;

import com.google.gson.annotations.SerializedName;

import core.lib.base.BaseApp;
import core.lib.network.model.BaseGson;
import kh.com.psnd.R;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Course extends BaseGson {

    @SerializedName("course_name")
    private String name;

    @SerializedName("place")
    private String place;

    @SerializedName("course_start_date")
    private String date;

    @SerializedName("time_span")
    private String spendTime;

    @SerializedName("in_country")
    private String country;

    public String getDescription() {
        return BaseApp.context.getString(R.string.detail_label_due_country, date, spendTime, country);
    }
}
