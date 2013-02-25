package com.bankManagementSystem.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bankManagementSystem.util.BaseActivity;

public class RegisterTipActivity extends BaseActivity implements OnClickListener {
	private Button btn_return;
	private Button btn_next;
	public static int RESULT = 1;
	public static int REQUEST = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.self_reg_tip_view);
		setupView();
		btn_next.setOnClickListener(this);
		btn_return.setOnClickListener(this);
	}

	private void setupView() {
		btn_return = (Button) findViewById(R.id.returnButton);
		btn_next = (Button) findViewById(R.id.nextButton);

	}

	public void onClick(View v) {
		if (v == btn_next) {

			Intent nextIntent = new Intent(RegisterTipActivity.this,
					RegisterInputActivity.class);
			RegisterTipActivity.this
					.startActivityForResult(nextIntent, REQUEST);
		} else {

			setResult(RESULT);
			finish();
		}
	}
}
