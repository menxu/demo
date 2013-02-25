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
import android.widget.EditText;
import android.widget.TextView;

import com.bankManagementSystem.util.BaseActivity;

/*
 用户转账                      
 */
public class UserManTransferAccountsActivity extends BaseActivity {
	private Button btn_back;
	private Button btn_next;
	private TextView tv_title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uesrtransferaccount);
		setupView();
		btn_back.setText("返回");
		tv_title.setText("用户转账");

		btn_back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent backIntent = new Intent();
				backIntent.setClass(UserManTransferAccountsActivity.this,
						UserManActivity.class);
				UserManTransferAccountsActivity.this.startActivity(backIntent);

			}
		});

	}

	private void setupView() {
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_next = (Button) findViewById(R.id.btn_next);
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
							UserManTransferAccountsActivity.this.exit();
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