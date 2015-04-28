package org.simple.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AsynHttpTools {

	public static AsyncHttpClient asynClient = new AsyncHttpClient();

	static {
		asynClient.setTimeout(11000);

	}

	public static void httpGetMethod(String url,
			AsyncHttpResponseHandler responseHandler) {

		asynClient.get(url, responseHandler);
		
	}

	public static void httpGetMethodByParams(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		asynClient.get(url, params, responseHandler);
	}

	public static void httpPostMethod(String url,
			AsyncHttpResponseHandler responseHandler) {
		asynClient.post(url, responseHandler);
	}

	public static void httpPostMethodByParams(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		asynClient.post(url, params, responseHandler);
	}

}
