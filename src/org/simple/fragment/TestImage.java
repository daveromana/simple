package org.simple.fragment;

import org.simple.common.util.Constants;
import org.spring.simple.R;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;

import android.widget.ImageView;


public class TestImage extends Fragment{
	private Gallery gallery;
	private ImageLoader imageLoader=ImageLoader.getInstance();
    private DisplayImageOptions options;
    String[] imageUrls = Constants.IMAGES;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_stub)
		.showImageForEmptyUri(R.drawable.ic_empty)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
    }
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.ac_image_gallery, container, false);
		gallery=(Gallery) rootView.findViewById(R.id.gallery);
	    gallery.setAdapter(new ImageAdapter());
		
		return rootView;
	}
	private class ImageAdapter extends BaseAdapter{
		
		private LayoutInflater inflater;
         ImageAdapter() {
			// TODO Auto-generated constructor stub
        	 inflater=LayoutInflater.from(getActivity());
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageUrls.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView imageView=(ImageView) convertView;
			if(imageView==null){
				imageView=(ImageView) inflater.inflate(R.layout.item_gallery_image, parent, false);
			}
			ImageLoader.getInstance().displayImage(imageUrls[position], imageView,options);
			return imageView;
		}
		
	}

}
