package com.example.dictionary.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dictionary.models.Word;

import java.util.List;

@Dao
public interface WordDAO {

    @Insert
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Update
    void update(Word word);

    @Query("SELECT * FROM wordTable WHERE id=:id ")
    Word get(Long id);

    @Query("SELECT * FROM wordTable")
    List<Word> getList();

    @Query("DELETE FROM wordTable")
    void deleteAll();



}
