package com.teamj.joseguaman.bespeapp.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.adapter.MainAdapter;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

/**
 * Created by alterbios on 06-Jul-17.
 */

public class LugarFragment extends Fragment {
    private static final String ARG_PRODUCTS = "PRODS";
    private static List<Area> areasLista;
    private static List<Lugar> listaLugar;
    private MainAdapter mMainAdapter;
    int position = 0;
    RecyclerView prodList;


    public static LugarFragment newInstance(List<Area> products, int position) {
        LugarFragment fragment = new LugarFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCTS, (ArrayList<Area>) products);
        args.putInt("KEY_POSITION", position);
        args.putInt("KEY_ID", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if(isVisibleToUser){
        if (getArguments() != null) {
            areasLista = (List<Area>) getArguments().getSerializable(ARG_PRODUCTS);
            this.position = getArguments().getInt("KEY_POSITION");
        }
        // }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_tabs, container, false);
        //prodList = (RecyclerView) view.findViewById(R.id.product_list);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        prodList.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_camera);
        listaLugar.add(new Lugar(1, new Area(), "descripciom1", bm, "titulo1"));
        listaLugar.add(new Lugar(2, new Area(), "descripciom2", bm, "titulo2"));
        listaLugar.add(new Lugar(3, new Area(), "descripciom3", bm, "titulo3"));
        listaLugar.add(new Lugar(4, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(5, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(6, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(7, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(8, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(9, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(10, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(11, new Area(), "descripciom4", bm, "titulo4"));
        listaLugar.add(new Lugar(12, new Area(), "descripciom4", bm, "titulo4"));

        mMainAdapter = new MainAdapter(listaLugar);
        prodList.setAdapter(mMainAdapter);
        //Log.e("ProductFragment " ,"" + areasLista.get(position).getName());
    }
}
