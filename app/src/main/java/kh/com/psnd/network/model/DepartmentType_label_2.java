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
public class DepartmentType_label_2 extends BaseGson {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("dTypeID")
    public int departmentTypeId;

    @ColumnInfo(name = "type")
    @SerializedName("dType")
    public String departmentType;

    @ColumnInfo(name = "generalCommId")
    @SerializedName("gID")
    public int generalCommId;

    @Override
    public String toString() {
        return departmentType;
    }
}