package com.example.behrooz.homework.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Behrooz on 12/23/2017.
 */

public class DictionaryBaseHelper extends SQLiteOpenHelper {

  public DictionaryBaseHelper(Context context) {
    super(context, DbSchema.NAME, null, DbSchema.VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {

    db.execSQL("create table " + DbSchema.NAME + "(" +
      "_id integer primary key autoincrement," +
      DbSchema.DictionaryTable.Cols.UUID + "," +
      DbSchema.DictionaryTable.Cols.PERSIAN_WORD + "," +
      DbSchema.DictionaryTable.Cols.ENGLISH_WORD +
      ")");

  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

  }
}
