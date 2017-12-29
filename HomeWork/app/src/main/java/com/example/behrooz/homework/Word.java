package com.example.behrooz.homework;

import java.util.UUID;

/**
 * Created by Behrooz on 12/23/2017.
 */

public class Word implements Comparable<Word> {
  private String persianWord;
  private String englishWord;
  private UUID uuid ;

  public Word(UUID uuid) {
    this.uuid= uuid;
  }

  public String getPersianWord() {
    return persianWord;
  }

  public void setPersianWord(String persianWord) {
    this.persianWord = persianWord;
  }

  public String getEnglishWord() {
    return englishWord;
  }

  public void setEnglishWord(String englishWord) {
    this.englishWord = englishWord;
  }

  public UUID getUuid() {
    return uuid;
  }


  public Word(){
    this(UUID.randomUUID());
  }

  @Override
  public int compareTo( Word word) {

    return this.englishWord.compareTo(word.englishWord);

  }
}
