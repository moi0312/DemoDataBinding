package com.edlo.network.util;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by moi0312 on 2018/2/21.
 */

public class Util {
	private static final String TAG = Util.class.getSimpleName();

	public static String concatUrlWithGETParams(String url, @NonNull String[] keys, @NonNull String[] values) {
		String result = url;
		for (int i=0; i < keys.length; i++) {
			if(values.length > i) {
				String prefix = (i==0) ? "?" : "&";
				String key = keys[i];
				String value = values[i];
				url = url.concat(prefix + key + "=" + value);
			}
		}
		Log.v(TAG, "concatUrlWithGETParams: "+ url);
		return url;
	}
}
