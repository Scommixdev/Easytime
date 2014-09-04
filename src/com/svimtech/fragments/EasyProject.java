package com.svimtech.fragments;



import java.util.ArrayList;

import com.svimtech.Webservices.GetTaskByProject;
import com.svimtech.Webservices.Getprojects;
import com.svimtech.adapter.EasyProjectAdapter;

import com.svimtech.easytime.R;
import com.svimtech.modal.ProjectTimeModal;
import com.svimtech.modal.TaskItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;

public class EasyProject extends Fragment {
	
	View rootView;
	Spinner projectlistview;
	EasyProjectAdapter adapter;
	ArrayList<ProjectTimeModal> data;
	Getprojects getprojects;
	GetTaskByProject gettask;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		rootView=inflater.inflate(R.layout.easyproject, container,false);
		projectlistview=(Spinner)rootView.findViewById(R.id.projectlist);
			data=new ArrayList<ProjectTimeModal>();
			ProjectTimeModal modl=new ProjectTimeModal();
			modl.setName("Select Projects");
			data.add(modl);
			getprojects=new Getprojects(getActivity(),data,adapter,projectlistview);
			
		
			projectlistview.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					if(position==0)
					{
						
					}
					else{
						gettask=null;
						gettask=new GetTaskByProject(getActivity());
						gettask.execute(data.get(position).getProjectid());
					}
					
				
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					System.out.println("nothing");
				}
			});

	
	
		
		getprojects.execute();
	
		return rootView;
	}

}
