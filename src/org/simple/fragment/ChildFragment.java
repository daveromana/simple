package org.simple.fragment;

import java.util.ArrayList;

import org.simple.common.util.Tools;
import org.simple.main.MainActivity;
import org.spring.simple.R;

import com.astuetz.PagerSlidingTabStrip;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChildFragment extends Fragment{
	private PagerSlidingTabStrip child_TabStrip;
	private ViewPager child_Pager;
	private MyPagerAdapter adapter;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater,
    		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	View view=inflater.inflate(R.layout.child_fragment, container, false);
    	//((MainActivity)getActivity()).getResideMenu().addIgnoredView(view);
    	
    	child_Pager=(ViewPager) view.findViewById(R.id.child_pager);
    	child_TabStrip=(PagerSlidingTabStrip) view.findViewById(R.id.child_tabs);
    	adapter=new MyPagerAdapter(getChildFragmentManager());
    	child_Pager.setAdapter(adapter);
    	child_TabStrip.setViewPager(child_Pager);
    	
    	//((MainActivity)getActivity()).getResideMenu().addIgnoredView(view);
    	return view;
    }
    
    public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = getResources().getStringArray(R.array.child_titles);
        private ArrayList<Fragment> fragments=Tools.getNewInstance(getResources().getStringArray(R.array.child_fragments));
		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

	}
}
