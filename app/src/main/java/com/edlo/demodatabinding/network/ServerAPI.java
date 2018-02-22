package com.edlo.demodatabinding.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.edlo.demodatabinding.model.ItemSpot;
import com.edlo.network.HTTPMethod;
import com.edlo.network.QuickReq;
import com.edlo.network.ResponseCallback;
import com.edlo.network.util.Util;
import com.edlo.network.request.JSONObjectReq;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by edward.lo on 2018/1/5.
 */

public class ServerAPI {
	private static final String TAG = ServerAPI.class.getSimpleName();

	private static final String SERVER_DOMAIN = "http://data.taipei/opendata/datalist/apiAccess";

	public static class Header {
//		public static final String AUTHORIZATION = "Authorization";
//		public static final String TOKEN_PREFIX = "Bearer ";
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String CONTENT_TYPE_JSON = "application/json";
	}

	public static class Key {
		public static final String SCOPE = "scope";
		public static final String RID = "rid";
	}

	public static class Value {
		public static final String SCOPE = "resourceAquire";
		public static final String RID = "bf073841-c734-49bf-a97f-3757a6013812";
	}

	private static String getAPI_SpotInParks() {
		return Util.concatUrlWithGETParams(SERVER_DOMAIN,
				new String[] {Key.SCOPE, Key.RID}, new String[] {Value.SCOPE, Value.RID} );
	}

	public static void req_SpotInParks(final Context context, final ResponseCallback<JSONObject> callback) {
		String url = ServerAPI.getAPI_SpotInParks();
		JSONObjectReq req = new JSONObjectReq.Builder(url)
				.setMethod(HTTPMethod.GET)
				.setResponseCallback(callback)
				.build();
		Log.v(TAG, "SpotInParks request: "+ url);
		QuickReq.getInstance(context).request(req, null);
	}

	public static ArrayList<ItemSpot> result_SpotInParks(@NonNull JSONObject response) {
		ArrayList<ItemSpot> list = null;
		try {
			JSONArray results = response.getJSONObject("result").getJSONArray("results");
			list = new ArrayList<>();
			Gson gson = new Gson();
			for (int i=0; i< results.length(); i++) {
				JSONObject obj = results.getJSONObject(i);
				ItemSpot item = gson.fromJson(obj.toString(), ItemSpot.class);
				list.add(item);
//				Log.v(TAG, "itemJSON: "+i+ " -> " + obj.toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}