package com.svimtech.Webservices;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.svimtech.adapter.SpinnerAdapter;
import com.svimtech.easytime.RegisterActivity;
import com.svimtech.modal.SpinnerRegisterCompany;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Spinner;

public class GetCompaniesRegister extends AsyncTask<Void, Void, Void>{
	SpinnerAdapter adp;
	ProgressDialog dialog;
	Spinner spinner;
	RegisterActivity registerActivity;
	ArrayList<SpinnerRegisterCompany> spinnerList;
	public GetCompaniesRegister(SpinnerAdapter adp, Spinner spinner,
			ArrayList<SpinnerRegisterCompany> spinnerList, RegisterActivity registerActivity) {
		this.adp=adp;
		this.spinner=spinner;
		this.spinnerList=spinnerList;
		this.registerActivity=registerActivity;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onPreExecute() {
		dialog=new ProgressDialog(registerActivity);
		dialog.setMessage("Getting Companies...");
		dialog.setCancelable(false);
		dialog.show();
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		HttpGet get=new HttpGet("http://easytimeayodia.herokuapp.com/getcompany");
		HttpClient client=new DefaultHttpClient();
		ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
		//username , companyid,password,securitytoken,email
		list.add(new BasicNameValuePair("username","" ));
		list.add(new BasicNameValuePair("companyid","" ));
		list.add(new BasicNameValuePair("password","" ));
		list.add(new BasicNameValuePair("securitytoken","" ));
		list.add(new BasicNameValuePair("email","" ));
		HttpResponse response = null;
		try {
			response=client.execute(get);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String resp = null;
	try {
		resp=EntityUtils.toString(response.getEntity());
		System.out.println(resp);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		JSONArray array=new JSONArray(resp);
		for(int i=0;i<array.length();i++){
			JSONObject obj=array.getJSONObject(i);
			String id=obj.getString("id");
			String name=obj.getString("name");
			String username=obj.getString("username");
			String email=obj.getString("email");
			String password=obj.getString("password");
			String securitytoken=obj.getString("securitytoken");
			String billingaddress=obj.getString("billingaddress");
			String phonenumber=obj.getString("phonenumber");
			SpinnerRegisterCompany oj=new SpinnerRegisterCompany();
			oj.setId(id);
			oj.setName(name);
			spinnerList.add(oj);
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
		adp=new SpinnerAdapter(registerActivity,spinnerList);
		spinner.setAdapter(adp);
		
		
		
	}

}
