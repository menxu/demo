package com.example.left_right_extended;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;

public class BookStore_TopList extends RelativeLayout {
	
	LayoutInflater inflater;
	LinearLayout bookstore_container;
	BookStore_Top top;
	Context ctx;
	
	public BookStore_TopList(Context context,BookStore_Top view) {
		super(context);
		top = view;
		ctx = context;
		inflater = LayoutInflater.from(context);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.bookstore_toplist, null);
		
		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		this.addView(layout,layoutParams);
		
		
		bookstore_container = (LinearLayout)layout.findViewById(R.id.bookstore_container);
		
		
	}
	
	
	public void DataBind(String topid,String name)
	{
		bookstore_container.removeAllViews();
		BookStore_BookList booklist = new BookStore_BookList(ctx);
		bookstore_container.addView(booklist, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		top.bookstore_toplist_text.setText(name);
	}
	
//	
}
