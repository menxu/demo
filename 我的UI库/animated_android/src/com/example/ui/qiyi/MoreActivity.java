package com.example.ui.qiyi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.anim.R;

public class MoreActivity extends Activity {
	TextView mTitleView;

	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_activity);
		prepareView();
		mTitleView.setText(R.string.category_more);
	}

	private void prepareView() {
		mTitleView = (TextView) findViewById(R.id.title_text);
	}
}
