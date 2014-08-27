package com.svimtech.fragments;

import java.util.ArrayList;

import com.svimtech.adapter.EasyProjectTaskAdapter;
import com.svimtech.easytime.MainActivity;
import com.svimtech.easytime.R;
import com.svimtech.services.CountTimeService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class EasyprojectTasks extends Fragment {
	
	View rootView;
	
	EasyProjectTaskAdapter adapter;
	ArrayList<String> data;
	ListView spinner;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView=inflater.inflate(R.layout.easyprojects, container,false);
		spinner=(ListView)rootView.findViewById(R.id.projectitems);
		data=new ArrayList<String>();
		
		data.add("Task 1");
		data.add("Task 2");
	
		adapter=new EasyProjectTaskAdapter(getActivity(), data);
		spinner.setAdapter(adapter);
	
		spinner.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			        Intent i=new Intent();
			        i.setAction("com.svimtech.services.ACTION_START");
			        getActivity().startService(i);
			}
			
		});
		
		
		
		return rootView;
	}
	
	
	
	

}
