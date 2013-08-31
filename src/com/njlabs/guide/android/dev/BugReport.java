package com.njlabs.guide.android.dev;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.andreabaccega.widget.FormEditText;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

/**
 * Created by Niranjan on 5/17/13.
 */
public class BugReport extends SherlockActivity {
	
	private AQuery aq;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bug_report);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        aq = new AQuery(this);
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        
        // check for Internet status
        if (isInternetPresent) {
            // Internet Connection is Present
            // proceed normally

        } else {
            // Internet connection is not present
            // Ask user to connect to Internet
            AlertDialog.Builder builder = new AlertDialog.Builder(this);    // ALERT DIALOG
            builder.setTitle("No Internet Connection")
            		.setMessage("A working internet connection is required for accessing Amrita UMS !")
                    .setCancelable(false)
                    .setIcon(R.drawable.warning)
                    .setPositiveButton("Got it !", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(BugReport.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            
        }
    }
    public void SendReport(View view) throws UnsupportedEncodingException
    {
    	FormEditText EmailID = (FormEditText) findViewById(R.id.email_id);
        FormEditText BugLocation = (FormEditText) findViewById(R.id.bug_location);
        FormEditText Bug = (FormEditText) findViewById(R.id.bug);
    	FormEditText[] allFields    = { EmailID,BugLocation,Bug  };
    	boolean allValid = true;
        for (FormEditText field: allFields) {
            allValid = field.testValidity() && allValid;
        }
        if (allValid) {
        	// GET DATA FROM BOX
        	String  email = EmailID.getText().toString().trim(); 
        	String  bug_location = BugLocation.getText().toString().trim(); 
        	String  bug = Bug.getText().toString().trim();
        	// URL ENCODE DATA
        	email = URLEncoder.encode(email, "utf-8");
        	bug_location = URLEncoder.encode(bug_location, "utf-8");
        	bug = URLEncoder.encode(bug, "utf-8");
        	// PROGRESS DIALOG
        	ProgressDialog dialog = new ProgressDialog(this);
        	dialog.setMessage("Sending Your Report...");
        	// AQUERY SEND
        	String url = "http://njlabs.kovaideals.com/api/aid/bug_report.php?email="+email+"&bug_location=AGAD-"+bug_location+"&bug=AGAD-"+bug+"";
            aq.progress(dialog).ajax(url, String.class, new AjaxCallback<String>() {
                    @Override
                    public void callback(String url, String html, AjaxStatus status) { 
                    	Toast.makeText(getApplicationContext(),html, Toast.LENGTH_LONG).show();
                    	Intent intent = new Intent(BugReport.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein,R.anim.fadeout);                        
                    }                   
            });        	
        } else {
            // EditText are going to appear with an exclamation mark and an explicative message.
        }
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