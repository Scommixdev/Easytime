package com.svimtech.fragments;



import java.util.ArrayList;

import com.svimtech.adapter.EasyProjectTimeAdapter;
import com.svimtech.adapter.EasyProjectAdapter;
import com.svimtech.easytime.R;
import com.svimtech.modal.TaskItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class EasyProjectTask extends Fragment {
	
	View rootView;
	ListView tasklistview;
	EasyProjectTimeAdapter adapter;
	ArrayList<TaskItem> data;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView=inflater.inflate(R.layout.easyprojecttask, container,false);
		tasklistview=(ListView)rootView.findViewById(R.id.tasklist);
		data=new ArrayList<TaskItem>();
		
		for(int i=0;i<10;i++)
		{
			TaskItem item=new TaskItem();
			item.setProject("Project "+i);
			item.setTaskName("Task "+i);
			if(i%2==0)
			{
				item.setTaskStatus("Pending");
			}
			else{
				item.setTaskStatus("Completed");
			}
			
			item.setStartDate("8/25/2014");
			item.setEndDate("8/30/2014");
			data.add(item);
		}
		
		
		//adapter=new EasyProjectTimeAdapter(getActivity(), data);
	//	tasklistview.setAdapter(adapter);
		return rootView;
	}

}
