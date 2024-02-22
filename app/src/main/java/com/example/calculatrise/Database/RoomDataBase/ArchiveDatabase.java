package com.example.calculatrise.Database.RoomDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.calculatrise.Database.Archive;
import com.example.calculatrise.Database.Daos.ArchiveDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Archive.class},version = 1 , exportSchema = false)
public abstract class ArchiveDatabase extends RoomDatabase {

    public abstract ArchiveDao archiveDao();

    private static volatile ArchiveDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
   public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ArchiveDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ArchiveDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ArchiveDatabase.class, "archive_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
