package com.example.behrooz.homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.behrooz.homework.database.DbSchema;
import com.example.behrooz.homework.database.DictionaryBaseHelper;
import com.example.behrooz.homework.database.WordCursorWrapper;

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

  public static WordRepository getInstance(Context context) {
    if (wordRepository == null)
      wordRepository = new WordRepository(context);
    return wordRepository;
  }

  private WordRepository(Context context) {
    words = new ArrayList<>();
    this.context = context.getApplicationContext();
    database = new DictionaryBaseHelper(this.context).getWritableDatabase();
  }


  public List<Word> getWords() {
    return words;
  }

  public Word getWord(UUID uuid) {
    for (int i = 0; i < words.size(); i++) {
      if (words.get(i).getUuid().equals(uuid))
        return words.get(i);
    }
    return null;
  }

  public int getPosition(UUID uuid) {
    for (int i = 0; i < words.size(); i++) {
      if (words.get(i).getUuid().equals(uuid))
        return i;
    }
    return 0;
  }

  public void addWord(Word word) {
    if (!words.contains(word))
      words.add(word);
    ContentValues contentValues = getContentValues(word);
    database.insert(DbSchema.DictionaryTable.NAME, null, contentValues);

  }

  //////add word void ??????
  public void deleteWord(Word word) {
    if (words.contains(word))
      words.remove(word);
  }

  public void updateWord(Word newWord) {
    ContentValues contentValues = getContentValues(newWord);
    database.update(DbSchema.DictionaryTable.NAME,
      contentValues,
      DbSchema.DictionaryTable.Cols.UUID + " = ?",
      new String[]{newWord.getUuid().toString()});
  }


  public ContentValues getContentValues(Word word) {
    ContentValues contentValues = new ContentValues();

    contentValues.put(DbSchema.DictionaryTable.Cols.UUID, word.getUuid().toString());
    contentValues.put(DbSchema.DictionaryTable.Cols.PERSIAN_WORD, word.getPersianWord());
    contentValues.put(DbSchema.DictionaryTable.Cols.ENGLISH_WORD, word.getEnglishWord());
    return contentValues;
  }

  private WordCursorWrapper queryWords(String whereClause, String[] whereArgs) {
    Cursor cursor = database.query(DbSchema.DictionaryTable.NAME, null, whereClause, whereArgs, null, null, null);
    return new WordCursorWrapper(cursor);
  }

}
