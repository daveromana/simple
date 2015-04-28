package org.simple.main;

import java.util.ArrayList;
import java.util.Arrays;

import org.spring.simple.R;

import com.allthelucky.common.view.ImageIndicatorView;
import com.allthelucky.common.view.NetworkImageIndicatorView;
import com.allthelucky.common.view.ImageIndicatorView.OnItemChangeListener;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GuideImageIndicatorActivity extends Activity implements
		OnClickListener, OnItemChangeListener {

	private ImageIndicatorView imageIndicatorView;
	private Button goButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_indicator_guide);
		this.imageIndicatorView = (ImageIndicatorView) findViewById(R.id.guide_indicate_view);
		this.goButton = (Button) findViewById(R.id.button1);
		this.goButton.setVisibility(View.GONE);
		this.imageIndicatorView.setOnItemChangeListener(this);
		this.goButton.setOnClickListener(this);
		initView();

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
         Intent intent=new Intent();
         intent.setClass(this, MainActivity.class);
	     startActivity(intent);
	     this.finish();
	}

	@Override
	public void onPosition(int position, int totalCount) {
		// TODO Auto-generated method stub
		if (position == totalCount - 1) {
			goButton.setVisibility(View.VISIBLE);
		} else{
			goButton.setVisibility(View.GONE);
		}
	}

	private void initView() {
		this.imageIndicatorView
				.setupLayoutByDrawable(Arrays.asList(getLayouts()));
		String url[]={
				"https://lh3.googleusercontent.com/-PyggXXZRykM/URquh-kVvoI/AAAAAAAAAbs/hFtDwhtrHHQ/s1024/Colorado%252520River%252520Sunset.jpg",
				"https://lh3.googleusercontent.com/-ZAs4dNZtALc/URquikvOCWI/AAAAAAAAAbs/DXz4h3dll1Y/s1024/Colors%252520of%252520Autumn.jpg",
				"https://lh4.googleusercontent.com/-GztnWEIiMz8/URqukVCU7bI/AAAAAAAAAbs/jo2Hjv6MZ6M/s1024/Countryside.jpg"
				
		};
		//this.imageIndicatorView.setupLayoutByImageUrl(Arrays.asList(url));
		this.imageIndicatorView
				.setIndicateStyle(ImageIndicatorView.INDICATE_USERGUIDE_STYLE);
		this.imageIndicatorView.show();
	}

	private Integer[] getLayouts() {
		TypedArray ar = getResources().obtainTypedArray(R.array.guideImage);
		int len = ar.length();
		Integer[] resIds = new Integer[len];
		for (int i = 0; i < len; i++)
			resIds[i] = ar.getResourceId(i, 0);

		ar.recycle();
		return resIds;
	}

}
