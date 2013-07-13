package com.njlabs.guide.android.dev;

import org.codechimp.apprater.AppRater;

import android.app.Application;

import com.parse.Parse;

public class MainApplication extends Application {
	
	public void onCreate() { 
		super.onCreate();
		AppRater.app_launched(this);
	    Parse.initialize(this, "pkJJlHY3G50SV7njK0IB4va3AWufykgt0g7eDHi2", "nJlB4f3Hq1avivVu7RGMV6vmS9BMOE4KoPsekeHE"); 
	}
}
