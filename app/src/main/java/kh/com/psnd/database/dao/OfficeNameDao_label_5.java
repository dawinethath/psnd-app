package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import kh.com.psnd.network.model.OfficeName_label_5;

@Dao
public interface OfficeNameDao_label_5 {

    @Query("SELECT * FROM OfficeName_label_5 WHERE departmentId = :departmentId AND officeTypeId = :officeTypeId")
    List<OfficeName_label_5> findAllDepartmentIdAndOfficeTypeId(int departmentId, int officeTypeId);

    @Query("SELECT * FROM OfficeName_label_5 WHERE departmentId = :departmentId AND officeTypeId = :officeTypeId")
    Flowable<List<OfficeName_label_5>> findAllDepartmentIdAndOfficeTypeId_Rx(int departmentId, int officeTypeId);

    @Query("SELECT * FROM OfficeName_label_5 WHERE id=:id")
    OfficeName_label_5 findAllById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll_Rx(OfficeName_label_5... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(OfficeName_label_5... items);

    @Query("DELETE FROM OfficeName_label_5")
    void deleteAll();
}
