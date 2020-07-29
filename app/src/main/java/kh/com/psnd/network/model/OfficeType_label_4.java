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
public class OfficeType_label_4 extends BaseGson {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("offTypeID")
    public int officeTypeId;

    @ColumnInfo(name = "type")
    @SerializedName("offType")
    public String officeName;

    @ColumnInfo(name = "departmentId")
    @SerializedName("departmentID")
    public int departmentId;

    @Override
    public String toString() {
        return officeName;
    }
}