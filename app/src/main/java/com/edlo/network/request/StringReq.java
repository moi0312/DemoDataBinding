package com.edlo.network.request;

import com.android.volley.AuthFailureError;
import com.edlo.network.HTTPMethod;
import com.edlo.network.ResponseCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by edward.lo on 2018/1/4.
 */

public class StringReq extends com.android.volley.toolbox.StringRequest {
	private Map<String, String> headers;

	private StringReq(HTTPMethod method, String url, Map<String, String> headers, ResponseCallback<String> responseCallback) {
		super(method.getValue(), url, responseCallback, responseCallback);
		setHeaders(headers);
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return (this.headers != null) ? this.headers : super.getHeaders();
	}

	public static class Builder implements IReqBuilder<StringReq, String> {
		private HTTPMethod method = HTTPMethod.GET;
		private String url;
		private Map<String, String> headers;
		private ResponseCallback<String> responseCallback;

		public Builder(String url) { this.url = url; }

		public Builder(String url, ResponseCallback<String> responseCallback) {
			this.url = url;
			this.responseCallback = responseCallback;
		}
		@Override
		public StringReq build() {
			return new StringReq(method, url, headers, responseCallback);
		}

		@Override
		public Builder setMethod(HTTPMethod method) {
			this.method = method;
			return this;
		}
		@Override
		public Builder setHeaders(HashMap<String, String> headers) {
			this.headers = headers;
			return this;
		}

		@Override
		public IReqBuilder putHeader(String key, String value) {
			if(this.headers == null) {
				this.headers = new HashMap<>();
			}
			this.headers.put(key, value);
			return this;
		}

		@Override
		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}
		@Override
		public Builder setResponseCallback(ResponseCallback<String> responseCallback) {
			this.responseCallback = responseCallback;
			return this;
		}
	}
}