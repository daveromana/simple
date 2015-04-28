package org.simple.main;

import org.simple.common.util.PreferencesUtils;
import org.spring.simple.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreenActivity extends Activity {
	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		image = (ImageView) findViewById(R.id.splashscreen_image);
		image.setBackgroundResource(getSplashscreen());
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			
				if(!PreferencesUtils.getBoolean(SplashScreenActivity.this, "isFirst")){
					Intent intent =new Intent(SplashScreenActivity.this,GuideImageIndicatorActivity.class);
					startActivity(intent);
					PreferencesUtils.putBoolean(SplashScreenActivity.this, "isFirst", true);
					SplashScreenActivity.this.finish();
				}else{
					Intent intent =new Intent(SplashScreenActivity.this,MainActivity.class);
					startActivity(intent);
					SplashScreenActivity.this.finish();
				}
			}
		}, 2100);
	}

	@SuppressLint("Recycle")
	private int getSplashscreen() {
		TypedArray array = getResources()
				.obtainTypedArray(R.array.splashscreen);
		return array.getResourceId(0, 0);
	}

}
