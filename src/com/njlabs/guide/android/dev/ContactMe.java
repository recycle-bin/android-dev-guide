package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Niranjan on 5/17/13.
 */
public class ContactMe extends SherlockActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_me);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public void EmailMe(View view)
    {
    	Intent it = new Intent(Intent.ACTION_SEND);   
    	it.putExtra(Intent.EXTRA_EMAIL, "niranjan@leanlab.in");   
    	it.putExtra(Intent.EXTRA_TEXT, "Hi Niranjan,");   
    	it.setType("text/plain");   
    	startActivity(Intent.createChooser(it, "Choose an Email Client"));
    }
    public void FBMe(View view)
    {
    	Uri uri = Uri.parse("http://www.facebook.com/niranjan94");
    	Intent it  = new Intent(Intent.ACTION_VIEW,uri);
    	startActivity(it);
    }
    public void FBNJLabs(View view)
    {
    	Uri uri = Uri.parse("http://www.facebook.com/njlabs");
    	Intent it  = new Intent(Intent.ACTION_VIEW,uri);
    	startActivity(it);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
    }
}
