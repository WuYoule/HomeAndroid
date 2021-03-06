package com.leweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LeWeatherOpenHelper extends SQLiteOpenHelper {
    //Province表建表语句
	public static final String CREATE_PROVINCE="create table Province ("
            +"id integer primary key autoincrement,"
			+"province_name text,"
			+"province_code text,"+
			  "childCount integer)";
	//City建表语句
	public static final String CREATE_CITY="create table City("
	        +"id integer primary key autoincrement,"
			+"city_name text,"
			+"city_code text,"
			+ "province_id integer,"
			+ "childCount integer)";
	
	//County建表语句
	public static final String CREATE_COUNTY="create table County("
	
			+"id integer primary key autoincrement,"
			+"county_name text,"
			+"county_code text,"
			+"city_id integer)";
	
	
	public LeWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	         db.execSQL(CREATE_PROVINCE);
             db.execSQL(CREATE_CITY);
             db.execSQL(CREATE_COUNTY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("Youle tag", "onUpgrade");
//	   db.execSQL("alter table Province add column childCount integer");
//	   db.execSQL("alter table City add column childCount integer");
		onCreate(db);

	}

}
