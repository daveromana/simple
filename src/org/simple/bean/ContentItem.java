package org.simple.bean;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.spring.simple.R;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public final class ContentItem implements ListItem {
	private ContentBean bean;
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	public ContentItem(ContentBean bean) {
		this.bean = bean;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();
	}

	@Override
	public boolean isClickable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public View getView(Context context, View convertView,
			LayoutInflater inflater, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=convertView;
		
		 ViewHolder holder = null;
	/*	if (convertView == null) {
			view = inflater.inflate(R.layout.item_list_image, 
					null);
			
			holder = new ViewHolder();
			holder.image = (ImageView) view.findViewById(R.id.image);
			holder.text = (TextView) view.findViewById(R.id.text);
			view.setTag(holder);

		} else*/ if(convertView!=null && convertView.getId()==R.id.item_content){
			
			holder = (ViewHolder) view.getTag();
		}else{
			view = inflater.inflate(R.layout.item_list_image, 
					null);
			
			holder = new ViewHolder();
			holder.image = (ImageView) view.findViewById(R.id.image);
			holder.text = (TextView) view.findViewById(R.id.text);
			view.setTag(holder);
		}
		
		holder.text.setText(bean.getConten_Text());
		ImageLoader.getInstance().displayImage(bean.getImage_Url(),
				holder.image, options,animateFirstListener);
		
		return view;
	}

	private static class ViewHolder 
	{
		TextView text;
		ImageView image;
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

}
