package kh.com.psnd.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;

import kh.com.psnd.network.model.Staff;
import lombok.Data;

@Entity
@Data
public class DetailStaffEntity {

    public static final long INTERVAL_CHECK_NEW_DATA = 1_000 * 60 * 1; // 1mn

    @PrimaryKey
    public int staffId;

    @ColumnInfo(name = "json")
    public String json;

    @ColumnInfo(name = "recent")
    public long recent = System.currentTimeMillis();

    public DetailStaffEntity() {
    }

    public DetailStaffEntity(Staff staff) {
        setStaffId(staff.getStaffId());
        setJson(staff.toJson());
    }

    public Staff getStaff() {
        return new Gson().fromJson(json, Staff.class);
    }
}
