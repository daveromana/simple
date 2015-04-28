package com.simple.dialog;

import org.spring.simple.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ButtomPopuWindow extends PopupWindow {
	private Button btn_delete, btn_sure;
	private TextView title;
	private View bttomMenuView;

	public ButtomPopuWindow(final Activity context) {
		super(context);
		LayoutInflater infalter = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		bttomMenuView = infalter.inflate(R.layout.actionsheet, null);
		title = (TextView) bttomMenuView.findViewById(R.id.title);
		btn_delete = (Button) bttomMenuView.findViewById(R.id.btn_delete);
		btn_sure = (Button) bttomMenuView.findViewById(R.id.cancel);
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				switch (view.getId()) {
				case R.id.btn_delete:
					dismiss();
					context.finish();
					break;

				case R.id.cancel:
					dismiss();
					break;
				}

			}
		};

		btn_delete.setOnClickListener(listener);

		btn_sure.setOnClickListener(listener);

		this.setContentView(bttomMenuView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		this.setAnimationStyle(android.R.style.Animation_Toast);
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		this.setBackgroundDrawable(dw);
		bttomMenuView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int height = bttomMenuView.findViewById(R.id.top).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});

	}

}
