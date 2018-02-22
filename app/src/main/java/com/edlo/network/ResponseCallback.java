package com.edlo.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by edward.lo on 2018/1/4.
 */

public abstract class ResponseCallback<T> implements Response.Listener<T>, Response.ErrorListener {

	@Override
	public final void onErrorResponse(VolleyError error) {
		onError(error.getMessage());
	}

	public abstract void onResponse(T response);
	public abstract void onError(String message);
}
