package com.edlo.network.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.edlo.network.ResponseCallback;
import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by edward.lo on 2018/1/4.
 */

public class GsonReq<T> extends Request<T> {

	private final Gson gson = new Gson();
	private final Map<String, String> headers;
	private final Class<T> clazz;
	private final ResponseCallback<T> responseCallback;

	public GsonReq(int method, String url, Class<T> clazz, Map<String, String> headers, ResponseCallback<T> responseCallback) {
		super(method, url, responseCallback);
		this.clazz = clazz;
		this.headers = headers;
		this.responseCallback = responseCallback;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return (headers != null) ? headers : super.getHeaders();
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
		} catch (Exception e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(T response) {
		responseCallback.onResponse(response);
	}
}




