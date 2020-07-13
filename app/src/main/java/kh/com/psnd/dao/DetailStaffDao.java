package kh.com.psnd.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface DetailStaffDao {
    @Query("SELECT * FROM DetailStaff")
    List<DetailStaff> getAll();

    @Query("SELECT * FROM DetailStaff WHERE staffId IN (:staffIds)")
    Flowable<List<DetailStaff>> loadAllByIds(int[] staffIds);

    @Query("SELECT * FROM DetailStaff WHERE staffId=:staffId")
    Single<DetailStaff> loadSingle(int staffId);

//    @Query("SELECT * FROM DetailStaff WHERE first_name LIKE :first AND " +
//           "last_name LIKE :last LIMIT 1")
//    DetailStaff findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(DetailStaff... users);

    @Delete
    public Single<Integer> delete(DetailStaff user);
}
