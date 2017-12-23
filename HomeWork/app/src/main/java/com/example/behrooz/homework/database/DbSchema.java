package com.example.behrooz.homework.database;

/**
 * Created by Behrooz on 12/23/2017.
 */

public class DbSchema {

  public static final String NAME = "dictionary.db";
  public static final int VERSION = 1;

  public static final class DictionaryTable {
    public static final String NAME = "Dictionary";
    public static final class Cols {
      public static final String UUID = "UUID";
      public static final String PERSIAN_WORD = "Persian Word";
      public static final String ENGLISH_WORD = "English Word";
    }
  }
}
