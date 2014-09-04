package com.svimtech.easytime;

import java.util.ArrayList;

import com.svimtech.database.EasyTimepref;
import com.svimtech.modal.TaskItem;

import android.app.Application;

public class App extends Application{

	
	ArrayList<TaskItem> data;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		data=new ArrayList<TaskItem>();
		EasyTimepref pref=new EasyTimepref(getApplicationContext());
	}
	public ArrayList<TaskItem> getData() {
		return data;
	}
	public void setData(ArrayList<TaskItem> data) {
		this.data = data;
	}
}
