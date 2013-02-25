package com.example.left_right_extended;


import org.json.JSONObject;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class BookStore_BookList extends RelativeLayout{
	LinearLayout layout;
	LayoutInflater inflater;
	Context ctx;
	String adPos;
	ListView lstBooklist;
	BookAdapter adapter;
	String[] booknames = {"笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1","笑傲江湖1"};
	
	public BookStore_BookList(Context context) {
		super(context);
		ctx = context;
		Init();
	}
	
	public BookStore_BookList(Context context, AttributeSet attrs) {
		super(context, attrs);
		ctx = context;
		Init();
	}
	
	private void Init() {
		inflater = LayoutInflater.from(ctx);
		layout = (LinearLayout) inflater.inflate(
				R.layout.bookstore_booklist, null);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
		this.addView(layout,params);
		
		lstBooklist = (ListView)layout.findViewById(R.id.lstBooklist);
		adapter = new BookAdapter();
		lstBooklist.setAdapter(adapter);
		
	}
	
	private OnBookListScrollListener onScrollListener;
	public interface OnBookListScrollListener
	{
		void onScroll(AbsListView view,int firstVisibleItem,
				int visibleItemCount, int totalItemCount);
		void onTouch(View v, MotionEvent event);
	}
	
	
	public void SetOnScrollListener(OnBookListScrollListener listener)
	{
		onScrollListener = listener;
	}
	
	public void SetPos(String pos)
	{
		adPos = pos;
	}
	
	
private class BookAdapter extends BaseAdapter {
		
		
		
		public BookAdapter()
		{
		}

		@Override
		public int getCount() {
			
			return booknames.length;
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
				convertView = inflater.inflate(R.layout.bookstore_booklist_item, null);
				holder = new ViewHolder();
				holder.bookstore_booklist_item_cover = (ImageView) convertView.findViewById(R.id.bookstore_booklist_item_cover);
				holder.bookstore_booklist_item_name = (TextView) convertView.findViewById(R.id.bookstore_booklist_item_name);
				holder.bookstore_booklist_item_author = (TextView) convertView.findViewById(R.id.bookstore_booklist_item_author);
				holder.bookstore_booklist_item_chapter = (TextView) convertView.findViewById(R.id.bookstore_booklist_item_chapter);
				holder.bookstore_booklist_item_time = (TextView) convertView.findViewById(R.id.bookstore_booklist_item_time);
				holder.bookstore_booklist_item_extra = (TextView) convertView.findViewById(R.id.bookstore_booklist_item_extra);
				holder.bookstore_booklist_item_index = (TextView) convertView.findViewById(R.id.bookstore_booklist_item_index);
				holder.bookstore_booklist_item_wordcount=(TextView)convertView.findViewById(R.id.bookstore_booklist_item_wordcount);
				
				convertView.setTag(holder);
			}
			holder = (ViewHolder) convertView.getTag();
			try {
				holder.bookstore_booklist_item_name.setText(booknames[position]);
				holder.bookstore_booklist_item_author.setText("唐家三少");
				holder.bookstore_booklist_item_chapter.setText("第一章");
				
				holder.bookstore_booklist_item_time.setText("2012-12-22");
				holder.bookstore_booklist_item_extra.setText(booknames[position]);
				holder.bookstore_booklist_item_index.setText(booknames[position]);
				holder.bookstore_booklist_item_wordcount.setText("10000");
			    
				convertView.setBackgroundResource(R.drawable.listitembg2);
				convertView.setPadding(dip2px(10), dip2px(5), 0, dip2px(5));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return convertView;
		}
	}
	
	private static class ViewHolder {
		public ImageView bookstore_booklist_item_cover;
		public TextView bookstore_booklist_item_name;
		public TextView bookstore_booklist_item_author;
		public TextView bookstore_booklist_item_chapter;
		public TextView bookstore_booklist_item_time;
		public TextView bookstore_booklist_item_index;
		public TextView bookstore_booklist_item_extra;
		public TextView bookstore_booklist_item_wordcount;
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
	
	
	public  String FixWords(int words)
	{
		if (words > 10000)
		{
			return (words / 10000) + "万字";
		}
		return words + "字";
	}
	
	
	public  String FixTime(long time)
	{
		long n = System.currentTimeMillis() - time;
		if (n < 1000l * 60) {
			return "1分钟内";
		} else if (n < 1000l * 60 * 60) {
			long min = n / (1000l * 60);
			return min + "分钟前";
		} else if (n < 1000l * 60 * 60 * 24) {
			long hour = n / (1000l * 60 * 60);
			return hour + "小时前";
		} else if (n < 1000l * 60 * 60 * 24 * 30) {
			long day = n / (1000l * 60 * 60 * 24);
			return day + "天前";
		} else if (n < 1000l * 60 * 60 * 24 * 30 * 12){
			long month = n / (1000l * 60 * 60 * 24 * 30);
			return month + "月前";
		} else {
			return "很久以前";
		}
	}

}
