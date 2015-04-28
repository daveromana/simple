package org.simple.main;

import java.util.List;

import org.simple.animtations.ZoomOutPageTransformer;
import org.simple.common.util.PreferencesUtils;
import org.simple.common.util.Tools;
import org.simple.fragment.LeftMenuFragment;
import org.simple.view.TabView;
import org.spring.simple.R;
import com.astuetz.PagerSlidingTabStrip;
import com.astuetz.PagerSlidingTabStrip.IconTabProvider;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.simple.dialog.ButtomPopuWindow;
/*import com.special.ResideMenu.ResideMenu;
 import com.special.ResideMenu.ResideMenu.OnMenuListener;
 import com.special.ResideMenu.ResideMenuItem;*/

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainActivity extends SlidingFragmentActivity {
	private ViewPager pager;
	private PagerSlidingTabStrip tabs;
	private ContactPagerAdapter contactAdapter;

	private TabView tabview;
	private int currentPager = 0;

	// private ResideMenu resideMenu;
	private LinearLayout layout;
	private int size;
	private boolean igore = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			currentPager = savedInstanceState.getInt("currentPager", 0);
		}
		setContentView(R.layout.main);
		// setUpMenu();
		initSlideMenu();
		initView();

	}

	private void initSlideMenu() {
		setBehindContentView(R.layout.left_menu_frame);
		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();
		//Fragment leftFrag = new LeftMenuFragment();
		
		t.replace(R.id.menu_frame,Tools.getNewInstance(new String[]{getResources().getString(R.string.leftFragMenu)}).get(0) );
		t.commit();
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
        sm.setFadeEnabled(true);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		
		//sm.setMode(SlidingMenu.LEFT_RIGHT);
		/*
		 * sm.setBackgroundResource(R.drawable.menu_background);
		 * sm.setBehindCanvasTransformer(new CanvasTransformer() {
		 * 
		 * @Override public void transformCanvas(Canvas canvas, float
		 * percentOpen) { // TODO Auto-generated method stub float scale =
		 * (float) (percentOpen * 0.25 + 0.75); canvas.scale(scale, scale,
		 * -canvas.getWidth() / 2, canvas.getHeight() / 2);
		 * 
		 * } }); sm.setAboveCanvasTransformer(new CanvasTransformer() {
		 * 
		 * @Override public void transformCanvas(Canvas canvas, float
		 * percentOpen) { // TODO Auto-generated method stub float scale =
		 * (float) (1 - percentOpen * 0.25); canvas.scale(scale, scale, 0,
		 * canvas.getHeight() / 2);
		 * 
		 * } });
		 */

		getSlidingMenu().setSecondaryMenu(R.layout.right_menu_frame);
		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame_two, Tools.getNewInstance(new String[]{getResources().getString(R.string.rightFragMen)}).get(0)).commit();
		// sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		getSlidingMenu().setOnOpenedListener(new OnOpenedListener() {

			@Override
			public void onOpened() {
				// TODO Auto-generated method stub
				findViewById(R.id.title_bar_right_menu).setBackgroundResource(
						R.drawable.skin_conversation_title_right_btn_selected);

			}
		});
		getSlidingMenu().setOnClosedListener(new OnClosedListener() {

			@Override
			public void onClosed() {
				// TODO Auto-generated method stub
				findViewById(R.id.title_bar_right_menu).setBackgroundResource(
						R.drawable.skin_conversation_title_right_btn);
			}
		});
		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
	}

	private void initView() {

		tabview = (TabView) findViewById(R.id.tabview);

		pager = tabview.getViewPager();

		tabs = tabview.getTab();

		contactAdapter = new ContactPagerAdapter(getSupportFragmentManager());
		size = contactAdapter.getCount();
		pager.setOffscreenPageLimit(4);
		pager.setAdapter(contactAdapter);

		tabs.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		tabs.setViewPager(pager);
		pager.setCurrentItem(currentPager);
		findViewById(R.id.title_bar_left_menu).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						showMenu();
					}
				});
		findViewById(R.id.title_bar_right_menu).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						showSecondaryMenu();
					}
				});
	}

	public class ContactPagerAdapter extends FragmentPagerAdapter implements
			IconTabProvider {
		public int[] getICONS() {
			TypedArray ar = getResources().obtainTypedArray(R.array.tab_image);
			int len = ar.length();
			int[] resIds = new int[len];
			for (int i = 0; i < len; i++)
				resIds[i] = ar.getResourceId(i, 0);

			ar.recycle();
			return resIds;
		}

		public int[] getSelectedICONS() {
			TypedArray ar = getResources().obtainTypedArray(
					R.array.tab_image_selected);
			int len = ar.length();
			int[] resIds = new int[len];
			for (int i = 0; i < len; i++)
				resIds[i] = ar.getResourceId(i, 0);

			ar.recycle();
			return resIds;
		}

		private final int[] SelectedICONS = getSelectedICONS();
		private final int[] ICONS = getICONS();

		private String[] urls = getResources().getStringArray(
				R.array.model_html);
		private List<Fragment> list;

		public ContactPagerAdapter(FragmentManager fm) {
			super(fm);
			list = Tools.getNewInstance(getResources().getStringArray(
					R.array.fragment));
		}

		@Override
		public int getPageIconResId(int position) {
			// TODO Auto-generated method stub
			return ICONS[position];
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub

			Fragment frag = list.get(arg0);

			Bundle bundle = new Bundle();
			bundle.putString("url", urls[arg0]);

			frag.setArguments(bundle);
			return frag;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return ICONS.length;
		}

		@Override
		public int getSelectedIconResId(int position) {
			// TODO Auto-generated method stub
			return SelectedICONS[position];
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub

		super.onSaveInstanceState(outState);
		outState.putInt("currentPager", pager.getCurrentItem());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				final ButtomPopuWindow selected = new ButtomPopuWindow(this);

				selected.showAtLocation(this.findViewById(R.id.tabview),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

				/*
				 * AlertDialog.Builder builder = new Builder(this);
				 * 
				 * builder.setMessage("确定要退出吗?"); builder.setTitle("提示");
				 * builder.setIcon(android.R.drawable.ic_dialog_alert);
				 * builder.setPositiveButton("确认", new
				 * DialogInterface.OnClickListener() { public void
				 * onClick(DialogInterface dialog, int which) {
				 * dialog.dismiss(); android.os.Process
				 * .killProcess(android.os.Process .myPid()); } });
				 * 
				 * builder.setNegativeButton( "取消", new
				 * android.content.DialogInterface.OnClickListener() { public
				 * void onClick(DialogInterface dialog, int which) {
				 * dialog.dismiss(); } });
				 * 
				 * builder.create().show();
				 */
				return false;
			}

		}
		return false;

	}

}
