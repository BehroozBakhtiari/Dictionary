package com.example.behrooz.homework;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeaderFragment extends Fragment {

    public static final int REQ_ADD = 0;
    public static final String ADD_DIALOG_TAG ="add_dialog_tag" ;
    private ImageView imageView;

    public HeaderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_header, container, false);

        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                AddDialogFragment addDialogFragment = AddDialogFragment.newInstance();
                addDialogFragment.setTargetFragment(HeaderFragment.this , REQ_ADD);
                addDialogFragment.show(fragmentManager , ADD_DIALOG_TAG);
            }
        });

        return view;
    }

}
