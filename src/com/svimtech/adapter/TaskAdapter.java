package com.svimtech.adapter;

import java.util.ArrayList;

import com.svimtech.adapter.EasyProjectAdapter.ViewHolder;
import com.svimtech.easytime.ProjectTasks;
import com.svimtech.easytime.R;
import com.svimtech.modal.TaskItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TaskAdapter extends BaseAdapter {

	ProjectTasks projectTasks;
	ArrayList<TaskItem> data;
	public TaskAdapter(ProjectTasks projectTasks, ArrayList<TaskItem> data) {
		// TODO Auto-generated constructor stub
		this.projectTasks=projectTasks;
		this.data=data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
ViewHolder holder;
		
		if(convertView==null)
		{
			LayoutInflater inflater=(LayoutInflater)projectTasks.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.item_spinner_company, parent,false);
		
			holder.tv=(TextView)convertView.findViewById(R.id.cname);
			convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder)convertView.getTag();
		}
		
		
		holder.tv.setText((position+1)+": "+data.get(position).getName());
		
		
		
		
		
		return convertView;
	}
	class ViewHolder{
		TextView tv;
		
	}

}
