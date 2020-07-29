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
public class Rank_label_8 extends BaseGson {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("rank_id")
    public int id;

    @ColumnInfo(name = "name")
    @SerializedName("rank_name")
    public String name;

    @ColumnInfo(name = "nameShort")
    @SerializedName("rank_name_short")
    public String nameShort;

    @Override
    public String toString() {
        return name;
    }
}