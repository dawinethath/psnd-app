package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import kh.com.psnd.network.model.SectorType_label_6;

@Dao
public interface SectorTypeDao_label_6 {
    @Query("SELECT * FROM SectorType_label_6")
    List<SectorType_label_6> findAll();

    @Query("SELECT * FROM SectorType_label_6")
    Flowable<List<SectorType_label_6>> findAll_Rx();

    @Query("SELECT * FROM SectorType_label_6 WHERE id=:id")
    SectorType_label_6 findAllById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll_Rx(SectorType_label_6... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SectorType_label_6... items);

    @Query("DELETE FROM SectorType_label_6")
    void deleteAll();
}
