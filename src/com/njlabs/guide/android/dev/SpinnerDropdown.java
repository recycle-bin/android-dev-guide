package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Menu;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.content.Intent;

public class SpinnerDropdown extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner_dropdown);
		
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        WebView webView = (WebView) findViewById(R.id.webViewXML);
		webView.getSettings().setJavaScriptEnabled(true);		
		webView.loadUrl("file:///android_asset/code_snippets/spinner_dropdown_xml.html");
		
        WebView webView1 = (WebView) findViewById(R.id.webViewJava);
		webView1.getSettings().setJavaScriptEnabled(true);		
		webView1.loadUrl("file:///android_asset/code_snippets/spinner_dropdown_java.html");
	}
	public void DemoSpinnerDropdown(View view)
	{
		Intent intent1 = new Intent(this, SpinnerDemo.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
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
