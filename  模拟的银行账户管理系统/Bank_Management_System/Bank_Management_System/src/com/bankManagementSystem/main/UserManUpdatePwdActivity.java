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
import android.widget.Toast;

import com.bankManagementSystem.model.Session;
import com.bankManagementSystem.util.BaseActivity;
import com.bankManagementSystem.util.DBhelper;

/*
 用户修改密码                       
 */
public class UserManUpdatePwdActivity extends BaseActivity {
	private Button btn_back;
	private Button btn_modify;
	private EditText et_password;
	private EditText et_passwordConfirm;
	private TextView tv_title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uesrupdatepwd);
		setupView();
		
		btn_back.setText("返回");
		tv_title.setText("修改密码");
		
		btn_back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent backIntent = new Intent();
				backIntent.setClass(UserManUpdatePwdActivity.this,
						UserManActivity.class);
				UserManUpdatePwdActivity.this.startActivity(backIntent);

			}
		});
		btn_modify.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				boolean flag = false;
				String password = et_password.getText().toString();

				flag = DBhelper.getInstance(UserManUpdatePwdActivity.this)
						.modify(password, Session.id);
				if (flag) {
					Toast.makeText(UserManUpdatePwdActivity.this, "密码修改成功!",
							Toast.LENGTH_SHORT).show();
					Intent mainIntent = new Intent();
					mainIntent.setClass(UserManUpdatePwdActivity.this,
							MainActivity.class);
					UserManUpdatePwdActivity.this.startActivity(mainIntent);
				}
			}
		});

	}

	private void setupView() {
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_modify = (Button) findViewById(R.id.btn_modify);
		et_password = (EditText) findViewById(R.id.Password);
		et_passwordConfirm = (EditText) findViewById(R.id.PasswordConfirm);
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
							UserManUpdatePwdActivity.this.exit();
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