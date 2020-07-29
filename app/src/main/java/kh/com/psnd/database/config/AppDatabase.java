package kh.com.psnd.database.config;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import core.lib.base.BaseApp;
import kh.com.psnd.database.dao.DepartmentDao_label_3;
import kh.com.psnd.database.dao.DepartmentTypeDao_label_2;
import kh.com.psnd.database.dao.DetailStaffDao;
import kh.com.psnd.database.dao.GeneralCommDao_label_1;
import kh.com.psnd.database.dao.OfficeNameDao_label_5;
import kh.com.psnd.database.dao.OfficeTypeDao_label_4;
import kh.com.psnd.database.dao.PositionDao_label_9;
import kh.com.psnd.database.dao.RankDao_label_8;
import kh.com.psnd.database.dao.SectorNameDao_label_7;
import kh.com.psnd.database.dao.SectorTypeDao_label_6;
import kh.com.psnd.database.entities.DetailStaffEntity;
import kh.com.psnd.network.model.DepartmentType_label_2;
import kh.com.psnd.network.model.Department_label_3;
import kh.com.psnd.network.model.GeneralComm_label_1;
import kh.com.psnd.network.model.OfficeName_label_5;
import kh.com.psnd.network.model.OfficeType_label_4;
import kh.com.psnd.network.model.Position_label_9;
import kh.com.psnd.network.model.Rank_label_8;
import kh.com.psnd.network.model.SectorName_label_7;
import kh.com.psnd.network.model.SectorType_label_6;

@Database(entities = {
        DetailStaffEntity.class,
        GeneralComm_label_1.class,
        DepartmentType_label_2.class,
        Department_label_3.class,
        OfficeType_label_4.class,
        OfficeName_label_5.class,
        SectorType_label_6.class,
        SectorName_label_7.class,
        Rank_label_8.class,
        Position_label_9.class},
        version = AppDatabase.DB_VERSION, exportSchema = true)
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

    public abstract GeneralCommDao_label_1 generalCommDao_label_1();

    public abstract DepartmentTypeDao_label_2 departmentTypeDao_label_2();

    public abstract DepartmentDao_label_3 departmentDao_label_3();

    public abstract OfficeTypeDao_label_4 officeTypeDao_label_4();

    public abstract OfficeNameDao_label_5 officeNameDao_label_5();

    public abstract SectorTypeDao_label_6 sectorTypeDao_label_6();

    public abstract SectorNameDao_label_7 sectorName_label_7();

    public abstract RankDao_label_8 rankDao_label_8();

    public abstract PositionDao_label_9 positionDao_label_9();

}
