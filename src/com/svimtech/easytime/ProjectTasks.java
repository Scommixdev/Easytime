package com.svimtech.easytime;

import java.util.ArrayList;
import java.util.Locale;

import com.svimtech.adapter.TaskAdapter;
import com.svimtech.fragments.EasyProject;

import com.svimtech.modal.TaskItem;
import com.svimtech.services.CountTimeService;

import android.app.ActivityManager;
import android.app.SearchManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProjectTasks extends ActionBarActivity  {

	 private DrawerLayout mDrawerLayout;
	    private ListView mDrawerList;
	    private ActionBarDrawerToggle mDrawerToggle;

	    private CharSequence mDrawerTitle;
	    private CharSequence mTitle;
	  
	    		App obj;
	    		ArrayList<TaskItem> data;
	    TaskAdapter adapter;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        obj=(App)getApplication();
	        data=obj.getData();
	        adapter=new TaskAdapter(ProjectTasks.this,data);
	        
	        mTitle = mDrawerTitle = getTitle();
	        
	        
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        mDrawerList = (ListView) findViewById(R.id.left_drawer);

	        // set a custom shadow that overlays the main content when the drawer opens
	        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
	        // set up the drawer's list view with items and click listener
	        mDrawerList.setAdapter(adapter);
	        
	        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

	        // enable ActionBar app icon to behave as action to toggle nav drawer
	        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	        getSupportActionBar().setHomeButtonEnabled(true);

	        // ActionBarDrawerToggle ties together the the proper interactions
	        // between the sliding drawer and the action bar app icon
	        mDrawerToggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                mDrawerLayout,         /* DrawerLayout object */
	                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
	                R.string.drawer_open,  /* "open drawer" description for accessibility */
	                R.string.drawer_close  /* "close drawer" description for accessibility */
	                ) {
	            public void onDrawerClosed(View view) {
	            	getSupportActionBar().setTitle(mTitle);
	               // invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }

	            public void onDrawerOpened(View drawerView) {
	            	getSupportActionBar().setTitle(mDrawerTitle);
	              //  invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }
	        };
	        mDrawerLayout.setDrawerListener(mDrawerToggle);

	        if (savedInstanceState == null) {
	            selectItem(0);
	        }
	    }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.main, menu);
	        return super.onCreateOptionsMenu(menu);
	    }

	    /* Called whenever we call invalidateOptionsMenu() */
	    @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        // If the nav drawer is open, hide action items related to the content view
	        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
	        return super.onPrepareOptionsMenu(menu);
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	         // The action bar home/up action should open or close the drawer.
	         // ActionBarDrawerToggle will take care of this.
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	        // Handle action buttons
	        switch(item.getItemId()) {
	        case R.id.action_settings:
	            // create intent to perform web search for this planet
	            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
	            intent.putExtra(SearchManager.QUERY, getSupportActionBar().getTitle());
	            // catch event that there's no activity to handle intent
	            if (intent.resolveActivity(getPackageManager()) != null) {
	                startActivity(intent);
	            } else {
	                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
	            }
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }

	    /* The click listner for ListView in the navigation drawer */
	    private class DrawerItemClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            selectItem(position);
	        }
	    }

	    private void selectItem(int position) {
	        // update the main content by replacing fragments
	    	 Fragment fragment = new TaskFragment();
	         Bundle args = new Bundle();
	         
	         args.putString(TaskFragment.ARG_TASK_ID, data.get(position).getTaskid());
	         args.putString(TaskFragment.ARG_TASK_ACCOUNTID, data.get(position).getAccountid());
	         args.putString(TaskFragment.ARG_TASK_startdate, data.get(position).getStartdate());
	         args.putString(TaskFragment.ARG_TASK_name, data.get(position).getName());
	         args.putString(TaskFragment.ARG_TASK_enddate, data.get(position).getEnddate());
	         args.putString(TaskFragment.ARG_TASK_description, data.get(position).getDescription());
	         args.putString(TaskFragment.ARG_TASK_taskstatus, data.get(position).getTaskstatus());
	         args.putString(TaskFragment.ARG_TASK_projectid, data.get(position).getProjectid());
	         args.putString(TaskFragment.ARG_TASK_companyid,data.get(position).getCompanyid());
	         args.putInt(TaskFragment.position, position);
	         fragment.setArguments(args);
	  
	        FragmentManager fragmentManager = getSupportFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

	        // update selected item and title, then close the drawer
	        mDrawerList.setItemChecked(position, true);
	        setTitle(data.get(position).getName());
	        mDrawerLayout.closeDrawer(mDrawerList);
	    }
	    
	    /**
	     * Fragment that appears in the "content_frame", shows a planet
	     */
	    public static class TaskFragment extends Fragment {
	    	
	        public static final String position="position";
			public static final String ARG_TASK_ID = "TASK_ID";
	        public static final String ARG_TASK_ACCOUNTID = "TASK_ID";
	        public static final String ARG_TASK_startdate = "startdate";
	        public static final String ARG_TASK_name= "name";
	        public static final String ARG_TASK_enddate = "enddate";
	        public static final String ARG_TASK_description= "description";
	        public static final String ARG_TASK_taskstatus = "taskstatus";
	        public static final String ARG_TASK_projectid = "projectid";
	        public static final String ARG_TASK_companyid = "companyid";
	        
	        public TaskFragment() {
	            // Empty constructor required for fragment subclasses
	        }
	        	TextView startdateview,enddateview,nameview;
				private Button stop;
				private Button start;	
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	            View rootView = inflater.inflate(R.layout.taskfragment, container, false);
	            final int pos=getArguments().getInt(position);
	            String taskid = getArguments().getString(ARG_TASK_ID);
	           String accountid=getArguments().getString(ARG_TASK_ACCOUNTID);
	           String startdate=getArguments().getString(ARG_TASK_startdate);
	           String enddate=getArguments().getString(ARG_TASK_enddate);
	           final String name=getArguments().getString(ARG_TASK_name);
	           
	           startdateview=(TextView)rootView.findViewById(R.id.startdate);
	           enddateview=(TextView)rootView.findViewById(R.id.endate);
	           nameview=(TextView)rootView.findViewById(R.id.taskname);
	           
	           startdateview.setText(startdate);
	           enddateview.setText(enddate);
	           nameview.setText(name);
	           
	           stop=(Button)rootView.findViewById(R.id.stopit);
			stop.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if(isMyServiceRunning(CountTimeService.class)==true)
						{
							    Intent i=new Intent();
						        i.setAction("com.svimtech.services.ACTION_STOP");
						      
						        i.putExtra("ID", pos);
						        i.putExtra("DELETE", name);
						        getActivity().startService(i);
						       
						}
						else
						{
							Toast.makeText(getActivity(), "Timer Already running", Toast.LENGTH_SHORT).show();
						}
					}
				});
			
			
			start=(Button)rootView.findViewById(R.id.start);
			start.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					if(isMyServiceRunning(CountTimeService.class)==false)
					{
						    Intent i=new Intent();
					        i.setAction("com.svimtech.services.ACTION_START");
					        i.putExtra("PROJECT", name);
					        i.putExtra("ID", pos);
					        getActivity().startService(i);
					}
					else
					{
						Toast.makeText(getActivity(), "Timer Already running", Toast.LENGTH_SHORT).show();
					}
				     
				}
			});
	      
	           
	            return rootView;
	        }
	        public  boolean isMyServiceRunning(Class<?> serviceClass) {
			    ActivityManager manager = (ActivityManager)getActivity().getSystemService(Context.ACTIVITY_SERVICE);
			    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			        if (serviceClass.getName().equals(service.service.getClassName())) {
			            return true;
			        }
			    }
			    return false;
			}
	    }
	    
	

	    @Override
	    public void setTitle(CharSequence title) {
	        mTitle = title;
	        getSupportActionBar().setTitle(mTitle);
	    }

	    /**
	     * When using the ActionBarDrawerToggle, you must call it during
	     * onPostCreate() and onConfigurationChanged()...
	     */

	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	        mDrawerToggle.syncState();
	    }

	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        // Pass any configuration change to the drawer toggls
	        mDrawerToggle.onConfigurationChanged(newConfig);
	    }

	   
	}