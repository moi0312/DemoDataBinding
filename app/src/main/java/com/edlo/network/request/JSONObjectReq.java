package com.edlo.network.request;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.edlo.network.HTTPMethod;
import com.edlo.network.ResponseCallback;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by edward.lo on 2018/1/4.
 */

public class JSONObjectReq extends JsonObjectRequest {
	private Map<String, String> headers;

	private JSONObjectReq(HTTPMethod method, String url, Map<String, String> headers, JSONObject jsonRequest, ResponseCallback<JSONObject> responseCallback) {
		super(method.getValue(), url, jsonRequest, responseCallback, responseCallback);
		setHeaders(headers);
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return (this.headers != null) ? this.headers : super.getHeaders();
	}

	public static class Builder implements IReqBuilder<JSONObjectReq, JSONObject> {
		private HTTPMethod method = HTTPMethod.GET;
		private String url;
		private HashMap<String, String> headers;
		private JSONObject jsonRequest;
		private ResponseCallback<JSONObject> responseCallback;

		public Builder(String url) { this.url = url; }

		public Builder(String url, ResponseCallback<JSONObject> responseCallback) {
			this.url = url;
			this.responseCallback = responseCallback;
		}

		@Override
		public JSONObjectReq build() {
			return new JSONObjectReq(method, url, headers, jsonRequest, responseCallback);
		}

		@Override
		public Builder setMethod(HTTPMethod method) {
			this.method = method;
			return this;
		}

		@Override
		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		@Override
		public Builder setHeaders(HashMap<String, String> headers) {
			this.headers = headers;
			return this;
		}

		@Override
		public Builder putHeader(String key, String value) {
			if(this.headers == null) {
				this.headers = new HashMap<>();
			}
			this.headers.put(key, value);
			return this;
		}

		@Override
		public Builder setResponseCallback(ResponseCallback<JSONObject> responseCallback) {
			this.responseCallback = responseCallback;
			return this;
		}
		public Builder setRequestBody(JSONObject jsonRequest) {
			this.jsonRequest = jsonRequest;
			return this;
		}
	}
}
