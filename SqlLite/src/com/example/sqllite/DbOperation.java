package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbOperation {
	
	private Context mContext;
	private SQLiteDatabase mSqLiteDatabase;
	private final String DATABASE_NAME="androidDb";
	private final String TABLE_NAME="users";
	private final String COLUMN_USERNAME="userName";
	private final String COLUMN_USERADDRESS="userAddress";
	
	public DbOperation(Context context){
		
		mContext=context;
		
	}
	public void openOrCreateDb() {
		
		mSqLiteDatabase=mContext.openOrCreateDatabase(DATABASE_NAME, mContext.MODE_PRIVATE, null);
		mSqLiteDatabase.execSQL("create TABLE "+TABLE_NAME+"(userId INTEGER PRIVMARY KEY  AUTOINCREMENT,"+COLUMN_USERNAME+" VARCHAR(50) NOT NULL,"+COLUMN_USERADDRESS+" VARCHAR(50) NOT NULL");
		
		ContentValues values=new ContentValues();
		values.put(COLUMN_USERNAME, "wqy");
		values.put(COLUMN_USERADDRESS, "上海闵行区");
		mSqLiteDatabase.insert(TABLE_NAME, "", values);
		mSqLiteDatabase.close();
		mSqLiteDatabase=null;
	}

}
