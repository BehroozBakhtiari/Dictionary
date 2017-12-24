package com.example.behrooz.homework;

import java.util.UUID;

/**
 * Created by Behrooz on 12/23/2017.
 */

public class Word {
  private String persianWord;
  private String englishWord;
  private UUID uuid ;

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

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }
}
