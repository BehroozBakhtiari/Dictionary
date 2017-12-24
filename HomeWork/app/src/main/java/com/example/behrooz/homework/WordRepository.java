package com.example.behrooz.homework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.behrooz.homework.database.DictionaryBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Payami on 12/24/2017.
 */

public class WordRepository {
    private List<Word> words;
    private Context context;
    private SQLiteDatabase database;
    private static WordRepository wordRepository;

    public static WordRepository getInstance(Context context){
        if (wordRepository==null)
            wordRepository = new WordRepository(context);
        return wordRepository;
    }

    private WordRepository(Context context){
        words  = new ArrayList<>();
        this.context = context.getApplicationContext();
        database = new DictionaryBaseHelper(this.context).getWritableDatabase();
    }


    public Word getWord(UUID uuid){
        for (int i=0 ; i<words.size();i++){
            if (words.get(i).getUuid().equals(uuid))
                return words.get(i);
        }
        return null;
    }

    public int getPosition(UUID uuid){
        for (int i=0;i<words.size();i++){
            if (words.get(i).getUuid().equals(uuid))
            return i;
        }
        return 0;
    }

    public void addWord(Word word){
        if (!words.contains(word))
            words.add(word);
    }

    public void deleteWord(Word word){
        if (words.contains(word))
            words.remove(word);
    }

}
