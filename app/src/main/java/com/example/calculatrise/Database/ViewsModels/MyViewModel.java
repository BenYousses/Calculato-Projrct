package com.example.calculatrise.Database.ViewsModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.calculatrise.Database.Archive;
import com.example.calculatrise.Database.Repository.MyRepository;
import java.util.List;

public class MyViewModel extends AndroidViewModel {
    MyRepository myRepository;
    public MyViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
    }

   public void insertArchive(Archive archive){
        myRepository.insertArchive(archive);
    }
    public LiveData<List<Archive>> getAllArchives(){
        return myRepository.getAllArchives();
    }

    public void deleteAllArchive(){
        myRepository.deleteAllArchive();
    }

    public  void deleteArchiveById(Archive archive){
                myRepository.deleteArchiveById(archive);
    }
}
