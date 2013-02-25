package com.bankManagementSystem.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bankManagementSystem.model.User;
import com.bankManagementSystem.util.BaseActivity;
import com.bankManagementSystem.util.DBhelper;

public class RegisterConfirmActivity extends BaseActivity implements
		OnClickListener {
	private Button backButton;
	private Button confirmButton;
	private TextView tv_bankCardNumber;
	private TextView tv_IDCardType;
	private TextView tv_username;
	User user = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.self_confirm_view);
		setupView();
		user = (User) getIntent().getExtras().getSerializable("user");
		tv_bankCardNumber.setText(user.getAccount());
		tv_IDCardType.setText(user.getCertificate());
		tv_username.setText(user.getUsername());
		backButton.setOnClickListener(this);
		confirmButton.setOnClickListener(this);
	}

	private void setupView() {
		backButton = (Button) findViewById(R.id.returnButton);
		confirmButton = (Button) findViewById(R.id.confirmButton);
		tv_bankCardNumber = (TextView) findViewById(R.id.tv_bankCardNumber);
		tv_IDCardType = (TextView) findViewById(R.id.tv_IDCardType);
		tv_username = (TextView) findViewById(R.id.tv_username);

	}

	public void onClick(View v) {
		if (v == backButton) {
			finish();
		} else {
			boolean flag = false;
			flag = DBhelper.getInstance(this).save(user);
			if (flag) {
				Toast.makeText(this, "×¢²á³É¹¦", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
			}
		}
	}
}
