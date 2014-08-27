package com.svimtech.easytime;

import com.svimtech.fragments.EasyProjectTask;
import com.svimtech.fragments.EasyProjectTimes;
import com.svimtech.fragments.Easyproject;
import com.svimtech.fragments.EasytimeLog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new Easyproject();
        case 1:
            // Games fragment activity
            return new EasyProjectTask();
        case 2:
            // Movies fragment activity
            return new EasyProjectTimes();
        case 3:
        	return new EasytimeLog();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }
 
}
