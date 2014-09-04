package com.svimtech.easytime;

import com.svimtech.database.EasyTimepref;

import android.app.Application;

public class App extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		EasyTimepref pref=new EasyTimepref(getApplicationContext());
	}
}
