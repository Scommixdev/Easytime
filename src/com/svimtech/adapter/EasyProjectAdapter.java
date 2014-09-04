package com.svimtech.adapter;

import java.util.ArrayList;

import com.svimtech.easytime.R;
import com.svimtech.fragments.EasyProject;
import com.svimtech.modal.ProjectTimeModal;
import com.svimtech.modal.TaskItem;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EasyProjectAdapter extends BaseAdapter{

	FragmentActivity fragmentActivity; ArrayList<ProjectTimeModal> data;
	
	public EasyProjectAdapter(FragmentActivity fragmentActivity, ArrayList<ProjectTimeModal> data2) {
		// TODO Auto-generated constructor stub
		this.fragmentActivity=fragmentActivity;
		this.data=data2;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	ViewHolder holder;
		
		if(convertView==null)
		{
			LayoutInflater inflater=(LayoutInflater)fragmentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.item_spinner_company, parent,false);
		
			holder.tv=(TextView)convertView.findViewById(R.id.cname);
			convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder)convertView.getTag();
		}
		
		holder.tv.setText(data.get(position).getName());
		
		
		
		return convertView;
	}
	class ViewHolder{
		TextView tv;
		
	}

}
