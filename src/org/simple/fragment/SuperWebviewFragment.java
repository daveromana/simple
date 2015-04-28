package org.simple.fragment;

import java.util.Map;
import org.apache.http.Header;
import org.simple.common.util.Person;
import org.simple.common.util.PreferencesUtils;
import org.simple.common.util.Tools;
import org.simple.http.AsynHttpTools;
import org.simple.http.JavaScriptObject;
import org.spring.simple.R;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.RenderPriority;
import android.widget.Toast;

public class SuperWebviewFragment extends Fragment {
	private static final String APP_CACAHE_DIRNAME = "/websimple"; 
	private SwipeRefreshLayout swipeLayout;
	private WebView w;
	private View view;
	private String urlT;
 
	
	public static SuperWebviewFragment newInstance() {

		SuperWebviewFragment web = new SuperWebviewFragment();
		return web;
	}

	@SuppressLint({ "SetJavaScriptEnabled", "InlinedApi" })
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		urlT = getArguments().getString("url");
		// TODO Auto-generated method stub

		if (w != null) {
			w.destroy();
		}

		view = inflater.inflate(R.layout.webview_content_layout, container,
				false);
		w = (WebView) view.findViewById(R.id.webview);
		
		swipeLayout = (SwipeRefreshLayout) view
				.findViewById(R.id.swipe_container);

		swipeLayout.setColorSchemeResources(android.R.color.holo_blue_light,
				android.R.color.holo_red_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_green_light);

		swipeLayout
				.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

					@Override
					public void onRefresh() {
						// TODO Auto-generated method stub
						w.loadUrl(w.getUrl());
					}
				});
		WebSettings settings = w.getSettings();
		//settings.setRenderPriority(RenderPriority.HIGH);
		if(Tools.getAPNType(getActivity())==-1){
			settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
			
		}else{
			settings.setCacheMode(WebSettings.LOAD_DEFAULT);
			
		}
		
		//settings.setCacheMode(WebSettings.LOAD_DEFAULT);
		settings.setJavaScriptEnabled(true);
		settings.setDomStorageEnabled(true);
		settings.setDatabaseEnabled(true);
		String cacheDirPath=getActivity().getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME;
		Log.i("web缓存路径", cacheDirPath);
		//settings.setDatabasePath(cacheDirPath);
		settings.setAllowFileAccess(true);
		settings.setAppCacheEnabled(true);
		settings.setAppCachePath(cacheDirPath);
		
		
		settings.setSupportZoom(false);
		settings.setDefaultTextEncodingName("UTF-8");

		w.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
			
				view.loadUrl(url);

				return true;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);

			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);

			}
		});
		/*w.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				if (arg2.getAction() == KeyEvent.ACTION_DOWN) {
					if (arg1 == KeyEvent.KEYCODE_BACK && w.canGoBack()) {
						w.goBack();
						return true;
					} else if (arg1 == KeyEvent.KEYCODE_BACK && !w.canGoBack()) {
						AlertDialog.Builder builder = new Builder(getActivity());
						builder.setMessage("确定要退出吗?");
						builder.setTitle("提示");
						builder.setIcon(android.R.drawable.ic_dialog_alert);
						builder.setPositiveButton("确认",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
										android.os.Process
												.killProcess(android.os.Process
														.myPid());
									}
								});

						builder.setNegativeButton(
								"取消",
								new android.content.DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								});

						builder.create().show();

						return false;
					}

				}
				return false;
			}
		});*/
		w.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				// progressBar.setProgress(newProgress);
				if (newProgress == 100) {
					swipeLayout.setRefreshing(false);
				} else {
					if (!swipeLayout.isRefreshing()) {
						swipeLayout.setRefreshing(true);
					}

				}
				super.onProgressChanged(view, newProgress);
			}

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(SuperWebviewFragment.this.getActivity())
						.setTitle("提醒消息")
						.setMessage(message)
						.setPositiveButton(android.R.string.ok,
								new AlertDialog.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										result.confirm();
									}
								}).setCancelable(false).create().show();
				return true;
			}
		});

		w.requestFocus();
		/* js注册Android 对象 */

		w.addJavascriptInterface(new JavaScriptObject(getActivity()),
				"AndUtils");
		w.addJavascriptInterface(new Object() {
			private Handler handler = new Handler();
			private Gson gson = new Gson();

			/**
			 * get 请求方式
			 * 
			 * @param url
			 */
			@JavascriptInterface
			public void httpGetMethod(final String url) {
				AsynHttpTools.httpGetMethod(url,
						new AsyncHttpResponseHandler() {

							@Override
							public void onSuccess(int arg0, Header[] arg1,
									final byte[] arg2) {
								// TODO Auto-generated method stub
								Log.i("js地址测试", url);
								handler.post(new Runnable() {

									@Override
									public void run() {

										Person p = new Person();
										p.setAge(22);
										p.setName("Curry");

										String str = gson.toJson(p);

										w.loadUrl("javascript:httpGetResult('"
												+ str + "')");

									}
								});

							}

							@Override
							public void onFailure(int arg0, Header[] arg1,
									byte[] arg2, Throwable arg3) {
								// TODO Auto-generated method stub
								Toast.makeText(getActivity(), "请求发生错误",
										Toast.LENGTH_SHORT).show();
							}
						});
			}

			/**
			 * post 请求方式
			 * 
			 * @param url
			 * @param JsonParams
			 */
			@JavascriptInterface
			public void httpPostMethod(String url, String JsonParams) {

				Map<String, String> temp = gson.fromJson(JsonParams,
						new TypeToken<Map<String, String>>() {
						}.getType());
				RequestParams params = new RequestParams(temp);
				AsynHttpTools.httpPostMethodByParams(url, params,
						new JsonHttpResponseHandler() {
							@Override
							public void onSuccess(int statusCode,
									Header[] headers,
									final String responseString) {
								handler.post(new Runnable() {

									@Override
									public void run() {
										// TODO Auto-generated method stub
										w.loadUrl("javascript:httpPostResult('"
												+ responseString + "')");
									}
								});

							}

							@Override
							public void onFailure(int statusCode,
									Header[] headers, String responseString,
									Throwable throwable) {
								Toast.makeText(getActivity(), "请求发生错误",
										Toast.LENGTH_SHORT).show();
							}
						});
			}

		}, "HttpRequest");

		CookieSyncManager.createInstance(getActivity());
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(true);
		cookieManager.setCookie(urlT,
				PreferencesUtils.getString(getActivity(), "cookie"));
		Log.i("Cookie", PreferencesUtils.getString(getActivity(), "cookie")
				+ "");
		CookieSyncManager.getInstance().sync();

		w.loadUrl(urlT);

		return view;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		w.onPause();
		super.onPause();

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		w.onResume();
		super.onResume();

	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		view = null;

		if (w != null) {
			w.destroy();
			w = null;
		}
		super.onDestroyView();

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

		super.onDestroy();
	}

	public static interface GetWebView {
		public void getWebView(WebView webView);
	}



	

}
