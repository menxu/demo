package com.bankManagementSystem.main;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bankManagementSystem.model.User;
import com.bankManagementSystem.util.BaseActivity;

public class RegisterInputActivity extends BaseActivity implements OnClickListener {
	private Button btn_return;
	private Button btn_next;
	private Spinner spinner;
	private EditText et_Card;
	private EditText et_account;
	private EditText et_secondaccount;
	private EditText et_name;
	private EditText et_phone;
	private EditText et_password;
	private EditText et_secondpassword;
	private EditText et_address;
	public static int RESULT = 1;
	public static int REQUEST = 0;
	private String[] certificates = new String[] { "身份证", "学生证", "出生证", "护照",
			"其他证件" };
	String certificate = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.self_reg_input_view);
		setupView();
		btn_next.setOnClickListener(this);
		btn_return.setOnClickListener(this);
		setupView();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, certificates);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				certificate = certificates[position];
				;

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		btn_next.setOnClickListener(this);
	}

	private void setupView() {
		btn_return = (Button) findViewById(R.id.returnButton);
		btn_next = (Button) findViewById(R.id.nextButton);
		spinner = (Spinner) findViewById(R.id.CertificateSpinner);
		et_Card = (EditText) findViewById(R.id.CardNum);
		et_account = (EditText) findViewById(R.id.account);
		et_secondaccount = (EditText) findViewById(R.id.second_account);
		et_name = (EditText) findViewById(R.id.Name);
		et_phone = (EditText) findViewById(R.id.Telephone);
		et_password = (EditText) findViewById(R.id.Password);
		et_secondpassword = (EditText) findViewById(R.id.PasswordConfirm);
		et_address = (EditText) findViewById(R.id.address);

	}

	public void onClick(View v) {
		if (v == btn_next) {
			String indetify = et_Card.getText().toString();
			String account = et_account.getText().toString();
			String secondaccount=et_secondaccount.getText().toString();
			String username = et_name.getText().toString();
			String phone = et_phone.getText().toString();
			String password = et_password.getText().toString();
			String secondPassword = et_secondpassword.getText().toString();
			String address = et_address.getText().toString();
			if (null == indetify || indetify.equals("")) {
				Toast.makeText(this, "证件号码不能为空!", Toast.LENGTH_LONG).show();
               return ;
			}
			if(indetify.length()!=18){
				Toast.makeText(this, "证件号码格式不对!", Toast.LENGTH_LONG).show();
				return;
			}
			if (null == account || account.equals("")) {
				Toast.makeText(this, "用户账号不能为空!", Toast.LENGTH_LONG).show();
              return;
			}
			if (!account.equals(secondaccount)) {
				Toast.makeText(this, "账号输入不一致，请重新输入", Toast.LENGTH_LONG).show();
			}
			if (null == username || username.equals("")) {
				Toast.makeText(this, "用户姓名不能为空!", Toast.LENGTH_LONG).show();
              return;
			}
			if (null == phone || phone.equals("")) {
				Toast.makeText(this, "手机号码不能为空!", Toast.LENGTH_LONG).show();
              return;
			}
			if (null == password || password.equals("")) {
				Toast.makeText(this, "密码不能为空!", Toast.LENGTH_LONG).show();
              return;
			}
			if(password.length()<6||password.length()>30){
				Toast.makeText(this, "密码格式错误!", Toast.LENGTH_LONG).show();
				return ;
			}
			if (!password.equals(secondPassword)) {
				Toast.makeText(this, "密码输入不一致，请重新输入", Toast.LENGTH_LONG).show();
				return;
			}
			if (null==address||address.equals("")) {
				Toast.makeText(this, "地址不能为空", Toast.LENGTH_LONG).show();
				return;
			}
			User user = new User();
			user.setCertificate(certificate);
			user.setIndetify(et_Card.getText().toString());
			user.setAccount(et_account.getText().toString());
			user.setUsername(et_name.getText().toString());
			user.setPhone(et_phone.getText().toString());
			user.setPassword(et_secondpassword.getText().toString());
			user.setAddress(et_address.getText().toString());
			user.setTime(getTime());
			Intent nextIntent = new Intent(this, RegisterConfirmActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("user", user);
			nextIntent.putExtras(bundle);
			startActivity(nextIntent);
		} else {
			setResult(RESULT);
			finish();
		}
	}

	// 获得当前时间，按XX-XX-XX输出
	private String getTime() {
		String time = null;
		Calendar calendar = Calendar.getInstance();
		time = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1)
				+ "-" + calendar.get(Calendar.DATE);
		return time;
	}

}
