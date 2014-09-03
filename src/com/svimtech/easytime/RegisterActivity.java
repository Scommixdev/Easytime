package com.svimtech.easytime;

import java.util.ArrayList;

import com.svimtech.Webservices.GetCompaniesRegister;
import com.svimtech.Webservices.RegisterTask;
import com.svimtech.adapter.SpinnerAdapter;
import com.svimtech.modal.SpinnerRegisterCompany;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends ActionBarActivity{
	GetCompaniesRegister regTask;
	Spinner spinner;
	SpinnerAdapter adp;
	ArrayList<SpinnerRegisterCompany> spinnerList;
	EditText uname, password,confirm_pass, email;
	Button register;
	String r_uname,r_password,r_email;
	RegisterTask task;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		uname=(EditText)findViewById(R.id.reg_fullname);
		password=(EditText)findViewById(R.id.reg_password);
		confirm_pass=(EditText)findViewById(R.id.reg_password_confirm);
		email=(EditText)findViewById(R.id.reg_email);
		register=(Button)findViewById(R.id.btnRegister);
		spinner=(Spinner)findViewById(R.id.spinnerCompany);
		spinnerList=new ArrayList<SpinnerRegisterCompany>();
		
		
		
		regTask=new GetCompaniesRegister(adp,spinner,spinnerList,RegisterActivity.this);
		regTask.execute();
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				r_uname=uname.getText().toString();
				r_password=password.getText().toString();
				r_email=email.getText().toString();
				String id=spinnerList.get(spinner.getSelectedItemPosition()).getId();
				if(r_uname.equals(""))
				{
					Toast t=new Toast(RegisterActivity.this);
					t.setDuration(Toast.LENGTH_SHORT);
					t.setGravity(Gravity.CENTER|Gravity.TOP, 0, 0);
					t.setText("Username cannot be blank!");
					t.show();
				}
				
				if(r_password.equals(""))
				{
					Toast t=new Toast(RegisterActivity.this);
					t.setDuration(Toast.LENGTH_SHORT);
					t.setGravity(Gravity.CENTER|Gravity.TOP, 0, 0);
					t.setText("password cannot be blank!");
					t.show();
				}
				
				if(r_email.equals(""))
				{
					Toast t=new Toast(RegisterActivity.this);
					t.setDuration(Toast.LENGTH_SHORT);
					t.setGravity(Gravity.CENTER|Gravity.TOP, 0, 0);
					t.setText("email cannot be blank!");
					t.show();
				}
				
			if(r_password.equals(confirm_pass.getText().toString()))
			{
				task=new RegisterTask(RegisterActivity.this,r_uname,r_password,r_email,id);
				task.execute();
			}
				
				
				
				
				
			}
		});
		
	}

}
