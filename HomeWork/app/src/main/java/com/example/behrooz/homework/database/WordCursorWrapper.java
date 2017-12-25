package com.example.behrooz.homework.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.behrooz.homework.Word;

import java.util.UUID;

/**
 * Created by Behrooz on 12/25/2017.
 */

public class WordCursorWrapper extends CursorWrapper {

  public WordCursorWrapper(Cursor cursor) {
    super(cursor);
  }
  public Word getWord() {
    String uuidString = getString(getColumnIndex(DbSchema.DictionaryTable.Cols.UUID));
    String persianWord = getString(getColumnIndex(DbSchema.DictionaryTable.Cols.PERSIAN_WORD));
    String englishWord = getString(getColumnIndex(DbSchema.DictionaryTable.Cols.ENGLISH_WORD));

    Word word = new Word(UUID.fromString(uuidString));
    word.setEnglishWord(englishWord);
    word.setPersianWord(persianWord);

    return word;
  }
}
