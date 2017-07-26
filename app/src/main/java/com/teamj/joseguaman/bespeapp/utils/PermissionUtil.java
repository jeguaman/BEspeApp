package com.teamj.joseguaman.bespeapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.teamj.joseguaman.bespeapp.R;

import java.util.Timer;
import java.util.TimerTask;

public abstract class PermissionUtil {

    private static AlertDialog alertDialog = null;


    public static boolean isAllPermissionGranted(Context activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permission = Constants.ALL_REQUIRED_PERMISSION;
            if (permission.length == 0) return false;
            for (String s : permission) {
                if (ActivityCompat.checkSelfPermission(activity, s) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void verifyPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permission = Constants.ALL_REQUIRED_PERMISSION;
            if (permission.length == 0) return;
            if (!PermissionUtil.isAllPermissionGranted(activity)) {
                ActivityCompat.requestPermissions(activity, permission, Constants.MY_PERMISSIONS_REQUEST);
            }
        }
    }

    public static AlertDialog createPermissionDialog(final Activity activity, final boolean withDelay) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(R.string.dialog_title_permission));
        builder.setCancelable(false);

        builder.setMessage(activity.getString(R.string.dialog_content_permission));
        builder.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                PermissionUtil.verifyPermission(activity);
            }
        });
        builder.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (withDelay) {
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            activity.finish();
                        }
                    }, 1000);
                } else {
                    activity.finish();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }

}
