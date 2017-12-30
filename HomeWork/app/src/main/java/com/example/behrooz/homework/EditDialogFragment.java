package com.example.behrooz.homework;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditDialogFragment extends DialogFragment {


    private Word word;
    private EditText etWord;
    private EditText etMeaning;
    private static final String ARGS_UUID = "args_uuid";



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add, null, false);

        etWord = (EditText) view.findViewById(R.id.et_word);
        etMeaning = (EditText) view.findViewById(R.id.et_meaning);

        UUID uuid = (UUID) getArguments().getSerializable(ARGS_UUID);
        word = WordRepository.getInstance(getActivity()).getWord(uuid);

        etWord.setText(word.getEnglishWord());
        etMeaning.setText(word.getPersianWord());

        return new AlertDialog.Builder(getActivity())
                .setTitle("Edit Word")
                .setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (etWord.getText().toString().length() == 0 || etMeaning.getText().toString().length() == 0)
                            Toast.makeText(getActivity(), "Empty input!", Toast.LENGTH_SHORT).show();

                        else {
                            word.setEnglishWord(etWord.getText().toString());
                            word.setPersianWord(etMeaning.getText().toString());
                        }

                        WordRepository.getInstance(getActivity()).updateWord(word);
                        getFragmentManager().popBackStackImmediate();
                        getTargetFragment().getTargetFragment().onResume();
                    }
                })
                .create();
    }

    public EditDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    public static EditDialogFragment newInstance(UUID uuid) {
        EditDialogFragment editDialogFragment = new EditDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_UUID , uuid );
        editDialogFragment.setArguments(args);
        return editDialogFragment;
    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        WordRepository.getInstance(getActivity()).updateWord(word);
//    }
}
