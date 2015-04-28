package org.simple.http;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaScriptObject {
	private Activity context;
	
	public JavaScriptObject(Activity context){
		this.context=context;
	}
/*	js调用此函数跳转activity*/
	
	@JavascriptInterface
	public void openActivty(String className){
		Intent intent=new Intent();
		intent.setClassName(context, className);
		context.startActivity(intent);
	}
	
	/*js调用此函数Toast消息显示*/
	@JavascriptInterface
	public void showToast(final String message){
		
		Runnable runnable = new Runnable() {
			public void run() {
				Toast.makeText(context, message,Toast.LENGTH_SHORT).show();
			}
		};
		context.runOnUiThread(runnable);
	}

}
