package com.example.behrooz.homework;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  Button btn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //===========================

    CharListFragment charListFragment = new CharListFragment();
    WordListFragment wordListFragment = new WordListFragment();
    HeaderFragment headerFragment = new HeaderFragment();

    //===========================
    FragmentManager fragmentManager = getSupportFragmentManager();

    //===========================

    fragmentManager.beginTransaction().replace(R.id.container_char, charListFragment).commit();
    fragmentManager.beginTransaction().replace(R.id.container_list, wordListFragment).commit();
    fragmentManager.beginTransaction().replace(R.id.container_header, headerFragment).commit();
  }
}
