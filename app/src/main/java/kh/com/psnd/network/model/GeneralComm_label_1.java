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
public class GeneralComm_label_1 extends BaseGson {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("gID")
    public int id;

    @ColumnInfo(name = "name")
    @SerializedName("gName")
    public String name;

    @ColumnInfo(name = "shortName")
    @SerializedName("gNameShort")
    public String shortName;

    @Override
    public String toString() {
        return name;
    }
}
