package com.example.dictionary.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dictionary.models.Word;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class DictionaryDataBase extends RoomDatabase {
    public abstract WordDAO getWordDAO();
}
