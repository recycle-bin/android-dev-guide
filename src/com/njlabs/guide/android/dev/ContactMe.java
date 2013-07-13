package com.njlabs.guide.android.dev;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ContactMe extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_me);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_me, menu);
		return true;
	}

}
