package org.simple.bean;

import org.spring.simple.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public final class LabelItem implements ListItem {
	private LabelBean bean;
	public LabelItem(LabelBean bean){
		this.bean=bean;
	}


	@Override
	public boolean isClickable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getView(Context context, View convertView,
			LayoutInflater inflater, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=convertView;
		ViewHolder2 holder=null;
		/*if(convertView==null){
			view=inflater.inflate(R.layout.itemlabel_layout, null);
			holder=new ViewHolder2();
			holder.label=(TextView) view.findViewById(R.id.label_name);
			view.setTag(holder);
		}else*/ if(convertView!=null&&convertView.getId()==R.id.item_label){
			holder=(ViewHolder2) view.getTag();
		}else{
			view=inflater.inflate(R.layout.itemlabel_layout, null);
			holder=new ViewHolder2();
			holder.label=(TextView) view.findViewById(R.id.label_name);
			view.setTag(holder);
		}
		holder.label.setText(bean.getLable());
		
		
		return view;
	}
	private static class ViewHolder2 {
		TextView label;
	}

}
