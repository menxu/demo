package com.example.left_right_extended;




import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	LinearLayout bookstore_container;
	BookStore_Top viewTop;
	Context ctx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = MainActivity.this;
        Log.d("TAG", "≤‚ ‘µ„1---°∑");
        bookstore_container = (LinearLayout)findViewById(R.id.bookstore_container);
        bookstore_container.removeAllViews();
        Log.d("TAG", "≤‚ ‘µ„2---°∑");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        if (viewTop == null)
		{
			viewTop = new BookStore_Top(ctx);
			Log.d("TAG", "≤‚ ‘µ„3---°∑");
		}
		bookstore_container.addView(viewTop,params);
		Log.d("TAG", "≤‚ ‘µ„4---°∑");
		
		viewTop.DataBind(0);
		Log.d("TAG", "≤‚ ‘µ„5---°∑");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
