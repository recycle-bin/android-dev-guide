package com.njlabs.guide.android.dev;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class Vibration extends SherlockActivity {

	TextView Status;
	Boolean isVibratingIndefinite=false;
	Boolean isVibratingPattern=false;
	Vibrator v;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vibration);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Status = (TextView) findViewById(R.id.status);
        WebView webView = (WebView) findViewById(R.id.webViewManifest);
        webView.setWebViewClient(new WebViewClient(){
        	@TargetApi(11)
        	@Override
        	public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        	    Log.d("shouldInterceptRequest", url);

        	    InputStream stream = inputStreamForAndroidResource(url);
        	    if (stream != null) {
        	        return new WebResourceResponse("text/javascript", "utf-8", stream);
        	    }
        	    return super.shouldInterceptRequest(view, url);
        	}

        	private InputStream inputStreamForAndroidResource(String url) {
        	    final String ANDROID_ASSET = "file:///android_asset/";

        	    if (url.contains(ANDROID_ASSET)) {
        	        url = url.replaceFirst(ANDROID_ASSET, "");
        	        try {
        	            AssetManager assets = getAssets();
        	            Uri uri = Uri.parse(url);
        	            return assets.open(uri.getPath(), AssetManager.ACCESS_STREAMING);
        	        } catch (IOException e) {
        	            e.printStackTrace();
        	        }
        	    }
        	    return null;
        	}        	
        	
        });
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/code_snippets/vibration_manifest.html");		
        webView = (WebView) findViewById(R.id.webViewJava);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient());
		webView.loadUrl("file:///android_asset/code_snippets/vibration_java.html");
	}
	
	public void DefiniteVibrate(View view)
	{
		EditText EnteredSecondsField = (EditText) findViewById(R.id.milliseconds);
		String EnteredSeconds=EnteredSecondsField.getText().toString();
		if(EnteredSeconds==null||EnteredSeconds==""||EnteredSeconds=="0")
		{
			Toast.makeText(getApplicationContext(), "Please enter a Time (in milliseconds)", Toast.LENGTH_SHORT).show();
		}
		else
		{
			// Get instance of Vibrator from current Context
			v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			// Vibrate the entered amount of time
			Long TimeValue=Long.parseLong(EnteredSeconds);
			v.vibrate(TimeValue);
		}
	}
	public void IndefiniteVibrate(View view)
	{
		Button IndefiniteVibrateButton = (Button) findViewById(R.id.IndefiniteVibrateButton);
		v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		if(isVibratingIndefinite)
		{
			v.cancel();
			isVibratingIndefinite=false;
			IndefiniteVibrateButton.setText("Start Vibration");
		}
		else
		{
			// Get instance of Vibrator from current Context
			// Start without a delay
			// Vibrate for 100 milliseconds
			// Sleep for 1000 milliseconds
			long[] pattern = {0, 1000, 100};
			// The '0' here means to repeat indefinitely
			// '-1' would play the vibration once
			v.vibrate(pattern, 0);
			isVibratingIndefinite=true;
			IndefiniteVibrateButton.setText("Stop Vibration");
		}

	}
	public void PatternVibrate(View view)
	{
		v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Get instance of Vibrator from current Context
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Start without a delay
		// Each element then alternates between vibrate, sleep, vibrate, sleep...
		long[] pattern = {0, 100, 1000, 300, 200, 100, 500, 200, 100};
		// The '-1' here means to vibrate once
		// '0' would make the pattern vibrate indefinitely
		v.vibrate(pattern, -1);
	}
	
    private Menu mainMenu;
    private SubMenu subMenu1;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        mainMenu = menu;

        subMenu1 = menu.addSubMenu("Options");
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main, subMenu1);

        MenuItem subMenu1Item = subMenu1.getItem();
        subMenu1Item.setIcon(R.drawable.abs__ic_menu_moreoverflow_normal_holo_dark);
        subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }  
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                return true;
            case R.id.AboutAppOption:
                Intent intent1 = new Intent(this, AboutApp.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                return true;
            case R.id.BugReportOption:
                Intent intent2 = new Intent(this, BugReport.class);
                startActivity(intent2);
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
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
                mainMenu.performIdentifierAction(subMenu1.getItem().getItemId(), 0);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
