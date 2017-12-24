package com.example.behrooz.homework;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public static final int REQ_DETAIL_FRAGMENT = 1 ;
    public static final String ARGS_UUID = "args_uuid";
    private Word word;
    private TextView tvWord;
    private TextView tvMeaning;
    private Button btnEdit;
    private Button btnRemove;



    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);

        word = (Word) getArguments().getSerializable(ARGS_UUID);

        tvWord = (TextView) view.findViewById(R.id.tv_word);
        tvMeaning = (TextView) view.findViewById(R.id.tv_meaning);
        btnRemove = (Button) view.findViewById(R.id.btn_remove);
        btnEdit = (Button) view.findViewById(R.id.btn_edit);


        return view;
    }


    public  static DetailFragment newInstance (UUID uuid){
        DetailFragment detailFragment = new DetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARGS_UUID , uuid);
        detailFragment.setArguments(args);

        return detailFragment;
    }


}