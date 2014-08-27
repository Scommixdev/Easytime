package com.svimtech.adapter;

import java.util.ArrayList;

import com.svimtech.easytime.R;
import com.svimtech.easytime.R.id;
import com.svimtech.easytime.R.layout;
import com.svimtech.services.CountTimeService;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EasyProjectTaskAdapter extends BaseAdapter
{
	FragmentActivity activity;
	
	ArrayList<String> data;
	
	public EasyProjectTaskAdapter(FragmentActivity activity, ArrayList<String> data) {
		// TODO Auto-generated constructor stub
		this.activity=activity;
		this.data=data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder;
		
		if(convertView==null)
		{
			LayoutInflater inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.itemspinner, parent,false);
			holder.iv=(ImageView)convertView.findViewById(R.id.iv);
			holder.tv=(TextView)convertView.findViewById(R.id.tv);
			holder.texttime=(TextView)convertView.findViewById(R.id.texttime);
			
			holder.stop=(Button)convertView.findViewById(R.id.stopit);
			holder.stop.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(isMyServiceRunning(CountTimeService.class)==true)
					{
						    Intent i=new Intent();
					        i.setAction("com.svimtech.services.ACTION_STOP");
					        i.putExtra("PROJECT", data.get(position));
					        i.putExtra("ID", position);
					        activity.startService(i);
					}
					else
					{
						Toast.makeText(activity, "Timer Already running", Toast.LENGTH_SHORT).show();
					}
				}
			});
			
			
			
			holder.start=(Button)convertView.findViewById(R.id.start);
			holder.start.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					if(isMyServiceRunning(CountTimeService.class)==false)
					{
						    Intent i=new Intent();
					        i.setAction("com.svimtech.services.ACTION_START");
					        i.putExtra("PROJECT", data.get(position));
					        i.putExtra("ID", position);
					        activity.startService(i);
					}
					else
					{
						Toast.makeText(activity, "Timer Already running", Toast.LENGTH_SHORT).show();
					}
				     
				}
			});
		
			
			convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder)convertView.getTag();
		}
		
		holder.tv.setText(data.get(position));
		holder.texttime.setText("");
		
		
		return convertView;
	}
	
	
	
	class ViewHolder{
		TextView tv,texttime;
		ImageView iv;
		Button start,stop;
	}
	private boolean isMyServiceRunning(Class<?> serviceClass) {
	    ActivityManager manager = (ActivityManager)activity.getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if (serviceClass.getName().equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
}
