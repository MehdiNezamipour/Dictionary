package com.example.dictionary.repositories;

import android.content.Context;

import androidx.room.Room;

import com.example.dictionary.DataBase.DictionaryDataBase;
import com.example.dictionary.models.Word;

import java.util.List;

public class WordRepository {

    private static WordRepository sWordRepository;
    private static Context sContext;
    private DictionaryDataBase mDataBase;

    public static WordRepository getInstance(Context context) {
        sContext = context.getApplicationContext();
        if (sWordRepository == null)
            sWordRepository = new WordRepository();
        return sWordRepository;
    }

    public WordRepository() {
        mDataBase = Room.databaseBuilder(sContext, DictionaryDataBase.class, "dictionaryDB.db")
                .allowMainThreadQueries()
                .build();
    }

    public void insert(Word word) {
        mDataBase.getWordDAO().insert(word);
    }

    public void delete(Word word) {
        mDataBase.getWordDAO().delete(word);
    }

    public void update(Word word) {
        mDataBase.getWordDAO().update(word);
    }

    public Word get(Long id) {
        return mDataBase.getWordDAO().get(id);
    }

    public List<Word> getList() {
        return mDataBase.getWordDAO().getList();
    }

    public void deleteAll() {
        mDataBase.getWordDAO().deleteAll();
    }

    public List<Word> search(String search) {
        search = search + "%";
        return mDataBase.getWordDAO().search(search);

    }


}
