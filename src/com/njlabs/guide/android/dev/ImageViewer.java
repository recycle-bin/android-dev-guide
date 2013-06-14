package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import android.os.Bundle;
import android.view.View;

public class ImageViewer extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		String image = extras.getString("image");
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		TouchImageView touch = new TouchImageView(this);
		touch.setImageResource(getResources().getIdentifier(image, "drawable",  getPackageName()));
		touch.setMaxZoom(4f);
		setContentView(touch);
		touch.setOnClickListener(zoom_exit_handler);	
		}
		View.OnClickListener zoom_exit_handler = new View.OnClickListener() {
		    public void onClick(View v) {
		    	finish();
		    	overridePendingTransition(R.anim.appear,R.anim.appear);
		    }
		};
	    public void onBackPressed() {
	        super.onBackPressed();
	        overridePendingTransition(R.anim.appear,R.anim.appear);
	    }
}
