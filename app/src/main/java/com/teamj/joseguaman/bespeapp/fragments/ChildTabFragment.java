package com.teamj.joseguaman.bespeapp.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.adapter.LugarAdapter;
import com.teamj.joseguaman.bespeapp.fragments.dialog.LugaresInfoDialog;
import com.teamj.joseguaman.bespeapp.listener.RecyclerItemClickListener;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.modelo.util.DialogInformacion;
import com.teamj.joseguaman.bespeapp.utils.ConnectionDetector;
import com.teamj.joseguaman.bespeapp.webService.LugaresRestClient;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Jose Guaman on 08/07/2017.
 */

public class ChildTabFragment extends Fragment {


    @BindView(R.id.reclyclerViewLugares)
    RecyclerView mRecyclerView;

    private View view;
    private Unbinder unbinder;
    private LugarAdapter mMainAdapter;
    private ConnectionDetector mConnectionDetector;
    private int idLugarSeleccionado;
    private String childname;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_child, container, false);
        Bundle bundle = getArguments();
        childname = bundle.getString("data");
        unbinder = ButterKnife.bind(this, view);
        mConnectionDetector = new ConnectionDetector(getActivity());
        setHasOptionsMenu(true);
        initLayout();
        loadData();

        return view;
    }


    public void initLayout() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMainAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Lugar lugar = mMainAdapter.getItem(position);
                        idLugarSeleccionado = lugar.getLugarId();
                        //TODO: consultar por id en un nuevo metodo

                        DialogInformacion mensaje = new DialogInformacion();
                        mensaje.setImage(R.drawable.ic_menu_camera);
//                        mensaje.setMensaje(getResources().getString(R.string.metodologia_aviso_siembra));
                        mensaje.setMensaje("Esto es nuevo xD");
                        mensaje.setTitulo("Nuevo título con id  " + position);
                        EventBus.getDefault().postSticky(mensaje);
                        DialogFragment dialog = new LugaresInfoDialog();
                        dialog.setCancelable(false);//evita que se cierre al presionar el back button
                        dialog.show(ChildTabFragment.this.getChildFragmentManager(), ChildTabFragment.class.getSimpleName());
                    }

                    @Override
                    public void onLongItemClick(View view, final int position) {

                    }
                })
        );
    }

    public void loadData() {
        List<Lugar> listaLugar = new ArrayList<>();
        //TODO: poner todo lo dela consulta que se traiga del webservice

        LugaresRestClient lrc = new LugaresRestClient(getActivity());
        lrc.getLugaresPorArea("", new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


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

        mMainAdapter = new LugarAdapter(listaLugar);

        mRecyclerView.setAdapter(mMainAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
