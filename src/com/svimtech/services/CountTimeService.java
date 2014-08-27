package com.svimtech.services;

import com.svimtech.easytime.MainActivity;
import com.svimtech.easytime.R;














import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

public class CountTimeService extends Service{

	private long startTime = 0L;
	long timeInMillies = 0L;
	 long timeSwap = 0L;
	 long finalTime = 0L;

	//	private RemoteViews layout;
	 private Handler myHandler = new Handler();
	protected String time;
	private final IBinder myBinder = new MyLocalBinder();
	
	private NotificationManager mNotifiManager;
	protected boolean shouldrun=true;
	public String action;
	public String project;
	public int id=1;
	private int position;
	private PendingIntent pendingIntent;
	private Intent intent;
	private NotificationCompat.Builder builder;
	private String delete;
	public static final String ACTION_STOP="com.svimtech.services.ACTION_STOP";
	public static final String ACTION_START="com.svimtech.services.ACTION_START";
	
	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return myBinder;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
        mNotifiManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        super.onCreate();
	}
	
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		
		    
		    if (intent != null) {
		        action = intent.getAction();     
		        
		     
		            if (action.equals(ACTION_START)) 
		            {
		            	   project=intent.getStringExtra("PROJECT");
		   		        position=intent.getIntExtra("ID", 0);
		   		        delete=intent.getStringExtra("DELETE");
		            	 startTime = SystemClock.uptimeMillis();
		            	// createNotification("Starting...",project,id);
		            	 Notification("0:00",project,id);
		     		     myHandler.postDelayed(updateTimerMethod, 1000);
		            }
		           else if(action.equals(ACTION_STOP)) 
		           {
		        	
				        position=intent.getIntExtra("ID", 0);
				        delete=intent.getStringExtra("DELETE");
		                stopPlay(id,delete,position);
		            }
		        
		    }
		
		
		return Service.START_FLAG_REDELIVERY;
	}

	 private void stopPlay(int id, String project2, int position2) {
		// TODO Auto-generated method stub
		 if(project.equals(project2))
		 {
				mNotifiManager.cancel(id);
				shouldrun=false;
				stopForeground(true);
				stopSelf();
		 }
		 else{
			 Toast.makeText(this, "Not Applicable!", Toast.LENGTH_SHORT).show();
		 }
		
	}

	

	public Runnable updateTimerMethod = new Runnable() {

		  public void run() {
		   timeInMillies = SystemClock.uptimeMillis() - startTime;
		   finalTime = timeSwap + timeInMillies;
		   
		   int seconds = (int) (finalTime / 1000);
		   int minutes = seconds / 60;
		   seconds = seconds % 60;
		   
		 
		 //  int milliseconds = (int) (finalTime % 1000);
		   
		   time ="" + minutes + ":"
				     + String.format("%02d", seconds);
		   
		  progressUpdate(time,id,project);
		   System.out.println(time);
		
		   if(shouldrun==true)
		   {
			   myHandler.postDelayed(this, 1000);
		   }
		
		  }

		 };




		 

		    
		    public void progressUpdate(String percentageComplete,int id,String project) {
		    builder.setContentText(percentageComplete);
		     

		        mNotifiManager.notify(id, builder.build());
		    }
		 
		    public void Notification(String string, String project2, int id2) {
		      
		 
		    	pendingIntent = null;
			      intent = null;
		    	 intent = new Intent(this,MainActivity.class);  
		    	
		    	 
		    	    pendingIntent = PendingIntent.getService(getApplicationContext(),
		    	            0, intent,
		    	            PendingIntent.FLAG_UPDATE_CURRENT);
		    	
		 
		        //Create Notification using NotificationCompat.Builder 
		         builder = new NotificationCompat.Builder(this)
		                // Set Icon
		         .setOngoing(true)
		                .setSmallIcon(R.drawable.ic_launcher)
		                // Set Ticker Message
		                .setTicker("Starting Timer"+string)
		                // Set Title
		                .setContentTitle("Timer for "+project2)
		                // Set Text
		                .setContentText(string)
		                // Add an Action Button below Notification
		                //.addAction(R.drawable.ic_launcher, "Stop", pendingIntent)
		                // Set PendingIntent into Notification
		                .setContentIntent(pendingIntent)
		                // Dismiss Notification
		                .setAutoCancel(true);
		 
		        // Create Notification Manager
		       // NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		        // Build Notification with Notification Manager
		         mNotifiManager.notify(id2, builder.build());
		         startForeground(id2, builder.build());
		    }
		 
		 
		    public class MyLocalBinder extends Binder {
		        public CountTimeService getService() {
		        	
		        
		            return CountTimeService.this;
		        }
		
		    }

}
