/*******************************************************************************
 * Copyright 2011-2014 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.simple.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.simple.bean.ContentBean;
import org.simple.bean.ContentItem;
import org.simple.bean.LabelBean;
import org.simple.bean.LabelItem;
import org.simple.bean.ListItem;
import org.simple.common.util.Constants;
import org.spring.simple.R;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImageListFragment extends Fragment {

	public static final int INDEX = 0;

	String[] imageUrls = Constants.IMAGES;

	

	private ListView listView;
	

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fr_image_list, container, false);
		listView = (ListView) rootView.findViewById(android.R.id.list);
		 
		
		listView.setAdapter(new ImageAdapter());
		/*listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImagePagerActivity(position);
			}
		});*/
		return rootView;
	}


	
	class ImageAdapter extends BaseAdapter {

		private LayoutInflater inflater;
		private ArrayList<ListItem> data=new ArrayList<ListItem>();

		ImageAdapter() {
			inflater = LayoutInflater.from(getActivity());
			 for(int i=0;i<3;i++){
				  LabelBean label=new LabelBean();
				  label.setLable("标题:"+i);
				  LabelItem item=new LabelItem(label);
				  Log.i("标题", i+"");
				  data.add(item);
				  for(int j=0;j<4;j++){
					  ContentBean content=new ContentBean();
					  content.setConten_Text("内容："+j);
					  content.setImage_Url(imageUrls[4*i+j]);
					  ContentItem cItem=new ContentItem(content);
					  Log.i("内容", j+"");
					  data.add(cItem);
				  }
			  }
			 Log.i("数量", data.size()+"");
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view=data.get(position).getView(getActivity(), convertView, inflater, parent);
		
			return view;
		}
		@Override
		public boolean isEnabled(int position) {
			// TODO Auto-generated method stub
			return data.get(position).isClickable();
		}
	}

	
}