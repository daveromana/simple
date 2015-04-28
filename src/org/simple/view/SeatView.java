package org.simple.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SeatView extends View {
	Paint paint = new Paint();
	boolean[][] status = new boolean[5][6];

	boolean isredraw = false;
	int row;
	int line;

	public SeatView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub

		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 6; y++) {
				if (status[x][y]) {
					paint.setColor(Color.RED);
				} else {
					paint.setColor(Color.GRAY);
				}
				canvas.drawRect(y * 50, x * 50, y * 50 + 40, x * 50 + 40, paint);
			}
		}
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			float y = event.getY();
			float x = event.getX();
			row = (int) (y / 50);
			line = (int) (x / 50);
			Log.i("行列数目", row + "/" + line);
			if(status[row][line]){
				status[row][line]=false;
			}else{
				status[row][line]=true;
			}
			
			invalidate();
		}

		return true;
	}

}
