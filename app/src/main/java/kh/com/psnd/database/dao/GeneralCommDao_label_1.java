package kh.com.psnd.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import kh.com.psnd.network.model.GeneralComm_label_1;

@Dao
public interface GeneralCommDao_label_1 {

    @Query("SELECT * FROM GeneralComm_label_1")
    Maybe<List<GeneralComm_label_1>> findAll_Rx();

    @Query("SELECT * FROM GeneralComm_label_1")
    List<GeneralComm_label_1> findAll();

    @Query("SELECT * FROM GeneralComm_label_1 WHERE id=:id")
    Single<GeneralComm_label_1> findAllById_Rx(int id);

    @Query("SELECT * FROM GeneralComm_label_1 WHERE id=:id")
    GeneralComm_label_1 findAllById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll_Rx(GeneralComm_label_1... items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(GeneralComm_label_1... items);

    @Query("DELETE FROM GeneralComm_label_1")
    void deleteAll();
}
