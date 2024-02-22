package com.example.calculatrise.Database.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.calculatrise.Database.Archive;

import java.util.List;

@Dao
public interface ArchiveDao {

    @Insert
    void insertArchive(Archive archive);
    @Query("Select * From Archive")
   LiveData< List<Archive>> getAllArchives();

    @Query("Delete  From Archive")
    void deleteAllArchive();

@Delete
void deleteArchiveById(Archive archive);
}
