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

import com.svimtech.easytime.RegisterActivity;

import android.os.AsyncTask;

public class RegisterTask extends AsyncTask<Void, Void, Void>{

	private String resp;
	RegisterActivity registerActivity;
	String r_uname;
	String r_password;
	String r_email,id;
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
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		HttpPost post=new HttpPost("http://easytimeayodia.herokuapp.com/insertusers");
		HttpClient client=new DefaultHttpClient();
		ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
		
		//username , companyid,password,securitytoken,email
		list.add(new BasicNameValuePair("username",r_uname ));
		list.add(new BasicNameValuePair("companyid",id ));
		list.add(new BasicNameValuePair("password",r_password ));
		list.add(new BasicNameValuePair("securitytoken","dvgafdbgf" ));
		list.add(new BasicNameValuePair("email",r_email ));
		
		try {
			post.setEntity(new UrlEncodedFormEntity(list));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpResponse response= client.execute(post);
		
			resp=EntityUtils.toString(response.getEntity());
			System.out.println(resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
