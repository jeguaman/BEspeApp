package com.teamj.joseguaman.bespeapp.fragments;


import android.app.DialogFragment;
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
import com.teamj.joseguaman.bespeapp.listener.RecyclerItemClickListener;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;

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
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
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
                        Snackbar.make(view, "Presionada en la posición " + position, Snackbar.LENGTH_LONG).show();
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
