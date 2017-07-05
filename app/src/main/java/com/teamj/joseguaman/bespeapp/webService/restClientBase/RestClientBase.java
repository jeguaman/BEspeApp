package com.teamj.joseguaman.bespeapp.webService.restClientBase;

import android.content.Context;

import com.android.volley.Request;

/**
 * Created by mac-developer on 10/12/16.
 */

public class RestClientBase {
    private Context mContext;

    public RestClientBase(Context mContext) {
        this.mContext = mContext;
    }

    public void executeRequest(final Request req) {
        VolleyRequest.getRequestQueue().add(req);
    }


}
