package com.bankManagementSystem.main;

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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bankManagementSystem.util.BaseActivity;

/*
 用户登录进去的主界面                        
 */
public class UserActivity extends BaseActivity {
	private int images[] = { R.drawable.preferential_shop,
			R.drawable.financial_market, R.drawable.financing_calc };
	private String functions[] = { "用户操作", "金融行情", "理财计算器" };
	private GridView giGridView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uesr);
		setupView();
		giGridView.setAdapter(new GridViewAdapter(this));

		giGridView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						Intent intent = new Intent();
						switch (position) {
						case 0:
							intent.setClass(UserActivity.this,
									UserManActivity.class);

							break;
						case 1:
							intent.setClass(UserActivity.this,
									FinanicalMarketActivity.class);
							break;
						case 2:
							intent.setClass(UserActivity.this,
									FinanicalCalActivity.class);
							break;
						default:
							break;
						}
						UserActivity.this.startActivity(intent);
					}
				});

	}

	private void setupView() {
		giGridView = (GridView) findViewById(R.id.gGridView);

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

			return images.length;
		}

		public Object getItem(int position) {

			return images[position];
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
			holder.tv.setText(functions[position]);
			holder.iv.setImageResource(images[position]);
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
							UserActivity.this.exit();
						}
					});
			builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
	                  				
				}
			});
			builder.create().show();

			break;
		}
		return super.onOptionsItemSelected(item);
	}

}