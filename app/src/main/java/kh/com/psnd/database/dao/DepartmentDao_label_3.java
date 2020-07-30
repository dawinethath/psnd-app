package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import kh.com.psnd.network.model.Department_label_3;

@Dao
public interface DepartmentDao_label_3 {

    @Query("SELECT * FROM Department_label_3 WHERE departmentTypeId = :departmentTypeId AND generalCommId=:generalCommId")
    Flowable<List<Department_label_3>> findAllByDepartmentTypeIdAndGeneralId_Rx(int generalCommId, int departmentTypeId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll_Rx(Department_label_3... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Department_label_3... items);

    @Query("DELETE FROM Department_label_3")
    void deleteAll();
}
