package com.example.behrooz.homework;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class CharListFragment extends Fragment {

  RecyclerView recyclerView;
  private CharAdapter charAdapter;
  private class CharViewHolder extends RecyclerView.ViewHolder {

    Character character;
    private TextView tvWord;
    private TextView tvChar;

    public CharViewHolder(View itemView) {
      super(itemView);

      tvChar = (TextView) itemView.findViewById(R.id.tv_char_detail);


    }

    public void setUI(Character character) {
      this.character = character;

      tvChar.setText(character);
    }

  }


  private class CharAdapter extends RecyclerView.Adapter<CharViewHolder> {
    private final ArrayList chars ;

    public CharAdapter(ArrayList chars) {
      this.chars = chars;
    }


    @Override
    public CharViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(getActivity()).inflate(R.layout.char_list_layout, parent, false);
      CharViewHolder charViewHolder = new CharViewHolder (view);
      return charViewHolder;
    }



    @Override
    public void onBindViewHolder(CharViewHolder holder, int position) {
      Character character = (Character) chars.get(position);
      holder.setUI(character);

    }

    @Override
    public int getItemCount() {
      if (chars == null)
        return 0;
      return chars.size();
    }
  }



  public CharListFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view =  inflater.inflate(R.layout.fragment_char_list, container, false);
    recyclerView = (RecyclerView) view.findViewById(R.id.char_recyclerview);
    updateUI();
    return view;
  }
  public void updateUI() {
    ArrayList chars = WordRepository.getInstance(getActivity()).getChars();

    charAdapter = new CharListFragment.CharAdapter(chars);
    recyclerView.setAdapter(charAdapter);
    charAdapter.notifyDataSetChanged();
    Collections.sort(chars);


  }

}
