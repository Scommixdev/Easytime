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

import com.svimtech.adapter.EasyProjectAdapter;
import com.svimtech.database.EasyTimepref;
import com.svimtech.fragments.EasyProject;
import com.svimtech.modal.ProjectTimeModal;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;
import android.widget.Spinner;

public class Getprojects extends AsyncTask<Void, Void, Void> {

	String resp;

	FragmentActivity fragmentActivity;
	 EasyProjectAdapter adapter; 
	 Spinner projectlistview;
	ArrayList<ProjectTimeModal> data; 
	 ProgressDialog dialog;
	public Getprojects(FragmentActivity fragmentActivity,
			ArrayList<ProjectTimeModal> data, EasyProjectAdapter adapter, Spinner projectlistview) {
		// TODO Auto-generated constructor stub
		this.fragmentActivity=fragmentActivity;
		this.data=data;
		this.adapter=adapter;
		this.projectlistview=projectlistview;
	
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog=new ProgressDialog(fragmentActivity);
		dialog.setMessage("Getting Projects...");
		dialog.setCancelable(false);
		dialog.show();
		// TODO Auto-generated method stub
	
	}

	@Override
	protected Void doInBackground(Void... params) {

		HttpPost post = new HttpPost("http://easytimeayodia.herokuapp.com/geteasyprojects");
		HttpClient client = new DefaultHttpClient();
		ArrayList<NameValuePair> namevaluepair = new ArrayList<NameValuePair>();
		namevaluepair.add(new BasicNameValuePair("companyid", EasyTimepref.getCompanyid()));

		try {
			
			
			post.setEntity(new UrlEncodedFormEntity(namevaluepair));
			HttpResponse response=client.execute(post);
			resp=EntityUtils.toString(response.getEntity());
			System.out.println(resp);

			
		} catch (UnsupportedEncodingException e) 
		
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray arr = null;
		try {
			arr = new JSONArray(resp);
			
			for(int i=0;i<arr.length();i++)
			{
				try {
					JSONObject obj=arr.getJSONObject(i);
					String id=obj.getString("id");
					String name=obj.getString("name");
					String startdate=obj.getString("startdate");
					String companyid=obj.getString("companyid");
					String enddate=obj.getString("enddate");
					String projectstatus=obj.getString("projectstatus");
					String projectid=obj.getString("projectid");
					
					ProjectTimeModal modal=new ProjectTimeModal();
					modal.setCompanyid(companyid);
					modal.setEnddate(enddate);
					modal.setId(id);
					modal.setName(name);
					modal.setProjectid(projectid);
					modal.setProjectstatus(projectstatus);
					modal.setStartdate(startdate);
					
					data.add(modal);
					
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		

		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		dialog.dismiss();
		adapter=new EasyProjectAdapter(fragmentActivity, data);
		projectlistview.setAdapter(adapter);
		
	}

}