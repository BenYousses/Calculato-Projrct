package com.example.calculatrise.Database.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.example.calculatrise.Database.Archive;
import com.example.calculatrise.Database.Daos.ArchiveDao;
import com.example.calculatrise.Database.RoomDataBase.ArchiveDatabase;

import java.util.List;

public class MyRepository {
  private  ArchiveDao archiveDao ;

    public MyRepository(Application application) {
        ArchiveDatabase db = ArchiveDatabase.getDatabase(application);
        archiveDao = db.archiveDao();
    }
   public void insertArchive(Archive archive){
        ArchiveDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                archiveDao.insertArchive(archive);
            }
        });
    }
    public   LiveData<List<Archive>> getAllArchives(){
        return archiveDao.getAllArchives();
    }

    public    void deleteAllArchive(){
        ArchiveDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                archiveDao.deleteAllArchive();
            }
        });
    }

    public  void deleteArchiveById(Archive archive){
        ArchiveDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                archiveDao.deleteArchiveById(archive);
            }
        });
    }
}
