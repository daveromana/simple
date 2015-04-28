package org.simple.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpAsyncTask {
	
	public static HttpClient httpClient = new DefaultHttpClient();
	
	
    public static String http_PostMethod_JSONRequest(final String url,final String json) throws Exception {
    	FutureTask<String> task = new FutureTask<String>(
				new Callable<String>() {

					@Override
					public String call() throws Exception {
						// TODO Auto-generated method stub
						HttpPost post = new HttpPost(url);
						JSONObject jsonParams=new JSONObject(json);
						Iterator<String> ite=jsonParams.keys();
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						while(ite.hasNext()){
							String key=ite.next().toString();
							params.add(new BasicNameValuePair(key,jsonParams.optString(key)));
						}
						System.out.println(params.toString());
						post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
						HttpResponse httpResponse = httpClient.execute(post);
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							String result = EntityUtils.toString(httpResponse
									.getEntity());
							return result;
						}
						return null;
					}
				});
		new Thread(task).start();
		return task.get();
    }
	
	public static String http_GetMethod_Request(final String url)
			throws Exception {
		FutureTask<String> task = new FutureTask<String>(
				new Callable<String>() {

					@Override
					public String call() throws Exception {
						
						HttpGet get = new HttpGet(url);
						HttpResponse httpResponse = httpClient.execute(get);
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							String result = EntityUtils.toString(httpResponse
									.getEntity());
							return result;
						}
						return null;
					}

				});
		new Thread(task).start();
		return task.get();

	}
	
	public static String http_PostMethod_Request(final String url,
			final Map<String, String> rawParams) throws Exception {
		FutureTask<String> task = new FutureTask<String>(
				new Callable<String>() {

					@Override
					public String call() throws Exception {
						// TODO Auto-generated method stub
						HttpPost post = new HttpPost(url);
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						for (String key : rawParams.keySet()) {
							params.add(new BasicNameValuePair(key, rawParams
									.get(key)));
						}
						post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
						HttpResponse httpResponse = httpClient.execute(post);
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							String result = EntityUtils.toString(httpResponse
									.getEntity());
							return result;
						}
						return null;
					}
				});
		new Thread(task).start();
		return task.get();
	}
    
}
