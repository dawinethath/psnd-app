package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import kh.com.psnd.network.model.Position_label_9;

@Dao
public interface PositionDao_label_9 {
    @Query("SELECT * FROM Position_label_9")
    List<Position_label_9> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll_Rx(Position_label_9... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Position_label_9... items);
}
