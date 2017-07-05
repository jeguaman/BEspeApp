package com.teamj.joseguaman.bespeapp.webService.restClientBase;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Jose on 9/28/2016.
 */

public class VolleyRequest {
    private static RequestQueue mRequestQueue;
    private static RequestQueue mImageRequestQueue;

    private static ImageLoader mImageLoader;
    private static Context mContext;
    private static LruBitmapCache lruBitmapCache;
    private static Cache cache;

    public VolleyRequest() {
    }

    /**
     * @param context application context
     */
    public static void init(Context context) {
        mContext = context;
        cache = new DiskBasedCache(mContext.getCacheDir(), 10 * 1024 * 1024);

        Network network = new BasicNetwork(new HurlStack());
        mImageRequestQueue = new RequestQueue(cache, network);
        // Don't forget to start the volley request queue
        mImageRequestQueue.start();

        mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());

        lruBitmapCache = new LruBitmapCache(context);
        mImageLoader = new ImageLoader(mImageRequestQueue, lruBitmapCache);
    }

    /**
     * @return instance of the queue
     * @throws IllegalStateException if init has not yet been called
     */
    public static synchronized RequestQueue getRequestQueue() throws IllegalStateException {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("Not initialized");
        }
    }

    // public <T> void addToRequestQueue(Request<T> req) {
    //     getRequestQueue().add(req);
    //  }

    //    public static synchronized RequestQueue getImageRequestQueue() throws IllegalStateException {
//        if (mImageRequestQueue != null) {
//            return mImageRequestQueue;
//        } else {
//            throw new IllegalStateException("Not initialized mImageRequestQueue");
//        }
//    }
    public static void clearImageCache(String url) {
        if (mImageRequestQueue != null) {
            String cacheKey = getCacheKey(url, 0, 0, ImageView.ScaleType.CENTER_INSIDE);
            mImageRequestQueue.getCache().remove(url);
            lruBitmapCache.remove(cacheKey);
        }
    }

    /**
     * Creates a cache key for use with the L1 cache. Copied from Image Loader
     *
     * @param url       The URL of the request.
     * @param maxWidth  The max-width of the output.
     * @param maxHeight The max-height of the output.
     * @param scaleType The scaleType of the imageView.
     */
    private static String getCacheKey(String url, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {
        return new StringBuilder(url.length() + 12).append("#W").append(maxWidth)
                .append("#H").append(maxHeight).append("#S").append(scaleType.ordinal()).append(url)
                .toString();
    }

    public static void clearImageCache() {
        if (mImageRequestQueue != null) {
            mImageRequestQueue.getCache().clear();
            cache.clear();
        }
    }

    public static synchronized ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
