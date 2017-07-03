package com.teamj.joseguaman.bespeapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jose Guaman on 30/06/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<Lugar> mListaLugar = new ArrayList<>();

    public MainAdapter(List<Lugar> lugarLista) {
        this.mListaLugar = lugarLista;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_icon)
        public ImageView imgIcono;

        @BindView(R.id.txt_other)
        public TextView txtOther;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lugar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lugar lugar = mListaLugar.get(position);
        holder.txtOther.setText(String.valueOf(lugar.getLugarId()));
    }

    @Override
    public int getItemCount() {
        return mListaLugar.size();
    }

    public Lugar getItem(int pos) {
        return mListaLugar.get(pos);
    }

}
