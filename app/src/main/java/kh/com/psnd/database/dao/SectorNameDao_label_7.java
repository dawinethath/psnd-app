package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import kh.com.psnd.network.model.SectorName_label_7;

@Dao
public interface SectorNameDao_label_7 {

    @Query("SELECT * FROM SectorName_label_7")
    List<SectorName_label_7> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll_Rx(SectorName_label_7... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SectorName_label_7... items);

}
