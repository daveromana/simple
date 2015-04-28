package org.simple.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface ListItem {
	
    
    public boolean isClickable();
     
    public View getView(Context context, View convertView, LayoutInflater inflater,ViewGroup parent);

}
