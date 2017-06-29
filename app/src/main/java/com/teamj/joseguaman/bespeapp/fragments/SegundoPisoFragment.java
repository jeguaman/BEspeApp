package com.teamj.joseguaman.bespeapp.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamj.joseguaman.bespeapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SegundoPisoFragment extends Fragment {


    public SegundoPisoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_segundo_piso, container, false);
    }

}
