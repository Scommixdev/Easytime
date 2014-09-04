package com.svimtech.database;

import android.content.Context;
import android.content.SharedPreferences;

public class EasyTimepref {
	
	static SharedPreferences pref;
	static EasyTimepref scomixpref;
	public static String USERNAME = "one";
	public static String EMAILID = "emailid";
	public static String ISREGISTERED="isRegistered";
    public static String DEVICETOKEN="devicetoken";
	public static String FirstRun ="FirstRun";
	public static String COMPANYID="companyid";



	


	public static EasyTimepref getInstance(Context context) {
		if (scomixpref == null) {
			scomixpref = new EasyTimepref(context);
		}
		return scomixpref;
	}

	public EasyTimepref(Context context) {
		pref = context.getSharedPreferences("sharelocpref",
				Context.MODE_PRIVATE);
	}
	
	
	
	
	
	

	


	
	public static  void setdevicetoken(String devicetoken) {
		pref.edit().putString(DEVICETOKEN, devicetoken).commit();
	}

	public static String getdevicetoken() {
		return pref.getString(DEVICETOKEN, "");

	}
	
	public static String getCompanyid() {
		return pref.getString(COMPANYID, "");
	}

	public static void setCompanyid(String companyid) {
		pref.edit().putString(COMPANYID, companyid).commit();
	}
	
	public static String getEMAILID() {
    	return pref.getString(EMAILID,"");
	}

	public static void setEMAILID(String eMAILID) {
		pref.edit().putString(EMAILID, eMAILID).commit();
	}

	

    
    
	
	

	

	public static String getUSERNAME() {
		return pref.getString(USERNAME,"");
	}

	public static void setUSERNAME(String uSERNAME) {
		pref.edit().putString(USERNAME, uSERNAME).commit();
	}


	public static void setisRegistered( boolean devicetoken) {
		pref.edit().putBoolean(ISREGISTERED, devicetoken).commit();
	}

	public static  boolean getisRegistered() {
		return pref.getBoolean(ISREGISTERED, false);
	}





	public static void setFirstRun( boolean devicetoken) {
		pref.edit().putBoolean(FirstRun, devicetoken).commit();
	}

	public static  boolean getFirstRun() {
		return pref.getBoolean(FirstRun, true);
	}
	

}