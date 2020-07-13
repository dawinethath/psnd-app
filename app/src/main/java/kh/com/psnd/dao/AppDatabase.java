package kh.com.psnd.dao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import core.lib.base.BaseApp;

@Database(entities = {DetailStaff.class}, version = AppDatabase.DB_VERSION)
public abstract class AppDatabase extends RoomDatabase {

    public static final String      DB_NAME    = "database-psnd";
    public static final int         DB_VERSION = 1;
    private static      AppDatabase instance   = null;

    private static final int             NUMBER_OF_THREADS     = 4;
    private static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized AppDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(BaseApp.context, AppDatabase.class, DB_NAME).build();
        }
        return instance;
    }

    public abstract DetailStaffDao detailStaffDao();
}
