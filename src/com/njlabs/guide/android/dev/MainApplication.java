package com.njlabs.guide.android.dev;

import android.app.Application;

import com.parse.Parse;

public class MainApplication extends Application {
	
	public void onCreate() { 
		super.onCreate();
		//AppRater.app_launched(this);
	    Parse.initialize(this, "mykey1", "mykey2"); 
	}
}
