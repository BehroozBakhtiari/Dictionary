package com.example.behrooz.homework;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordListFragment extends Fragment implements SearchView.OnQueryTextListener {

  public static final String ADD_DIALOG_TAG = "add_dialog_tag";
  public static final int REQ_DETAIL_FRAGMENT = 1;

  private RecyclerView recyclerView;
  private WordAdapter wordAdapter;


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
          detailFragment.setTargetFragment(WordListFragment.this, REQ_DETAIL_FRAGMENT);
          getFragmentManager().beginTransaction().replace(((ViewGroup) getView().getParent()).getId(), detailFragment)
            .addToBackStack(null)
            .commit();
//          Log.d("tagggg" , ((ViewGroup) getView().getParent()).getId() + "");
        }
      });
    }

    public void setUI(Word word) {
      this.word = word;
      tvWord.setText(word.getEnglishWord());
      tvChar.setText(String.valueOf(word.getEnglishWord().charAt(0)));
    }

  }


  private class WordAdapter extends RecyclerView.Adapter<WordHolder> {
    private final List<Word> words;

    public WordAdapter(List<Word> words) {
      this.words = words;
    }


    @Override
    public WordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item, parent, false);
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
      if (words == null)
        return 0;
      return words.size();
    }


    public Word removeItem(int position) {
      final Word word = words.remove(position);
      notifyItemRemoved(position);
      return word;
    }

    public void addItem(int position, Word word) {
      words.add(position, word);
      notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
      final Word word = words.remove(fromPosition);
      words.add(toPosition, word);
      notifyItemMoved(fromPosition, toPosition);
    }


    public void animateTo(List<Word> words) {
      applyAndAnimateRemovals(words);
      applyAndAnimateAdditions(words);
      applyAndAnimateMovedItems(words);
    }

    private void applyAndAnimateRemovals(List<Word> newModels) {
      for (int i = words.size() - 1; i >= 0; i--) {
        final Word word = words.get(i);
        if (!newModels.contains(word)) {
          removeItem(i);
        }
      }
    }

    private void applyAndAnimateAdditions(List<Word> newModels) {
      for (int i = 0, count = newModels.size(); i < count; i++) {
        final Word word = newModels.get(i);
        if (!words.contains(word)) {
          addItem(i, word);
        }
      }
    }

    private void applyAndAnimateMovedItems(List<Word> newModels) {
      for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
        final Word word = newModels.get(toPosition);
        final int fromPosition = words.indexOf(word);
        if (fromPosition >= 0 && fromPosition != toPosition) {
          moveItem(fromPosition, toPosition);
        }
      }
    }

  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_word_list, container, false);

    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

    updateUI();

    return view;

  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setHasOptionsMenu(true);

    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    updateUI();
  }


  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu, menu);
    final MenuItem item = menu.findItem(R.id.action_search);
    final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
    searchView.setOnQueryTextListener(this);
  }

  @Override
  public boolean onQueryTextSubmit(String query) {
    return false;
  }

  @Override
  public boolean onQueryTextChange(String query) {
    query = query.toLowerCase();

    final List<Word> filteredModelList = new ArrayList<>();
    for (Word word : WordRepository.getInstance(getActivity()).getWords()) {
      final String text = word.getEnglishWord().toLowerCase();
      if (text.contains(query)) {
        filteredModelList.add(word);
      }
    }
    wordAdapter.animateTo(filteredModelList);
    recyclerView.scrollToPosition(0);


    return true;
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_add:
        FragmentManager fragmentManager = getFragmentManager();
        AddDialogFragment addDialogFragment = AddDialogFragment.newInstance();
        addDialogFragment.setTargetFragment(WordListFragment.this, AddDialogFragment.REQ_ADD);
        addDialogFragment.show(fragmentManager, ADD_DIALOG_TAG);
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


  public void updateUI() {
    List<Word> words = WordRepository.getInstance(getActivity()).getWords();

    wordAdapter = new WordAdapter(words);
    recyclerView.setAdapter(wordAdapter);
    wordAdapter.notifyDataSetChanged();
    Collections.sort(words);


  }
}