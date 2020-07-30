package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import kh.com.psnd.network.model.DepartmentType_label_2;

@Dao
public interface DepartmentTypeDao_label_2 {

    @Query("SELECT * FROM DepartmentType_label_2 WHERE id=:id")
    DepartmentType_label_2 findAllById(int id);

    @Query("SELECT * FROM DepartmentType_label_2 WHERE id=:id")
    Single<DepartmentType_label_2> findAllById_Rx(int id);

    @Query("SELECT * FROM DepartmentType_label_2 WHERE generalCommId = :generalCommId")
    Flowable<List<DepartmentType_label_2>> findAllByGeneralCommId_Rx(int generalCommId);

    @Query("SELECT * FROM DepartmentType_label_2 WHERE generalCommId = :generalCommId")
    List<DepartmentType_label_2> findAllByGeneralCommId(int generalCommId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll_Rx(DepartmentType_label_2... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(DepartmentType_label_2... items);

    @Query("DELETE FROM DepartmentType_label_2")
    void deleteAll();
}
