package com.example.left_right_extended;

import org.json.JSONArray;
import org.json.JSONObject;


import android.R.integer;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class BookStore_Top extends LinearLayout{

	LayoutInflater inflater;
	
	Context ctx;
	LinearLayout bookstore_top;
	JSONArray jsonList;
	
	public int CurrIndex;
	public String[] mMenuNames;
	public String[] mMenuValues;
	
	LeftRightExtendView layout;
	BookStore_TopLeft topLeft;
	BookStore_TopList toplist;
	LinearLayout top;
	
	TextView bookstore_toplist_text;
	TextView bookstore_toplist_btn;
	
//	BookStore_TopList list1;
//	BookStore_TopList list2;

	public BookStore_Top(Context context) {
		super(context);
		ctx = context;
		Init();
		Log.d("TAG", "测试点6---》");
	}
	private void Init()
	{
		mMenuNames = getResources().getStringArray(R.array.bookstore_top);
		mMenuValues = getResources().getStringArray(R.array.bookstore_top_value);
		
		setOrientation(VERTICAL);
		
		inflater = LayoutInflater.from(ctx);
		top = (LinearLayout) inflater.inflate(
				R.layout.bookstore_top, null);
		
		Log.d("TAG", "测试点7---》");
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, dip2px(35));
		this.addView(top,params);
		
		LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 0);
		params2.weight = 1;
		RelativeLayout container = new RelativeLayout(ctx);
		addView(container, params2);
		
		layout = new LeftRightExtendView(ctx);
		layout.setLeftRightWidth(120);
		
		
		container.addView(layout, RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
		
		
		
		topLeft = new BookStore_TopLeft(ctx, this);
		toplist = new BookStore_TopList(ctx, this);
		
		layout.addViewLeft(topLeft);
		layout.addViewCenter(toplist);
		
		layout.setLeftRightWidth(110);
		
		toplist.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (layout.isLeftExtended())
				{
					layout.showCenter();
					return true;
				}
				return false;
			}
		});
		
		bookstore_toplist_text = (TextView)top.findViewById(R.id.bookstore_toplist_text);
		bookstore_toplist_btn = (TextView)top.findViewById(R.id.bookstore_toplist_btn);
		
		OnClickListener onClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isLeftExtended())
				{
					showCenter();
				}
				else {
					showLeft();
				}
			}
		};
		
		bookstore_toplist_btn.setOnClickListener(onClickListener);
		bookstore_toplist_text.setOnClickListener(onClickListener);
		
		this.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				showLeft();
			}
		}, 500);
		
		
	}
	
	public void showLeft()
	{
		bookstore_toplist_btn.setText("收起");
		layout.showLeft();
	}
	
	public void showCenter()
	{
		bookstore_toplist_btn.setText("展开");
		layout.showCenter();
	}
	
	public boolean isLeftExtended()
	{
		return layout.isLeftExtended();
	}
	
	public int dip2px(float dipValue)
	{
		float scale = getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale + 0.5f);
	}
	
	public int px2dip(float pxValue){
		float scale = getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }  
	
	public void DataBind(int index)
	{
		if (isLeftExtended())
		{
			Log.d("TAG", "测试点8---》");
			showCenter();
		}
		CurrIndex = index;
		toplist.DataBind(mMenuValues[index], mMenuNames[index]);
		topLeft.notifyDataSetChanged();
	}
	
}
