package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import kh.com.psnd.network.model.OfficeType_label_4;

@Dao
public interface OfficeTypeDao_label_4 {

    @Query("SELECT * FROM OfficeType_label_4 WHERE departmentId = :departmentId")
    List<OfficeType_label_4> findAllByDepartmentId(int departmentId);

    @Query("SELECT * FROM OfficeType_label_4 WHERE departmentId = :departmentId")
    Flowable<List<OfficeType_label_4>> findAllByDepartmentId_Rx(int departmentId);

    @Query("SELECT * FROM OfficeType_label_4 WHERE id=:id")
    OfficeType_label_4 findAllById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll_Rx(OfficeType_label_4... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(OfficeType_label_4... items);

    @Query("DELETE FROM OfficeType_label_4")
    void deleteAll();
}
