package com.example.calculatrise.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Archive implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
            @NonNull
  private  int id ;
    @ColumnInfo(name = "process")
    private  String process;
    @ColumnInfo(name = "equalSymbol")
    private String equalSymbol;
    @ColumnInfo(name = "Result")
    private  String Result ;

    public Archive(String process, String equalSymbol, String result) {
        this.process = process;
        this.equalSymbol = equalSymbol;
        Result = result;
    }

    public Archive() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getEqualSymbol() {
        return equalSymbol;
    }

    public void setEqualSymbol(String equalSymbol) {
        this.equalSymbol = equalSymbol;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }
}
