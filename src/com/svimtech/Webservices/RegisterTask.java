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

import com.svimtech.adapter.SpinnerAdapter;
import com.svimtech.easytime.RegisterActivity;
import com.svimtech.modal.SpinnerRegisterCompany;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class RegisterTask extends AsyncTask<Void, Void, Void>{

	private String resp;
	RegisterActivity registerActivity;
	String r_uname;
	String r_password;
	String r_email,id;
	 ProgressDialog dialog;
	private String resp1;
	public RegisterTask(RegisterActivity registerActivity, String r_uname,
			String r_password, String r_email, String id) {
		// TODO Auto-generated constructor stub
		this.registerActivity=registerActivity;
		this.r_uname=r_uname;
		this.r_password=r_password;
		this.r_email=r_email;
		this.id=id;
	}
	
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog=new ProgressDialog(registerActivity);
		dialog.setMessage("Registering...");
		dialog.setCancelable(false);
		dialog.show();
		// TODO Auto-generated method stub
	
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		

		
		
		
		HttpPost post1=new HttpPost("http://easytimeayodia.herokuapp.com/insertusers");
		HttpClient client1=new DefaultHttpClient();
		ArrayList<NameValuePair> list1=new ArrayList<NameValuePair>();
		
		//username , companyid,password,securitytoken,email
		list1.add(new BasicNameValuePair("username",r_uname ));
		list1.add(new BasicNameValuePair("companyid",id ));
		list1.add(new BasicNameValuePair("password",r_password ));
		list1.add(new BasicNameValuePair("securitytoken","dvgafdbgf" ));
		list1.add(new BasicNameValuePair("email",r_email ));
		
		try {
			post1.setEntity(new UrlEncodedFormEntity(list1));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try 
		{
			HttpResponse response1= client1.execute(post1);
		
			resp1=EntityUtils.toString(response1.getEntity());
			System.out.println("secondary resp "+resp1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	
		
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub

		super.onPostExecute(result);
		
		
	
		if(resp1.equals("[problem]"))
		{
			Toast.makeText(registerActivity, "Already Exists!", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(registerActivity, "Registered!", Toast.LENGTH_SHORT).show();
			registerActivity.finish();
			dialog.dismiss();
		}
		
		
	}

}
