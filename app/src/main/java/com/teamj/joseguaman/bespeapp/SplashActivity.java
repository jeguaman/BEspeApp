package com.teamj.joseguaman.bespeapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.teamj.joseguaman.bespeapp.utils.Constants;
import com.teamj.joseguaman.bespeapp.utils.PermissionUtil;
import com.teamj.joseguaman.bespeapp.utils.Tools;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jose Guaman on 23/07/2017.
 */

public class SplashActivity extends AppCompatActivity {


    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final long SPLASH_SCREEN_DELAY = 3000;
    private View mProgressView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        mProgressView = findViewById(R.id.progressBar);
        continueWithApplication();
    }

    private void continueWithApplication() {
        if (!Tools.checkConnection(this)) {
            Snackbar.make(findViewById(android.R.id.content), "No posee conexión a internet.", Snackbar.LENGTH_LONG).show();
        } else {
            // if (Tools.isHostRechable(Constants.getHostUrlAppNAME())) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    // Start the next activity
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            };

            // Simulate a long loading process on application startup.
            Timer timer = new Timer();
            timer.schedule(task, SPLASH_SCREEN_DELAY);
//            } else {
//                Log.e(TAG, "NO existe conexión al servidor");
//            }
        }
    }


    @Override
    protected void onResume() {
//        if (!PermissionUtil.isAllPermissionGranted(this)) {
//            PermissionUtil.createPermissionDialog(this, true).show();
//        } else {
//           continueWithApplication();
//        }
        super.onResume();
    }

}
