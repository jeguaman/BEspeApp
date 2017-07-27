package com.teamj.joseguaman.bespeapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.modelo.beacon.Lugar;
import com.teamj.joseguaman.bespeapp.modelo.beacon.WSResponse;
import com.teamj.joseguaman.bespeapp.webService.LugaresRestClient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jose Guaman on 30/06/2017.
 */

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.ViewHolder> {

    private static final String TAG = LugarAdapter.class.getSimpleName();

    private List<Lugar> mListaLugar = new ArrayList<>();
    private Context mContext;

    public LugarAdapter(List<Lugar> lugarLista, Context context) {
        this.mListaLugar = lugarLista;
        this.mContext = context;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        LugaresRestClient lugaresRestClient = new LugaresRestClient(mContext);
        Lugar lugar = mListaLugar.get(position);
        lugaresRestClient.getIconoPorIdLugar(String.valueOf(lugar.getLugarId()), new Response.Listener<WSResponse>() {
            @Override
            public void onResponse(WSResponse response) {
                Gson gson = new Gson();
                Lugar lugarRecibido = gson.fromJson(response.getJsonEntity(), Lugar.class);
                Bitmap bitmap;
                if (lugarRecibido.getImagen() != null) {
                    InputStream inputStream = new ByteArrayInputStream(lugarRecibido.getImagen());
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    holder.imgIcono.setImageBitmap(bitmap);
                } else {

                    // holder.imgIcono.setImageResource(R.drawable.ic_room);
                }

                holder.txtOther.setText(String.valueOf(lugarRecibido.getLugarId()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error " + error);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListaLugar.size();
    }

    public Lugar getItem(int pos) {
        return mListaLugar.get(pos);
    }

}
