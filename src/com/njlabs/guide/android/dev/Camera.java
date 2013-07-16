package com.njlabs.guide.android.dev;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Camera extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		
	}
	public void TakePic(View view)
	{
		Intent anIntent=new Intent(this,CameraActivity.class);
		startActivity(anIntent);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera, menu);
		return true;
	}

}
