package org.simple.common.util;

import java.util.ArrayList;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.util.Log;

public class Tools {

	/**
	 * 
	 * 获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap网络3：net网络 
	 * 
	 */
	
	private static int CMNET=3;
	private static int CMWAP=2;
	private static int WIFI=1;
	
	public static ArrayList<Fragment> getNewInstance(String[] className) {
		ArrayList<Fragment> objs = new ArrayList<Fragment>();
		try {
			for (String temp : className) {
				objs.add((Fragment) Class.forName(temp).newInstance());
			}

			return objs;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author sky Email vipa1888@163.com QQ:840950105 获取当前的网络状态 -1：没有网络
	 *         1：WIFI网络2：wap网络3：net网络
	 * @param context
	 * @return
	 */
	public static int getAPNType(Context context) {
		int netType = -1;
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			Log.e("networkInfo.getExtraInfo()",
					"networkInfo.getExtraInfo() is "
							+ networkInfo.getExtraInfo());
			if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
				netType = CMNET;
			} else {
				netType = CMWAP;
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = WIFI;
		}
		return netType;
	}

}
