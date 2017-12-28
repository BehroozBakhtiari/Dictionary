package com.example.behrooz.homework;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordListFragment extends Fragment {

    public static final String ADD_DIALOG_TAG ="add_dialog_tag" ;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private WordAdapter wordAdapter;



  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setHasOptionsMenu(true);
  }

    private class WordHolder extends RecyclerView.ViewHolder {

        private Word word;
        private TextView tvWord;
        private TextView tvChar;

        public WordHolder(View itemView) {
            super(itemView);
            tvWord = (TextView) itemView.findViewById(R.id.tv_word);
            tvChar = (TextView) itemView.findViewById(R.id.tv_char);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailFragment detailFragment = DetailFragment.newInstance(word.getUuid());
                    detailFragment.setTargetFragment(WordListFragment.this , DetailFragment.REQ_DETAIL_FRAGMENT);
                    getFragmentManager().beginTransaction().add(R.id.container , detailFragment).commit();
                }
            });
        }

        public void setUI(Word word){
            this.word = word;

            tvWord.setText(word.getEnglishWord());
            tvChar.setText(String.valueOf(word.getEnglishWord().charAt(0)));
        }

    }


    private class WordAdapter extends RecyclerView.Adapter<WordHolder>{
        private List<Word> words;

        public WordAdapter (List<Word> words){
            this.words=words;
        }


        @Override
        public WordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item , parent , false);

            WordHolder wordHolder = new WordHolder(view);
            return wordHolder;
        }

        @Override
        public void onBindViewHolder(WordHolder holder, int position) {
            Word word = words.get(position);
            holder.setUI(word);


        }

        @Override
        public int getItemCount() {
            if (words== null)
                return 0;
            return words.size();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_word_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;

    }



  @Override
  public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu, menu);
    MenuItem item = menu.findItem(R.id.action_search);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_add:
        FragmentManager fragmentManager = getFragmentManager();
        AddDialogFragment addDialogFragment = AddDialogFragment.newInstance();
        addDialogFragment.setTargetFragment(WordListFragment.this , AddDialogFragment.REQ_ADD);
        addDialogFragment.show(fragmentManager , ADD_DIALOG_TAG);
        return true;
      case R.id.action_search:

        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }


  @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    public void updateUI(){

        List<Word> words = WordRepository.getInstance(getActivity()).getWords();
        wordAdapter = new WordAdapter(words);
        recyclerView.setAdapter(wordAdapter);

    }

}
