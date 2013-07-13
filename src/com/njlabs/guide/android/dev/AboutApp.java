package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class AboutApp extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_app);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setSubtitle("What is this app ?");
		TextView item = (TextView) this.findViewById(R.id.textViewAboutApp);
        item.setText(Html.fromHtml("<h2>WHAT IS THIS ?</h2><p>This app is Just what the name suggest... A Simple but Complete (almost) Guide to Android Development. Covers everything from Setting up an IDE, SDK etc on your Development Machine to Threading and using the Android System API's.</p><h2> FEATURES:</h2><p> &#9679;&nbsp; An almost complete guide covering a wide range of topics<br>&#9679;&nbsp; Contains live working demos for most of the topics covered here so that you can easily understand how it works.<br>&#9679;&nbsp; The used .java and .xml code snippets are also provided in an understandable, syntax-highlighted form for easy learning.<br>&#9679;&nbsp; The Entire application itself is open-sourced so that the users can download the app's source and explore for better understanding. </p><p><blockquote><strong>NOTE:</strong> Demo for some of the System API's and threads have not been added yet. And will be done so in the upcoming release. Sorry ...</blockquote></p><h2> UPCOMING FEATURES:</h2><p> &#9679;&nbsp; A Feed from many Tech Blogs<br>&#9679;&nbsp; Tech News (with notifications)<br>&#9679;&nbsp; Periodical Auto-Updates<br>&#9679;&nbsp; And more to come ...</p><h2> WHERE CAN I GET THE APP's SOURCE-CODE ?</h2><p> The source is hosted on my favorite github.com. You can access by using the below information.<br><br><strong>Web Link:</strong><font color='#0099cc' >https://github.com/nj-labs/android-dev-guide</font><br><strong>git via HTTP:</strong><font color='#0099cc' >https://github.com/nj-labs/android-dev-guide.git</font><br><strong>git via SSH:</strong><font color='#0099cc' >git@github.com:nj-labs/android-dev-guide.git</font><br></p>"));
	}
	
	public void OpenGithub(View view)
	{
		Uri uri = Uri.parse("https://github.com/nj-labs/android-dev-guide");
		Intent it  = new Intent(Intent.ACTION_VIEW,uri);
		startActivity(it);
	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                //Intent intent = new Intent(this, MainActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
            	finish();
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                return true;
            case R.id.AboutAppOption:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                return true;
            case R.id.BugReportOption:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                return true;
            case R.id.ContactOption:
                Intent intent3 = new Intent(this, MainActivity.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
        
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

}
