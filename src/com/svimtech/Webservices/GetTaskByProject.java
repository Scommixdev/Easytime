package com.svimtech.Webservices;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.svimtech.easytime.App;
import com.svimtech.easytime.ProjectTasks;
import com.svimtech.modal.TaskItem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

public class GetTaskByProject extends AsyncTask<String, Void, Void> {

	String resp;
	private ProgressDialog dialog;
	FragmentActivity activity;
	
	ArrayList<TaskItem> data;
	App obj;
	public GetTaskByProject(FragmentActivity activity) {
		// TODO Auto-generated constructor stub
		this.activity=activity;
		obj=(App)activity.getApplication();
		data=obj.getData();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		dialog=new ProgressDialog(activity);
		dialog.setMessage("Getting tasks...");
		dialog.setCancelable(false);
		dialog.show();
		// TODO Auto-generated method stub
	
	}
	
	@Override
	protected Void doInBackground(String... params) {
	HttpClient client=new DefaultHttpClient();
	HttpPost post=new HttpPost("http://easytimeayodia.herokuapp.com/gettaskbyprojectid");
	ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
	 list.add(new BasicNameValuePair("projectid", params[0]));
	 
	 try 
	 
	 {
		post.setEntity(new UrlEncodedFormEntity(list));
		HttpResponse response=client.execute(post);
		resp=EntityUtils.toString(response.getEntity());
		System.out.println(resp);
		
	} 
	 catch (UnsupportedEncodingException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	 
	 catch (ClientProtocolException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 


	 
	 JSONArray arr = null;
	 try {
		 arr=new JSONArray(resp);
		 for(int i=0;i<arr.length();i++)
		 {
			 JSONObject obj=arr.getJSONObject(i);
			 
			 String taskid=obj.getString("taskid");
			 String accountid=obj.getString("accountid");
			 String startdate=obj.getString("startdate");
			 String name=obj.getString("name");
			 String enddate=obj.getString("enddate");
			 String description=obj.getString("description");
			 String taskstatus=obj.getString("taskstatus");
			 String projectid=obj.getString("projectid");
			 String companyid=obj.getString("companyid");
			 
			 TaskItem modal=new TaskItem();
			 modal.setAccountid(accountid);
			 modal.setCompanyid(companyid);
			 modal.setDescription(description);
			 modal.setEnddate(enddate);
			 modal.setName(name);
			 modal.setProjectid(projectid);
			 modal.setStartdate(startdate);
			 modal.setTaskid(taskid);
			 modal.setTaskstatus(taskstatus);
			 data.add(modal);
			 
		 }
		 
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 

	 
	 
	 
	 
	 
	return null;
	 
	 
	 
	 
	 
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		dialog.dismiss();
		
		Intent i=new Intent(activity,ProjectTasks.class);
		activity.startActivity(i);
		
	}

}