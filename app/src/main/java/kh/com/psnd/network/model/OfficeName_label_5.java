package kh.com.psnd.network.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import core.lib.network.model.BaseGson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class OfficeName_label_5 extends BaseGson {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("offID")
    public int officeId;

    @ColumnInfo(name = "name")
    @SerializedName("offName")
    public String officeName;

    @ColumnInfo(name = "nameShort")
    @SerializedName("offNameShort")
    public String officeNameShort;

    @ColumnInfo(name = "departmentId")
    @SerializedName("dID")
    public int departmentId;

    @ColumnInfo(name = "officeTypeId")
    @SerializedName("offTypeID")
    public int officeTypeId;

    @Override
    public String toString() {
        return officeName;
    }
}