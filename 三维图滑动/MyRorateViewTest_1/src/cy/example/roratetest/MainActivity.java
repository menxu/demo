package cy.example.roratetest;

import cy.rorate3d.view.CYRorateView;
import cy.rorate3d.view.CYRorateViewMeasuredObserver;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements CYRorateViewMeasuredObserver{
	private Context context;
	ListView listView;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
     
      setContentView(R.layout.activity_main);
      context = this;
      CYRorateView myView = (CYRorateView) findViewById(R.id.myView);
      View view1 =  LayoutInflater.from(this).inflate(R.layout.view1, null);
      View view2 = LayoutInflater.from(this).inflate(R.layout.view2, null);
      View view3 = LayoutInflater.from(this).inflate(R.layout.view3, null);
      View view4 = LayoutInflater.from(this).inflate(R.layout.view4, null);
     
      myView.addView(view1);
      myView.addView(view2);
      myView.addView(view3);
      myView.addView(view4);
      
      //这个方法可以设置旋转角度，不设置的话默认为90度
      myView.setRoateAngle(90);
     
      listView = (ListView) view2.findViewById(R.id.list);
      Adapter adapter = new Adapter();
      listView.setAdapter(adapter);
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				Toast.makeText(context, arg2 +"", Toast.LENGTH_SHORT).show();
			}
		});
    }
    
    class Adapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 16;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
			TextView txt = (TextView) convertView.findViewById(R.id.txt);
			txt.setText(position+1+"");
			return convertView;
		}
    	
    }
    /**
     * 里面有listview之类的好像高度会不对，继承下CYRorateViewMeasuredObserver重新设置高度，
     * 目前只能这样，还没找到什么更好的方法
     */
	@Override
	public void getRorateViewMeasured(int width, int height) {
		ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
		layoutParams.height = height;
		listView.setLayoutParams(layoutParams);
		
	}
   
}
