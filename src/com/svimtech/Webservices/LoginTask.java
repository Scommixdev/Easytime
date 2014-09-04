package com.svimtech.Webservices;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.svimtech.database.EasyTimepref;
import com.svimtech.easytime.LoginActivity;
import com.svimtech.easytime.ProjectTasks;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class LoginTask extends AsyncTask<Void, Void, Void> {

	private String resp;
	LoginActivity loginActivity; String emailtext;
	 String passvalue;
	private String passwd;
	private String usr;
	private ProgressDialog dialog;
	private int value;
	private String companyid;
	
	public LoginTask(LoginActivity loginActivity, String emailtext, String passvalue) {
		// TODO Auto-generated constructor stub
		this.loginActivity=loginActivity;
		this.emailtext=emailtext;
		this.passvalue=passvalue;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		value=1;
		usr="sgbngfnafsdnkjlsdfbvldfblbvsdfjklbjk";
		dialog=new ProgressDialog(loginActivity);
		dialog.setMessage("Signing in...");
		dialog.setCancelable(false);
		dialog.show();
		// TODO Auto-generated method stub
	
	}
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		HttpPost post=new HttpPost("http://easytimeayodia.herokuapp.com/getusers");
		HttpClient client=new DefaultHttpClient();
		ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
		
		//username , companyid,password,securitytoken,email
		list.add(new BasicNameValuePair("username",emailtext ));
		
		try {
			post.setEntity(new UrlEncodedFormEntity(list));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try 
		{
			HttpResponse response= client.execute(post);
		
			resp=EntityUtils.toString(response.getEntity());
			System.out.println("initial "+resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resp.equals("[]"))
		{
			value=0;
		}
		else{
			JSONArray arr = null;
			try {
				arr = new JSONArray(resp);
				
				for(int i=0;i<arr.length();i++)
				{
					try {
						JSONObject obj=arr.getJSONObject(i);
						usr=obj.getString("username");
						passwd=obj.getString("password");
						companyid=obj.getString("companyid");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}
		
		
	
		
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	
		if(value==0)
		{
			
		}
		else{
			if(usr.equals(emailtext)&&usr!=null)
			{
				if(passwd.equals(passvalue))
				{
					EasyTimepref pref =EasyTimepref.getInstance(loginActivity.getApplicationContext());
					pref.setCompanyid(companyid);
					pref.setEMAILID(emailtext);
					pref.setUSERNAME(usr);
					pref.setisRegistered(true);
					Intent toactivity=new Intent(loginActivity,ProjectTasks.class);
					loginActivity.startActivity(toactivity);
					loginActivity.finish();
				}
			}
			else
			{
				Toast.makeText(loginActivity, "Wrong", Toast.LENGTH_SHORT).show();
			}
			
			dialog.dismiss();
		}
		
		
	}

}
