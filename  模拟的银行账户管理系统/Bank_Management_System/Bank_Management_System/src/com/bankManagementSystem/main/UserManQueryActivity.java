package com.bankManagementSystem.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.bankManagementSystem.model.Session;
import com.bankManagementSystem.model.User;
import com.bankManagementSystem.util.BaseActivity;
import com.bankManagementSystem.util.DBhelper;

/*
 用户查询                       
 */
public class UserManQueryActivity extends BaseActivity {
	private TextView tv_username;
	private TextView tv_identify;
	private TextView tv_phone;
	private TextView tv_total;
	private Button btn_back;
	private TextView tv_title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uesrquery);
		setupView();
		User user = DBhelper.getInstance(this).getUser(Session.name,
				Session.password);
		tv_username.setText(user.getUsername());
		tv_identify.setText(user.getIndetify());
		tv_phone.setText(user.getPhone());
		tv_total.setText(user.getTotal() + "");

		btn_back.setText("返回");
		tv_title.setText("用户查询");

		btn_back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent backIntent = new Intent();
				backIntent.setClass(UserManQueryActivity.this,
						UserManActivity.class);
				UserManQueryActivity.this.startActivity(backIntent);

			}
		});

	}

	private void setupView() {
		tv_username = (TextView) findViewById(R.id.userquery_name);
		tv_identify = (TextView) findViewById(R.id.userquery_indetify);
		tv_phone = (TextView) findViewById(R.id.userquery_phone);
		tv_total = (TextView) findViewById(R.id.userquery_total);
		btn_back = (Button) findViewById(R.id.btn_back);
		tv_title = (TextView) findViewById(R.id.tv_title);
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
							UserManQueryActivity.this.exit();
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