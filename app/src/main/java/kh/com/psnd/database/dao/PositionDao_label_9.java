package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import kh.com.psnd.network.model.Position_label_9;

@Dao
public interface PositionDao_label_9 {

    @Query("SELECT * FROM Position_label_9")
    List<Position_label_9> findAll();

    @Query("SELECT * FROM Position_label_9")
    Flowable<List<Position_label_9>> findAll_Rx();

    @Query("SELECT * FROM Position_label_9 WHERE id=:id")
    Position_label_9 findAllById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll_Rx(Position_label_9... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Position_label_9... items);

    @Query("DELETE FROM Position_label_9")
    void deleteAll();
}
