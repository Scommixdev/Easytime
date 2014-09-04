package com.svimtech.fragments;



import java.util.ArrayList;

import com.svimtech.Webservices.Getprojects;
import com.svimtech.adapter.EasyProjectAdapter;
import com.svimtech.adapter.EasyProjectTaskAdapter;
import com.svimtech.easytime.R;
import com.svimtech.modal.ProjectTimeModal;
import com.svimtech.modal.TaskItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class EasyProject extends Fragment {
	
	View rootView;
	ListView projectlistview;
	EasyProjectAdapter adapter;
	ArrayList<ProjectTimeModal> data;
	Getprojects getprojects;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView=inflater.inflate(R.layout.easyproject, container,false);
		projectlistview=(ListView)rootView.findViewById(R.id.projectlist);
		data=new ArrayList<ProjectTimeModal>();
	
		getprojects=new Getprojects(getActivity(),data,adapter,projectlistview);
		
		getprojects.execute();
	
		return rootView;
	}

}
