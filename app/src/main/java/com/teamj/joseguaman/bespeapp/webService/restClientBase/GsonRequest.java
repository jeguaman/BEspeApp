package com.teamj.joseguaman.bespeapp.webService.restClientBase;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.teamj.joseguaman.bespeapp.utils.Constants;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Jose on 9/28/2016.
 */

public class GsonRequest<T> extends Request<T> {

    private static final String TAG = GsonRequest.class.getName();
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Map<String, String> params;
    private final Response.Listener<T> listener;
    public static final String CHARSET_PARAMETER = "charset";
    public static final String MEDIA_TYPE_WILDCARD = "*";
    public static final String WILDCARD = "*/*";

    public static final String APPLICATION_XML = "application/xml";

    public static final String APPLICATION_ATOM_XML = "application/atom+xml";

    public static final String APPLICATION_XHTML_XML = "application/xhtml+xml";

    public static final String APPLICATION_SVG_XML = "application/svg+xml";

    public static final String APPLICATION_JSON = "application/json";

    public static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

    public static final String TEXT_PLAIN = "text/plain";

    public static final String TEXT_XML = "text/xml";

    public static final String TEXT_HTML = "text/html";

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Map<String, String> params,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.params = params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params != null ? params : super.getParams();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            if (Constants.DEV_MODE) {
                Log.i(TAG, json);
            }
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString());
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            Log.e(TAG, e.toString());
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
            volleyError = error;
        }
        return volleyError;
    }
}
