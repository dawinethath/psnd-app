package kh.com.psnd.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;

import kh.com.psnd.network.model.Search;
import kh.com.psnd.network.model.Staff;
import lombok.Data;
import lombok.val;

@Entity
@Data
public class DetailStaff {

    public static final long INTERVAL_CHECK_NEW_DATA = 1000 * 60 * 1; // 1mn

    @PrimaryKey
    public int staffId;

    @ColumnInfo(name = "json")
    public String json;

    @ColumnInfo(name = "recent")
    public long recent = System.currentTimeMillis();

    public static DetailStaff getInstance(Search search, Staff staff) {
        val detail = new DetailStaff();
        detail.setStaffId(search.getStaffId());
        detail.setJson(staff.toJson());
        return detail;
    }

    public Staff getStaff() {
        return new Gson().fromJson(json, Staff.class);
    }
}
