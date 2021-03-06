package com.teamj.joseguaman.bespeapp.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.teamj.joseguaman.bespeapp.R;
import com.teamj.joseguaman.bespeapp.modelo.util.DialogInformacion;
import com.teamj.joseguaman.bespeapp.utils.PreferencesShare;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Jose Guaman on 03/07/2017.
 */

public class LugaresInfoDialog extends DialogFragment {

    @BindView(R.id.txt_title_dialog)
    TextView txtTitle;

    @BindView(R.id.icon_dialog)
    ImageView imgGenerica;

    DocumentView mContenido;

    private Unbinder unbinder;
    private DialogInformacion dialogInformacionRecibido;
    private PreferencesShare sharedPreferences;
    private View view;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_info_lugares, null);
        dialogInformacionRecibido = EventBus.getDefault().removeStickyEvent(DialogInformacion.class);
        unbinder = ButterKnife.bind(this, view);
        sharedPreferences = new PreferencesShare(getContext());
        mContenido = (DocumentView) view.findViewById(R.id.blogText);
        if (sharedPreferences.getModeNightApp()) {
            //TODO: colocar color blanco en el texto
        } else {
            //TODO: dejar color normal en negro
        }
        loadInformacion();
        builder.setView(view).setPositiveButton("Aceptar", null);
        builder.setCancelable(false);//evita que se cierre al presionar el back button
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);//evita que se cierre al presionar afuera del dialogo
        dialog.setCancelable(false);//evita que se cierre al presionar el back button
        return dialog;
    }

    private void loadInformacion() {
        txtTitle.setText(dialogInformacionRecibido.getTitulo());
        mContenido.setText(dialogInformacionRecibido.getMensaje());
        imgGenerica.setImageBitmap(dialogInformacionRecibido.getImage());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
