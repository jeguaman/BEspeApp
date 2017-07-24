package com.teamj.joseguaman.bespeapp.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.teamj.joseguaman.bespeapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Jose Guaman on 23/07/2017.
 */

public class InfoAppDialog extends DialogFragment {

    private Unbinder unbinder;
    private View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_info_app, null);
        unbinder = ButterKnife.bind(this, view);
        builder.setView(view).setPositiveButton("Aceptar", null);
        builder.setCancelable(false);//evita que se cierre al presionar el back button
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);//evita que se cierre al presionar afuera del dialogo
        dialog.setCancelable(false);//evita que se cierre al presionar el back button
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
