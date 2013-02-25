package com.bankManagementSystem.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.bankManagementSystem.util.BaseActivity;
/*
                                         应用程序的启动画面
*/
public class StartActivity extends BaseActivity {
	/** Called when the activity is first created. */

	Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉title。
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏显示。
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		handler.postDelayed(runnable, 1000);
	}

	Runnable runnable = new Runnable() {

		public void run() {
			Intent intent = new Intent(StartActivity.this, MainActivity.class);
			StartActivity.this.startActivity(intent);
			StartActivity.this.finish();
		}
	};

}