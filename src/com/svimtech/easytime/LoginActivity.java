package com.svimtech.easytime;


import com.svimtech.database.EasyDatabase;

import android.content.ContentValues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends ActionBarActivity{
	EditText email,password;
	Button btnLogin;
	TextView link_to_register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		btnLogin=(Button)findViewById(R.id.btnLogin);
		email=(EditText)findViewById(R.id.email);
		password=(EditText)findViewById(R.id.password);
		link_to_register=(TextView)findViewById(R.id.link_to_register);
		
		link_to_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent toactivity=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(toactivity);

			}
		});
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String emailtext=email.getText().toString();
				String passvalue=password.getText().toString();
				System.out.println(emailtext +"and "+passvalue);

				ContentValues cv=new ContentValues();
				cv.put(EasyDatabase.USERID, "1");
				cv.put(EasyDatabase.USERNAME, emailtext);
				cv.put(EasyDatabase.COMPANY, "DEMO");
				cv.put(EasyDatabase.EMAIL, emailtext);
				cv.put(EasyDatabase.PASSWORD, passvalue);
				getContentResolver().insert(EasyDatabase.CONTENT_URI_UserInfo, cv);

				Intent toactivity=new Intent(LoginActivity.this,MainActivity.class);
				startActivity(toactivity);
			}
		});
		
	}

}
