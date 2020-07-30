package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import kh.com.psnd.network.model.Rank_label_8;

@Dao
public interface RankDao_label_8 {

    @Query("SELECT * FROM Rank_label_8")
    List<Rank_label_8> findAll();

    @Query("SELECT * FROM Rank_label_8")
    Flowable<List<Rank_label_8>> findAll_Rx();

    @Query("SELECT * FROM Rank_label_8 WHERE id=:id")
    Rank_label_8 findAllById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll_Rx(Rank_label_8... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Rank_label_8... items);

    @Query("DELETE FROM Rank_label_8")
    void deleteAll();
}
