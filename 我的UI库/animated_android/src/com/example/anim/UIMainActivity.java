package com.example.anim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anima.activityswitch.Activity1;
import com.example.ui.bookpage.BookpageActivity;
import com.example.ui.bookshelf.BookshelfReaderActivity;
import com.example.ui.bubblechat.ChatActivity;
import com.example.ui.coverflow.CoverFlowActivity;
import com.example.ui.drawroll.DrawRollActivity;
import com.example.ui.lancher3d.Launcher3dMainActivity;
import com.example.ui.listviewgridview.ListViewActivity;
import com.example.ui.loadingview.LoadingViewMainActivity;
import com.example.ui.lockscreen.BitmapLockScrActivity;
import com.example.ui.multilayer.MultiLayerMenuActivity;
import com.example.ui.path.PathButtonActivity;
import com.example.ui.photostore.Gallery;
import com.example.ui.pickview.WheelDemo;
import com.example.ui.qiyi.QiyiMainActivity;
import com.example.ui.qqlogin.QqLoginActivity;
import com.example.ui.qqtab.QqtabActivity;
import com.example.ui.radiogroup.RelativeRadioGroupActivity;
import com.example.ui.ucweb.UcwebDemoActivity;
import com.example.ui.viewpager.ViewPagerActivity;
import com.example.ui.waterfall.WaterfallMainActivity;

public class UIMainActivity extends Activity implements OnItemClickListener,
		OnItemSelectedListener {

	private ListView mylist;
	private ArrayAdapter<String> arrayAdapter;
	private String[] contentString = new String[] { "Activity切换动画",
			" ViewPager多页面滑动切换以及动画效果", "仿QQtab切换动画", "仿Path照片分享软件的Button动画效果",
			"图片浏览器PhotoStore", " 自定义PopupWindow动画效果", "多层菜单的实例",
			"3D launcher效果", "瀑布流加载图片效果", "仿三星9100锁屏界面", "自定义RadioGroup效果",
			"仿苹果的pickview", "仿ucweb的菜单", "放大滚动效果", "阅读器页面翻页效果", "书架效果",
			"气泡式聊天模式", "启动界面效果","QQ登录界面","奇艺主界面","Listview嵌入Gridview" };

	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	private void init() {
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_single_choice, contentString);
		mylist = (ListView) findViewById(R.id.ListView01);
		mylist.setAdapter(arrayAdapter);
		mylist.setOnItemClickListener(this);
		mylist.setOnItemSelectedListener(this);
		mylist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

	 
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	 
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	 
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		setTitle(arrayAdapter.getItem(arg2));
		mylist.setItemChecked(arg2, true);
	}

	 
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	 
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
		Class<?> cls = null;
		if (index == 0) {
			cls = Activity1.class;
		} else if (index == 1) {
			cls = ViewPagerActivity.class;
		} else if (index == 2) {
			cls = QqtabActivity.class;
		} else if (index == 3) {
			cls = PathButtonActivity.class;
		} else if (index == 4) {
			cls = Gallery.class;
		} else if (index == 5) {
			cls = DrawRollActivity.class;
		} else if (index == 6) {
			cls = MultiLayerMenuActivity.class;
		} else if (index == 7) {
			cls = Launcher3dMainActivity.class;
		} else if (index == 8) {
			cls = WaterfallMainActivity.class;
		} else if (index == 9) {
			cls = BitmapLockScrActivity.class;
		} else if (index == 10) {
			cls = RelativeRadioGroupActivity.class;
		} else if (index == 11) {
			cls = WheelDemo.class;
		} else if (index == 12) {
			cls = UcwebDemoActivity.class;
		} else if (index == 13) {
			cls = CoverFlowActivity.class;
		} else if (index == 14) {
			cls = BookpageActivity.class;
		} else if (index == 15) {
			cls = BookshelfReaderActivity.class;
		} else if (index == 16) {
			cls = ChatActivity.class;
		} else if (index == 17) {
			cls = LoadingViewMainActivity.class;
		} else if (index == 18){
			cls = QqLoginActivity.class;
		} else if (index == 19 ){
			cls = QiyiMainActivity.class;
		} else if (index == 20){
			cls = ListViewActivity.class;
		}
		if (cls != null) {
			Intent intent = new Intent();
			intent.setClass(this, cls);
			startActivity(intent);
		}
	}

}
