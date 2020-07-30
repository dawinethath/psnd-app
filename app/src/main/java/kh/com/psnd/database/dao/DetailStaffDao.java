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
    Flowable<List<DetailStaffEntity>> findAll();

    @Query("SELECT * FROM DetailStaffEntity WHERE staffId IN (:staffIds)")
    Flowable<List<DetailStaffEntity>> loadAllByIds_Rx(int[] staffIds);

    @Query("SELECT * FROM DetailStaffEntity WHERE staffId=:staffId")
    Single<DetailStaffEntity> loadSingle_Rx(int staffId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(DetailStaffEntity... items);

    @Delete
    Single<Integer> delete(DetailStaffEntity user);
}
