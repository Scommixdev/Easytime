package com.svimtech.adapter;

import java.util.ArrayList;

import com.svimtech.easytime.R;
import com.svimtech.easytime.RegisterActivity;
import com.svimtech.modal.SpinnerRegisterCompany;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends BaseAdapter{

	RegisterActivity registerActivity;
	ArrayList<SpinnerRegisterCompany> spinnerList;
	public SpinnerAdapter(RegisterActivity registerActivity,
			ArrayList<SpinnerRegisterCompany> spinnerList) {
		// TODO Auto-generated constructor stub
		this.registerActivity=registerActivity;
		this.spinnerList=spinnerList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return spinnerList.size();
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
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView==null){
			LayoutInflater inflater=(LayoutInflater)registerActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.item_spinner_company, parent, false);
			holder=new ViewHolder();
			holder.companyname=(TextView)convertView.findViewById(R.id.cname);
		convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		holder.companyname.setText(spinnerList.get(position).getName());
		return convertView;
	}
	class ViewHolder{
		TextView companyname;
	}

}
