package com.svimtech.services;

import com.svimtech.easytime.MainActivity;
import com.svimtech.easytime.R;














import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

public class CountTimeService extends Service{

	private long startTime = 0L;
	long timeInMillies = 0L;
	 long timeSwap = 0L;
	 long finalTime = 0L;

	//	private RemoteViews layout;
	 private Handler myHandler = new Handler();
	protected String time;
	
	
	private NotificationManager mNotifiManager;
	protected boolean shouldrun=true;
	public String action;
	public String project;
	public int id=1;
	private int position;
	private PendingIntent pendingIntent;
	private Intent intent;
	private NotificationCompat.Builder builder;
	public static final String ACTION_STOP="com.svimtech.services.ACTION_STOP";
	public static final String ACTION_START="com.svimtech.services.ACTION_START";
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
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
		        
		        project=intent.getStringExtra("PROJECT");
		        position=intent.getIntExtra("ID", 0);
		        
		            if (action.equals(ACTION_START)) 
		            {
		            	 startTime = SystemClock.uptimeMillis();
		            	// createNotification("Starting...",project,id);
		            	 Notification("0:00",project,id);
		     		     myHandler.postDelayed(updateTimerMethod, 1000);
		            }
		           else if(action.equals(ACTION_STOP)) 
		           {
		                stopPlay(id);
		            }
		        
		    }
		
		
		return Service.START_FLAG_REDELIVERY;
	}

	 private void stopPlay(int id) {
		// TODO Auto-generated method stub
			mNotifiManager.cancel(id);
			shouldrun=false;
			stopForeground(true);
			stopSelf();
	}

	

	public Runnable updateTimerMethod = new Runnable() {

		  public void run() {
		   timeInMillies = SystemClock.uptimeMillis() - startTime;
		   finalTime = timeSwap + timeInMillies;
		   
		   int seconds = (int) (finalTime / 1000);
		   int minutes = seconds / 60;
		   seconds = seconds % 60;
		   int hours=minutes*60;
		   System.out.println(hours);
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




		 
//		 /**
//		     * Put the notification into the status bar
//		     * @param time2 
//		 * @param project2 
//		     */
//		    public void createNotification(String time2, String project2,int id) {
//		        //get the notification manager
//		  
//
////		    	if (layout == null) {
////		            layout = new RemoteViews(getPackageName(),
////		                    R.layout.notification_control_bar);
////		        } 
//		      	pendingIntent = null;
//			      intent = null;
//		    	 intent = new Intent(ACTION_STOP);  
//		    	 intent.putExtra("PROJECT",project);
//		    	 
//		    	    pendingIntent = PendingIntent.getService(getApplicationContext(),
//		    	            0, intent,
//		    	            PendingIntent.FLAG_UPDATE_CURRENT);
//		    	
//		          //  layout.setTextViewText(R.id.projectnamenoti, project);
//		            
//		           // layout.setOnClickPendingIntent(R.id.StopTimer,
//		                 //   pendingIntent);
//		            
//		            Intent intent = new Intent(this,MainActivity.class);
//		            PendingIntent pi= PendingIntent.getActivity(this, 0, intent, 0);
//
//		        //Create the notification instance.
//		        mNotification = new NotificationCompat.Builder(this)
//		                .setSmallIcon(R.drawable.ic_launcher)
//		                .setWhen(System.currentTimeMillis())   
//		                .setContentTitle(project2)
//		                .setTicker("Starting Timer for :"+project2)
//		                .setSubText(time2)
//		                .setContentIntent(pi)
//		                .addAction(R.drawable.ic_launcher, "Stop", pendingIntent)
//		               
//		                //.setContent(layout)
//		                .build();
//		        //Show the notification in the notification bar.
//		        mNotifiManager.notify(id, mNotification);  
//		        startForeground(id, mNotification);
//		    }
		    
		    public void progressUpdate(String percentageComplete,int id,String project) {
		    builder.setContentText(percentageComplete);
		      //  mNotification.setLatestEventInfo(CountTimeService.this, project, percentageComplete,null);
	          //  layout.setTextViewText(R.id.projectnamenoti, project);
	           // layout.setTextViewText(R.id.timingnoti, percentageComplete);

		        mNotifiManager.notify(id, builder.build());
		    }
		 
		    public void Notification(String string, String project2, int id2) {
		      
		 
		    	pendingIntent = null;
			      intent = null;
		    	 intent = new Intent(ACTION_STOP);  
		    	 intent.putExtra("PROJECT",project);
		    	 
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
		                .setContentTitle("Started Timer for "+project2)
		                // Set Text
		                .setContentText(string)
		                // Add an Action Button below Notification
		                .addAction(R.drawable.ic_launcher, "Stop", pendingIntent)
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
		 
		 
		  
		


}
