package com.svimtech.fragments;

import java.util.ArrayList;

import com.svimtech.adapter.EasyProjectTimeAdapter;
import com.svimtech.easytime.R;
import com.svimtech.modal.ProjectTimeModal;
import com.svimtech.modal.TaskItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class EasyProjectTimes extends Fragment {

	View rootView;
	ListView tasklistview;
	EasyProjectTimeAdapter adapter;
	ArrayList<ProjectTimeModal> data;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView=inflater.inflate(R.layout.easyprojecttask, container,false);
		tasklistview=(ListView)rootView.findViewById(R.id.tasklist);
		data=new ArrayList<ProjectTimeModal>();
		
		
		for(int i=0;i<10;i++)
		{
			ProjectTimeModal item=new ProjectTimeModal();
			
			item.setEasyprojecttimename("TW1000");
			item.setProjecttask("Task "+i);
			
			item.setTimestarted("8/25/2014");
			item.setTimeended("8/30/2014");
			data.add(item);
		}
		
		
		adapter=new EasyProjectTimeAdapter(getActivity(), data);
		tasklistview.setAdapter(adapter);
		
		
		return rootView;
	}
}
