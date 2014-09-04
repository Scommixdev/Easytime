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

import android.os.AsyncTask;

public class GetTaskByProject extends AsyncTask<String, Void, Void> {

	String resp;
	
	@Override
	protected Void doInBackground(String... params) {
	HttpClient client=new DefaultHttpClient();
	HttpPost post=new HttpPost("http://easytimeayodia.herokuapp.com/gettaskbyprojectid");
	ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
	 list.add(new BasicNameValuePair("projectid", params[0]));
	 
	 try {
		post.setEntity(new UrlEncodedFormEntity(list));
		HttpResponse response=client.execute(post);
		resp=EntityUtils.toString(response.getEntity());
		System.out.println(resp);
		
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 
	 
	 
	 
	return null;
	 
	 
	 
	 
	 
	}

}