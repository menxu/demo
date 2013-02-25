package com.bankManagementSystem.util;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;


	public class BaseActivity extends Activity {
		public static List<Activity> allaActivities = new ArrayList<Activity>();
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);

		}

		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			allaActivities.add(this);
		}

		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			allaActivities.remove(this);
		}

		public void exit() {
			for (Activity activity : allaActivities) {
				activity.finish();
			}
			allaActivities.clear();
		}
	}

