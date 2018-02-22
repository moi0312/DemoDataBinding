package com.edlo.network.request;

import com.edlo.network.HTTPMethod;
import com.edlo.network.ResponseCallback;

import java.util.HashMap;

/**
 * Created by edward.lo on 2018/1/5.
 */

interface IReqBuilder<ReqType, ResponseType> {
	IReqBuilder setMethod(HTTPMethod method);
	IReqBuilder setUrl(String url);
	IReqBuilder setHeaders(HashMap<String, String> headers);
	IReqBuilder putHeader(String key, String value);
	IReqBuilder setResponseCallback(ResponseCallback<ResponseType> responseCallback);
	ReqType build();
}
