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
public class Department_label_3 extends BaseGson {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("dID")
    public int departmentId;

    @ColumnInfo(name = "name")
    @SerializedName("dName")
    public String departmentName;

    @ColumnInfo(name = "nameShort")
    @SerializedName("dNameShort")
    public String departmentNameShort;

    @ColumnInfo(name = "departmentTypeId")
    @SerializedName("dTypeID")
    public int departmentTypeId;

    @ColumnInfo(name = "generalCommId")
    @SerializedName("gID")
    public int generalCommId;

    @Override
    public String toString() {
        return departmentName;
    }
}