package com.svimetch.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class EasyDatabase extends ContentProvider{
	
	///userinfo
	public static final String KEY_ID="_id";
	public static final String USERID="USERID";
	public static final String USERNAME="USERNAME";
	public static final String COMPANY="COMPANY";
	public static final String EMAIL="EMAIL";
	public static final String PASSWORD="PASSWORD";
	
	//project task table
	
	public static final String ACCOUNT="ACCOUNT";
	public static final String PROJECT="USERID";
	public static final String TASK="TASK";
	public static final String TASKID="TASKID";
	public static final String WORKHOURS="WORKHOURS";
	public static final String STARTTIME="STARTTIME";
	public static final String ENDTIME="ENDTIME";
	public static final String DESCRIPTION="DESCRIPTION";
	
	
	
	private static final String AUTHORITY = "com.svimetch.database.EasyDatabase";
	
	public static final String DATABASE_NAME="easydatabase.db";
	
	public static final String DATABASE_TABLEuserinfo="userinfo";
	public static final String DATABASE_TABLEuserproject="userproject";
	

	

	
	private static final int DATABASE_VERSION=1;
	public static final Uri CONTENT_URI_UserInfo = Uri.parse("content://"+ AUTHORITY + "/" + DATABASE_TABLEuserinfo);
	public static final Uri CONTENT_URI_Userproject=Uri.parse("content://"+AUTHORITY+"/"+DATABASE_TABLEuserproject);

EasyDatabase Help;
	SQLiteDatabase database;
	
	private class OpenHelper extends SQLiteOpenHelper{



		private static final String SQLuserinfo= "create table "+DATABASE_TABLEuserinfo+" ("+KEY_ID+
				" integer primary key autoincrement, "+USERID+" text ,"+USERNAME+" text ,"+COMPANY+" text ,"+EMAIL+
				" text ,"+PASSWORD+" text  ); " ;
		

		private static final String SQLuserproject= "create table "+DATABASE_TABLEuserproject+" ("+KEY_ID+
				" integer primary key autoincrement, "+ACCOUNT+" text ,"+PROJECT+" text ,"+TASK+" text ,"+TASKID+
				" text ,"+WORKHOURS+" text,"+STARTTIME+" text,"+ENDTIME+" text,"+DESCRIPTION+" text  ); " ;
		
		
		
		
		public OpenHelper(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			System.out.println("db created");
		db.execSQL(SQLuserinfo);
		db.execSQL(SQLuserproject);
		
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLEuserinfo);
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLEuserproject);
			onCreate(db);
		}
		
	}

	
	OpenHelper Helper;
	
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		 int count = 0;
		database=Helper.getWritableDatabase();
		
		if(uri==CONTENT_URI_UserInfo)
		{
		    count=this.database.delete(DATABASE_TABLEuserinfo, selection, null);
		}
		else if(uri==CONTENT_URI_Userproject)
		{
			 count=this.database.delete(DATABASE_TABLEuserinfo, selection, null);
		}
	

		
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		// TODO Auto-generated method stub
		Log.i("id", ""+uri.toString());
		ContentValues values;
		if (initialValues == null) {
			values = new ContentValues();
		} else {
			values = new ContentValues(initialValues);
		}
		
		long id;
		database = Helper.getWritableDatabase();

		if(uri==CONTENT_URI_UserInfo)
		{
			id=database.insert(DATABASE_TABLEuserinfo, null, values);
			Log.i("id", ""+id);
			uri = ContentUris.withAppendedId(EasyDatabase.CONTENT_URI_UserInfo,
					id);
		}
		else if(uri==CONTENT_URI_Userproject){
			id=database.insert(DATABASE_TABLEuserproject, null, values);
			Log.i("id", ""+id);
			uri = ContentUris.withAppendedId(EasyDatabase.CONTENT_URI_Userproject,
					id);
		}
			
			
			getContext().getContentResolver().notifyChange(uri, null);
		
	
				return uri;
			}
	
		
			
		
	
	

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		Helper = new OpenHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db=Helper.getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

			if(uri==CONTENT_URI_UserInfo)
			{
				qb.setTables(DATABASE_TABLEuserinfo);
				Log.i("id"," tables set in query");
			}
			else if(uri==CONTENT_URI_Userproject)
			{
				qb.setTables(DATABASE_TABLEuserproject);
				Log.i("id"," tables set in query");
			}
		
		
		Cursor c = qb.query(db, projection, selection, selectionArgs,
				sortOrder, null, null);
		
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
		
		
		
		
		}
		
		
		
		
	

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		 int count = 0;
		 database=Helper.getWritableDatabase();
		 if(uri==CONTENT_URI_UserInfo)
		 {
			 count = database.update(DATABASE_TABLEuserinfo, values, selection, selectionArgs);
		 }
		 else if(uri==CONTENT_URI_Userproject)
		 {
			 count = database.update(DATABASE_TABLEuserproject, values, selection, selectionArgs);
		 }
        
         getContext().getContentResolver().notifyChange(uri, null);
	     return count;
	}

}
