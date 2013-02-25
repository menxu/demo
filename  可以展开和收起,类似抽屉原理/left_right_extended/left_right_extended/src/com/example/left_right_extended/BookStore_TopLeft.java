package com.example.left_right_extended;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class BookStore_TopLeft extends LinearLayout{
	
	MainActivity ctx;
	LinearLayout layout;
	LayoutInflater inflater;
	
	ListView lstCategorylist;
	
	CategoryAdapter adapter;
	
	BookStore_Top bookStoreTop;

	public BookStore_TopLeft(Context context,BookStore_Top view) {
		super(context);
		bookStoreTop = view;
		
		ctx = (MainActivity)context;
		inflater = LayoutInflater.from(ctx);
		layout = (LinearLayout) inflater.inflate(
				R.layout.bookstore_top_left, null);
		
		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		this.addView(layout,layoutParams);
		
		adapter = new CategoryAdapter();
		
		lstCategorylist = (ListView)layout.findViewById(R.id.lstCategorylist);
		lstCategorylist.setAdapter(adapter);
		lstCategorylist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				adapter.notifyDataSetChanged();
				bookStoreTop.DataBind(position);
			}
		});
	}
	
	public void notifyDataSetChanged()
	{
		if (adapter != null)
		{
			adapter.notifyDataSetChanged();
		}
	}

	private class CategoryAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return bookStoreTop.mMenuNames.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				// 给ImageView设置资源
				convertView = inflater.inflate(
						R.layout.bookstore_category_item, null);
				holder = new ViewHolder();
				holder.bookstore_category_item_name = (TextView)convertView.findViewById(R.id.bookstore_category_item_name);
				holder.bookstore_category_line = convertView.findViewById(R.id.bookstore_category_line);
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			
			holder.bookstore_category_item_name.setText(bookStoreTop.mMenuNames[position]);
			
			if (bookStoreTop.CurrIndex == position)
			{
				convertView.setEnabled(false);
				holder.bookstore_category_item_name.setTextColor(Color.BLACK);
				holder.bookstore_category_line.setVisibility(View.GONE);
				holder.bookstore_category_item_name.setPadding(dip2px(5), dip2px(10), 0, dip2px(5));
			}
			else {
				convertView.setEnabled(true);
				holder.bookstore_category_item_name.setTextColor(Color.BLACK);
				holder.bookstore_category_line.setVisibility(View.VISIBLE);
				holder.bookstore_category_item_name.setPadding(dip2px(0), dip2px(10), 0, dip2px(5));
			}
			
			
			
			return convertView;
		}
		
	}
	
	private final class ViewHolder
	{
		public TextView bookstore_category_item_name;
		public View bookstore_category_line;
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
	
}
