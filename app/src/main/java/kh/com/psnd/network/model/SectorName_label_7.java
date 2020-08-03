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
public class SectorName_label_7 extends BaseGson {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("secID")
    public int sectorId;

    @ColumnInfo(name = "name")
    @SerializedName("secName")
    public String sectorName;

    @ColumnInfo(name = "nameShort")
    @SerializedName("secNameShort")
    public String sectorNameShort;

    @ColumnInfo(name = "officeId")
    @SerializedName("offID")
    public int officeId;

    @ColumnInfo(name = "sectorTypeId")
    @SerializedName("secTypeID")
    public int sectorTypeId;

    @Override
    public String toString() {
        return sectorName;
    }
}