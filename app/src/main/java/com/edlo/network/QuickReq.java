package com.edlo.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.edlo.network.request.JSONArrayReq;
import com.edlo.network.request.JSONObjectReq;
import com.edlo.network.request.StringReq;

/**
 * Created by edward.lo on 2018/1/4.
 */

public class QuickReq {
	private static final String TAG = QuickReq.class.getSimpleName();

	private static QuickReq instance;
	private static Context mContext;

	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	public static synchronized QuickReq getInstance(Context context) {
		if ( instance == null ) {
			instance = new QuickReq(context);
		}
		return instance;
	}

	private QuickReq(Context context) {
		mContext = context;
		mRequestQueue = getRequestQueue();

		if(QuickReqConfig.ENABLE_IMAGE_LOADER) { initImageLoader(); }
	}

	private RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			//1
//			mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());

			//2
			Cache cache = new DiskBasedCache(mContext.getCacheDir(), QuickReqConfig.CACHE_SIZE); // Instantiate the cache
			Network network = new BasicNetwork(new HurlStack()); // Set up to use HttpURLConnection as the HTTP client.
			mRequestQueue = new RequestQueue(cache, network); // Instantiate the RequestQueue with the cache.
			mRequestQueue.start(); // Start the mRequestQueue
		}
		return mRequestQueue;
	}

	private <T> void addRequest(Request<T> req, String tag) {
		if (tag != null) { req.setTag(tag); }
		getRequestQueue().add(req);
	}

	public void cancelRequestByTag(String tag) {
		getRequestQueue().cancelAll(tag);
	}

	public void request(StringReq req, String tag) { addRequest(req, tag); }
	public void request(JSONObjectReq req, String tag) { addRequest(req, tag); }
	public void request(JSONArrayReq req, String tag) { addRequest(req, tag); }


	private void initImageLoader() {
		mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

			private final LruCache<String, Bitmap> cache = new LruCache<>(QuickReqConfig.IMAGE_CACHE_SIZE);

			@Override public Bitmap getBitmap(String url) {
				return cache.get(url);
			}
			@Override public void putBitmap(String url, Bitmap bitmap) {
				cache.put(url, bitmap);
			}
		});
	}

	public ImageLoader getImageLoader() {
		return mImageLoader;
	}
}