package com.teamj.joseguaman.bespeapp.fragments;


import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.adapter.MainAdapter;
import com.teamj.joseguaman.bespeapp.fragments.dialog.LugaresInfoDialog;
import com.teamj.joseguaman.bespeapp.listener.RecyclerItemClickListener;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Area;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;
import com.teamj.joseguaman.bespeapp.modelo.util.DialogInformacion;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    @BindView(R.id.reclyclerViewLugares)
    RecyclerView mRecyclerView;

    private View view;
    private Unbinder unbinder;
    private MainAdapter mMainAdapter;

//    public MainFragment() {
//        // Required empty public constructor
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
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
//                        Lote lote = adapterSiembraAdapter.getItem(position);
//                        System.out.println("Lote seleccionado fragment: " + lote.toString());
//                        EventBus.getDefault().postSticky(lote);
//                        DialogFragment dialog = new AvisoSiembraDialog();
//                        dialog.setCancelable(false);//evita que se cierre al presionar el back button
//                        dialog.show(AvisoSiembraFragment.this.getChildFragmentManager(), AvisoSiembraFragment.class.getSimpleName());


                        DialogInformacion mensaje = new DialogInformacion();
                        mensaje.setImage(R.drawable.ic_menu_camera);
//                        mensaje.setMensaje(getResources().getString(R.string.metodologia_aviso_siembra));
                        mensaje.setMensaje("Esto es nuevo xD");
                        mensaje.setTitulo("Nuevo título con id  " + position);
                        EventBus.getDefault().postSticky(mensaje);
                        DialogFragment dialog = new LugaresInfoDialog();
                        dialog.setCancelable(false);//evita que se cierre al presionar el back button
                        dialog.show(MainFragment.this.getChildFragmentManager(), MainFragment.class.getSimpleName());
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

        mRecyclerView.setAdapter(mMainAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void mostrarInformacionDialog() {
        /*
         MetodologiaMensajeDialog mensaje = new MetodologiaMensajeDialog();
        mensaje.setImage(R.drawable.ic_tractor_24);
        mensaje.setMessage(getResources().getString(R.string.metodologia_aviso_siembra));
        mensaje.setTitulo(getString(R.string.title_aviso_siembra));
        EventBus.getDefault().postSticky(mensaje);
        DialogFragment dialog = new MetodologiaUtilDialog();
        dialog.setCancelable(false);//evita que se cierre al presionar el back button
        dialog.show(AvisoSiembraFragment.this.getChildFragmentManager(), AvisoSiembraFragment.class.getSimpleName());
         */
    }
}