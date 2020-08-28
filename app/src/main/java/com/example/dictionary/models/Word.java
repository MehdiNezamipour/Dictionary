package com.example.dictionary.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wordTable")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "English")
    private String mEnglish;
    @ColumnInfo(name = "Persian")
    private String mPersian;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnglish() {
        return mEnglish;
    }

    public void setEnglish(String english) {
        mEnglish = english;
    }

    public String getPersian() {
        return mPersian;
    }

    public void setPersian(String persian) {
        mPersian = persian;
    }
}
