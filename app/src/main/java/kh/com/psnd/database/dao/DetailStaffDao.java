package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import kh.com.psnd.database.entities.DetailStaffEntity;

@Dao
public interface DetailStaffDao {
    @Query("SELECT * FROM DetailStaffEntity")
    List<DetailStaffEntity> getAll();

    @Query("SELECT * FROM DetailStaffEntity WHERE staffId IN (:staffIds)")
    Flowable<List<DetailStaffEntity>> loadAllByIds(int[] staffIds);

    @Query("SELECT * FROM DetailStaffEntity WHERE staffId=:staffId")
    Single<DetailStaffEntity> loadSingle(int staffId);

//    @Query("SELECT * FROM DetailStaff WHERE first_name LIKE :first AND " +
//           "last_name LIKE :last LIMIT 1")
//    DetailStaff findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(DetailStaffEntity... items);

    @Delete
    public Single<Integer> delete(DetailStaffEntity user);
}
