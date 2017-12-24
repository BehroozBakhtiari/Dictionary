package com.example.behrooz.homework;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordListFragment extends Fragment {

    public static final int REQ_ADD = 0;
    public static final String ADD_DIALOG_TAG ="add_dialog_tag" ;
    private ImageView imageView;


    private class WordHolder extends RecyclerView.ViewHolder {
        public WordHolder(View itemView) {
            super(itemView);
        }
    }


    private class WordAdapter extends RecyclerView.Adapter<WordHolder>{
        

        @Override
        public WordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(WordHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_word_list, container, false);


        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                AddDialogFragment addDialogFragment = AddDialogFragment.newInstance();
                addDialogFragment.setTargetFragment(WordListFragment.this , REQ_ADD);
                addDialogFragment.show(fragmentManager , ADD_DIALOG_TAG);
            }
        });

        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        else{

        }
    }
}
