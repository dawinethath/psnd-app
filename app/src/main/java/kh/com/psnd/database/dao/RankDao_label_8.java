package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import kh.com.psnd.network.model.Rank_label_8;

@Dao
public interface RankDao_label_8 {
    @Query("SELECT * FROM Rank_label_8")
    List<Rank_label_8> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll_Rx(Rank_label_8... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Rank_label_8... items);
}
