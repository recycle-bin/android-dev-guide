package com.njlabs.guide.android.dev;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BugReport extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bug_report);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bug_report, menu);
		return true;
	}

}
