package org.simple.view;

import org.spring.simple.R;

import com.astuetz.PagerSlidingTabStrip;

import android.annotation.SuppressLint;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TabView extends RelativeLayout {
	private String tab_Postions = "bottom";
	private LinearLayout tab;
	private ViewPager viewPager;
	private RelativeLayout.LayoutParams tabLayout, viewPagerLayout;
    private boolean zShouldExpand=false;
	private PagerSlidingTabStrip tabStrip;
	public TabView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	@SuppressLint("InflateParams")
	public TabView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.customerPagerSlidingTab);
		tab_Postions = ta
				.getString(R.styleable.customerPagerSlidingTab_zposition_settings);
        zShouldExpand=ta.getBoolean(R.styleable.customerPagerSlidingTab_zShouldExpand, zShouldExpand);
        
		ta.recycle();

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		tab = (LinearLayout) inflater.inflate(R.layout.tab, null);

		tab.setId(1);

		viewPager = new ViewPager(context);
		viewPager.setId(2);
		tabStrip=(PagerSlidingTabStrip) tab.findViewById(R.id.tabs);
		tabStrip.setShouldExpand(zShouldExpand);
		
		viewPagerLayout = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);

		tabLayout = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

		if ("top".equals(tab_Postions.trim())) {
			tabLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP,
					RelativeLayout.TRUE);
			viewPagerLayout.addRule(RelativeLayout.BELOW, tab.getId());
			// viewPagerLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
			// viewPagerLayout.addRule(RelativeLayout.BELOW, 1);

			addView(tab, tabLayout);
			addView(viewPager, viewPagerLayout);

		} else if ("bottom".equals(tab_Postions.trim())) {
			tabLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
					RelativeLayout.TRUE);
			viewPagerLayout.addRule(RelativeLayout.ABOVE, tab.getId());
			// viewPagerLayout.addRule(RelativeLayout.ABOVE, 1);
			addView(tab, tabLayout);
			addView(viewPager, viewPagerLayout);
		}

	}

	public ViewPager getViewPager() {
		return viewPager;
	}

	public PagerSlidingTabStrip getTab() {

		return tabStrip;
	}

}
