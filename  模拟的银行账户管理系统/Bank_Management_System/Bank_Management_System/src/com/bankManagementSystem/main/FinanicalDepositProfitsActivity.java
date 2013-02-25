package com.bankManagementSystem.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.bankManagementSystem.util.BaseActivity;

/*
 存款利润                     
 */
public class FinanicalDepositProfitsActivity extends BaseActivity {
	private TextView tv;
	private Spinner spinner;
	private ListView listView;
	private Button btn;
	List<HashMap<String, String>> dataList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.depositprofits);
		setupView();
		ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
				.createFromResource(this, R.array.currency,
						android.R.layout.simple_spinner_item);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(spinnerAdapter);
		tv.setText("存款利润");
        btn.setText("返回");
		prepareData();

		SimpleAdapter listviewAdapter = new SimpleAdapter(this, dataList,
				R.layout.listview_item, new String[] { "time", "profit" },
				new int[] { R.id.tv_time, R.id.tv_profits });
		listView.setAdapter(listviewAdapter);
		
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent backIntent = new Intent();
				backIntent.setClass(FinanicalDepositProfitsActivity.this,
						FinanicalMarketActivity.class);
				FinanicalDepositProfitsActivity.this.startActivity(backIntent);

			}
		});

	}

	private void setupView() {
		tv = (TextView) findViewById(R.id.tv_title);
		btn = (Button) findViewById(R.id.btn_back);
		spinner = (Spinner) findViewById(R.id.spinner_1);
		listView = (ListView) findViewById(R.id.listview_1);
	}

	private void prepareData() {
		dataList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = null;
		map = new HashMap<String, String>();
		map.put("time", "活期");
		map.put("profit", "0.5000");
		dataList.add(map);
		map = new HashMap<String, String>();
		map.put("time", "通知存款一天");
		map.put("profit", "0.9500");
		dataList.add(map);
		map = new HashMap<String, String>();
		map.put("time", "通知存款七天");
		map.put("profit", "1.4900");
		dataList.add(map);

	}

	static class ViewHolder {
		TextView tv;
		ImageView iv;

	}

	public class GridViewAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public GridViewAdapter(Context context) {
			inflater = LayoutInflater.from(context);

		}

		public int getCount() {

			return 4;
		}

		public Object getItem(int position) {

			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		// 这个GridView有几项，就调用几次getView()..判断有几项，是通过getCount（）方法。。
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.gv_item, null);
				holder = new ViewHolder();
				holder.tv = (TextView) convertView
						.findViewById(R.id.gv_item_appname);
				holder.iv = (ImageView) convertView
						.findViewById(R.id.gv_item_icon);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			// holder.tv.setText(functions[position]);
			// holder.iv.setImageResource(images[position]);
			return convertView;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.containermenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.set:
			startActivity(new Intent(Settings.ACTION_SETTINGS));

			break;

		case R.id.exit:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("你确定要退出吗？");
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							FinanicalDepositProfitsActivity.this.exit();
						}
					});
			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {

						}
					});
			builder.create().show();

			break;
		}
		return super.onOptionsItemSelected(item);
	}

}