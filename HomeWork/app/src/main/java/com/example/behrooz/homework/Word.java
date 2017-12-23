package com.example.behrooz.homework;

import java.util.UUID;

/**
 * Created by Behrooz on 12/23/2017.
 */

public class Word {
  private String persian_Word ;
  private String English_Word ;
  private UUID uuid ;

  public String getPersian_Word() {
    return persian_Word;
  }

  public void setPersian_Word(String persian_Word) {
    this.persian_Word = persian_Word;
  }

  public String getEnglish_Word() {
    return English_Word;
  }

  public void setEnglish_Word(String english_Word) {
    English_Word = english_Word;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }
}
