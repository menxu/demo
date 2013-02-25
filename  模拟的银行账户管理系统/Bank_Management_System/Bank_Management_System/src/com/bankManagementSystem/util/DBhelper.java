package com.bankManagementSystem.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.bankManagementSystem.model.User;

public class DBhelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "login.db";
	// 用户表
	public static final String TBL_USER = "user";
	// 用户表的字段
	public static final String USER_ID = "_id";
	public static final String CERTIFICAT = "certificate"; // 证件类型
	public static final String USER_INDENTIFY = "indentify";// 用户证件号码
	public static final String USER_ACCOUNT = "account"; // 用户账号
	public static final String USER_NAME = "username"; //
	public static final String USER_PHONE = "phone";
	public static final String USER_ALL = "total"; // 存款金额
	public static final String USER_PASSWORD = "password";
	public static final String USER_ADDRESS = "address";
	public static final String USER_TIME = "time"; // 开户时间
	// 管理员表
	public static final String TBL_ADMIN = "admin";
	// 管理员表的字段
	public static final String ADMIN_ID = "_id";
	public static final String ADMIN_ACCOUNT = "adminname";
	public static final String ADMIN_PASSWORD = "password";
	// 创建用户表sql语句
	public static final String USER_SQL = "create table " + TBL_USER + "("
			+ USER_ID + " integer primary key autoincrement," + CERTIFICAT
			+ " text," + USER_INDENTIFY + " text," + USER_ACCOUNT + " text,"
			+ USER_NAME + " text," + USER_PHONE + " text," + USER_ALL
			+ " integer," + USER_PASSWORD + " text," + USER_ADDRESS + " text,"
			+ USER_TIME + " text);";
	// 创建管理员表sql语句
	public static final String ADMIN_SQL = "create table " + TBL_ADMIN + "("
			+ ADMIN_ID + " integer primary key autoincrement," + ADMIN_ACCOUNT
			+ " text," + ADMIN_PASSWORD + " text);";
	private SQLiteDatabase db;
	private static DBhelper instance = null;

	public static DBhelper getInstance(Context context) {
		if (instance == null) {
			instance = new DBhelper(context);
		}
		return instance;
	}

	private DBhelper(Context context) {
		super(context, DB_NAME, null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println(ADMIN_SQL);
		db.execSQL(USER_SQL);
		db.execSQL(ADMIN_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "drop table if exists " + TBL_USER;
		db.execSQL(sql);
		onCreate(db);
	}

	private void openDatabase() {

		// 没有数据库的话执行一次onCreate方法。。否则不执行onCreate方法..
		db = getWritableDatabase();

	}

	private void closeDb() {
		db.close();
	}

	// 核对用户是否存在，返回布尔值。
	public boolean checkUser(String account, String pwd) {
		boolean isUser = false;
		openDatabase();
		String sql = "select *from user where account= '" + account
				+ "' and password='" + pwd + "'";
		System.out.println("sql查询语句格式" + sql);
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			isUser = true;
		}
		cursor.close();
		closeDb();
		return isUser;
	}

	// 核对管理员是否存在。
	public boolean checkAdmin(String name, String pwd) {
		boolean isAdmin = false;
		openDatabase();
		String sql = "select *from admin where adminname= '" + name
				+ "' and password='" + pwd + "'";
		System.out.println("sql查询语句格式" + sql);
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			isAdmin = true;
		}
		cursor.close();
		closeDb();
		return isAdmin;
	}

	// 根据登录账号和登录密码查出用户的所有信息
	public User getUser(String account, String pwd) {
		User user = new User();
		openDatabase();
		String sql = "select *from user where account= '" + account
				+ "' and password='" + pwd + "'";
		System.out.println("sql查询语句格式" + sql);
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			user.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
			user.setAccount(cursor.getString(cursor.getColumnIndex("account")));
			user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
			user.setIndetify(cursor.getString(cursor
					.getColumnIndex("indentify")));
			user.setTotal(cursor.getInt(cursor.getColumnIndex("total")));
			user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
		}
		cursor.close();
		closeDb();
		return user;
	}

	// 根据登录账号查出用户的所有信息
	public User getUser(String account) {
		User user = new User();
		openDatabase();
		String sql = "select *from user where account= '" + account + "'";
		System.out.println("sql查询语句格式" + sql);
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			cursor.moveToNext();
			user.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
			user.setAccount(cursor.getString(cursor.getColumnIndex("account")));
			user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
			user.setIndetify(cursor.getString(cursor
					.getColumnIndex("indentify")));
			user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			user.setTotal(cursor.getInt(cursor.getColumnIndex("total")));
			user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
			user.setTime(cursor.getString(cursor.getColumnIndex("time")));
		}
		cursor.close();
		closeDb();
		return user;
	}

	// 遍历所有的用户信息
	public ArrayList<Map<String, Object>> getUserList() {
		openDatabase();
		Cursor cursor = db.query(TBL_USER, null, null, null, null, null, null);
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		while (cursor.moveToNext()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
			map.put("imageId", cursor.getInt(cursor.getColumnIndex("imageid")));
			map.put("name", cursor.getString(cursor.getColumnIndex("name")));

			list.add(map);
		}
		cursor.close();
		closeDb();
		return list;

	}

	public ArrayList<Map<String, Object>> getUserList(String condition) {
		openDatabase();
		String sql = "select * from user where name like '%" + condition + "%'"
				+ " or mobilephone like '%" + condition + "% '"
				+ " or officephone like '%" + condition + "% '"
				+ " or familyphone like '%" + condition + "%'";
		System.out.println("sql查询语句格式" + sql);
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		while (cursor.moveToNext()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
			map.put("imageId", cursor.getInt(cursor.getColumnIndex("imageid")));
			map.put("name", cursor.getString(cursor.getColumnIndex("name")));

			list.add(map);
		}
		cursor.close();
		closeDb();
		return list;

	}

	// 向数据库插入数据
	public boolean save(User user) {
		openDatabase();
		ContentValues values = new ContentValues();
		values.put(CERTIFICAT, user.getCertificate());
		values.put(USER_INDENTIFY, user.getIndetify());
		values.put(USER_ACCOUNT, user.getAccount());
		values.put(USER_NAME, user.getUsername());
		values.put(USER_PHONE, user.getPhone());
		// values.put(USER_ALL, user.getTotal());
		values.put(USER_PASSWORD, user.getPassword());
		values.put(USER_ADDRESS, user.getAddress());
		values.put(USER_TIME, user.getTime());
		return db.insert(TBL_USER, null, values) > 0;

	}

	// 修改用户信息
	public boolean modify(User user) {
		openDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_ACCOUNT, user.getAccount());

		return db.update(TBL_USER, values, USER_ID + "=" + user.get_id(), null) > 0;

	}

	// 修改用户密码
	public boolean modify(String password, int id) {
		openDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_PASSWORD, password);

		return db.update(TBL_USER, values, USER_ID + "=" + id, null) > 0;

	}

	public boolean delete(User user) {
		openDatabase();
		return db.delete(TBL_USER, "_id=?",
				new String[] { String.valueOf(user.get_id()) }) > 0;

	}

	public boolean deteleAll() {
		openDatabase();
		return db.delete(TBL_USER, null, null) > 0;

	}

	public boolean delete(int id) {
		openDatabase();
		return db
				.delete(TBL_USER, "_id=?", new String[] { String.valueOf(id) }) > 0;

	}

	public void backupData() {
		StringBuffer buffer = new StringBuffer();
		openDatabase();
		Cursor cursor = db.query("user", null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			buffer.append("insert into user values('")
					.append(cursor.getInt(cursor.getColumnIndex("_id")))
					.append("','")
					.append(cursor.getString(cursor.getColumnIndex("name")))
					.append("','")
					.append(cursor.getString(cursor
							.getColumnIndex("mobilephone")))
					.append("','")
					.append(cursor.getString(cursor
							.getColumnIndex("officephone")))
					.append("','")
					.append(cursor.getString(cursor
							.getColumnIndex("familyphone")))
					.append("','")
					.append(cursor.getString(cursor.getColumnIndex("position")))
					.append("','")
					.append(cursor.getString(cursor.getColumnIndex("company")))
					.append("','")
					.append(cursor.getString(cursor.getColumnIndex("address")))
					.append("','")
					.append(cursor.getString(cursor.getColumnIndex("zipcode")))
					.append("','")
					.append(cursor.getString(cursor.getColumnIndex("email")))
					.append("','")
					.append(cursor.getString(cursor
							.getColumnIndex("othercontact"))).append("','")
					.append(cursor.getString(cursor.getColumnIndex("remark")))
					.append("','")
					.append(cursor.getString(cursor.getColumnIndex("imageid")))
					.append("'").append(");").append("\n");

			saveDataToFile(buffer.toString());
		}

	}

	private void saveDataToFile(String strData) {
		String fileName = "user.bk";
		String dirName = "contact";
		FileOutputStream os = null;
		try {
			String SDpath = Environment.getExternalStorageDirectory()
					+ File.separator;

			File dirFile = new File(SDpath + dirName + File.separator);
			if (dirFile.exists()) {
				dirFile.mkdirs();
			}
			File file = new File(SDpath + dirName + File.separator + fileName);
			file.createNewFile();
			os = new FileOutputStream(file);
			os.write(strData.getBytes());
			os.flush();
		} catch (Exception e) {

		} finally {
			try {
				os.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	public void restoreData(String fileName) {
		try {
			String SDPATH = Environment.getExternalStorageDirectory() + "/";
			File file = null;
			if (fileName.endsWith(".bk")) {
				file = new File(SDPATH + "contact/" + fileName);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String str = "";
				while ((str = br.readLine()) != null) {
					System.out.println(str);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean findFile(String fileName) {
		boolean flag = false;
		File file = null;
		String SDpath = Environment.getExternalStorageDirectory()
				+ File.separator;
		if (fileName.endsWith(".bk")) {
			file = new File(SDpath + "contact" + File.separator + fileName);
			System.out.println(SDpath + "contact" + File.separator + fileName);
			if (file.exists()) {
				flag = true;
			}
		}

		return flag;
	}

}
