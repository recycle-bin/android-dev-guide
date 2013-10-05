package com.njlabs.guide.android.dev;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SmsMms extends SherlockActivity {

	TextView Status;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_mms);
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
		webView.loadUrl("file:///android_asset/code_snippets/sms_manifest.html");		
        webView = (WebView) findViewById(R.id.webViewJava);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/code_snippets/sms_mms_java.html");
	}
	public void OpenSMS(View view)
	{
		Boolean error=false;
		EditText EnteredNumberField = (EditText) findViewById(R.id.EnteredNumber);
		EditText EnteredMsgField = (EditText) findViewById(R.id.EnteredMsg);
		String EnteredNumber=EnteredNumberField.getText().toString();
		String EnteredMsg=EnteredMsgField.getText().toString();
		if(EnteredNumber==null||EnteredNumber=="")
		{
			Toast.makeText(getBaseContext(), "Please enter a number...", Toast.LENGTH_SHORT).show();
			error=true;
		}
		if(EnteredMsg==null||EnteredMsg=="")
		{
			Toast.makeText(getBaseContext(), "Please enter a message...", Toast.LENGTH_SHORT).show();
			error=true;
		}
		if(!error)
		{
			Intent SmsIntent = new Intent(Intent.ACTION_VIEW);
			SmsIntent.putExtra("address", EnteredNumber);
			SmsIntent.putExtra("sms_body", EnteredMsg); 			
			SmsIntent.setType("vnd.android-dir/mms-sms");
			startActivity(SmsIntent);
			EnteredNumberField.setText(null);
			EnteredMsgField.setText(null);
		}
		
	}
	public void OpenMMS(View view)
	{
		Boolean error=false;
		EditText EnteredNumberField = (EditText) findViewById(R.id.EnteredNumber);
		EditText EnteredMsgField = (EditText) findViewById(R.id.EnteredMsg);
		String EnteredNumber=EnteredNumberField.getText().toString();
		String EnteredMsg=EnteredMsgField.getText().toString();
		if(EnteredNumber==null||EnteredNumber=="")
		{
			Toast.makeText(getApplicationContext(), "Please enter a number...", Toast.LENGTH_SHORT).show();
			error=true;
		}
		if(EnteredMsg==null||EnteredMsg=="")
		{
			Toast.makeText(getApplicationContext(), "Please enter a message...", Toast.LENGTH_SHORT).show();
			error=true;
		}
		if(!error)
		{
			copyAsset("mms_sample.png");
			Intent MmsIntent = new Intent(Intent.ACTION_SEND);
			MmsIntent.putExtra("address", EnteredNumber);
			MmsIntent.putExtra("sms_body", EnteredMsg);			 
			//attaching an image
			File img = new File("/sdcard/mms_sample.png");
			MmsIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(img));   
			MmsIntent.setType("image/png");
			startActivity(MmsIntent);
			EnteredNumberField.setText(null);
			EnteredMsgField.setText(null);
		}
	}
	public void SendSMS(View view)
	{
		Boolean error=false;
		EditText EnteredNumberField = (EditText) findViewById(R.id.EnteredNumber);
		EditText EnteredMsgField = (EditText) findViewById(R.id.EnteredMsg);
		String EnteredNumber=EnteredNumberField.getText().toString();
		String EnteredMsg=EnteredMsgField.getText().toString();
		if(EnteredNumber==null||EnteredNumber=="")
		{
			Toast.makeText(getApplicationContext(), "Please enter a number...", Toast.LENGTH_SHORT).show();
			error=true;
		}
		if(EnteredMsg==null||EnteredMsg=="")
		{
			Toast.makeText(getApplicationContext(), "Please enter a message...", Toast.LENGTH_SHORT).show();
			error=true;
		}
		if(!error)
		{
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(EnteredNumber, null, EnteredMsg, null, null);
			EnteredNumberField.setText(null);
			EnteredMsgField.setText(null);
			Status.setText("SMS Sent !");
		}
		
	}
	public void ClearNum(View view)
	{
		EditText EnteredNumberField = (EditText) findViewById(R.id.EnteredNumber);
		EnteredNumberField.setText(null);
	}
	public void ClearMsg(View view)
	{
		EditText EnteredMsgField = (EditText) findViewById(R.id.EnteredMsg);
		EnteredMsgField.setText(null);
	}
	
	private void copyAsset(String filename)
	{
	    AssetManager assetManager = getAssets();
	    InputStream in = null;
	    OutputStream out = null;
	    try 
	    {
		    in = assetManager.open(filename);
		    File outFile = new File(getExternalFilesDir(null), filename);
		    out = new FileOutputStream(outFile);
		    copyFile(in, out);
		    in.close();
		    in = null;
		    out.flush();
		    out.close();
		    out = null;
	    } 
	    catch(IOException e) 
	    {
		    Log.d("tag", "Failed to copy asset file: " + filename, e);
	    }       
	}
	private void copyFile(InputStream in, OutputStream out) 
	{
	    byte[] buffer = new byte[1024];
	    int read;
	    try 
	    {
			while((read = in.read(buffer)) != -1)
			{
			  out.write(buffer, 0, read);
			}
		} 
	    catch (IOException e) 
		{
			Log.d("tag", "Failed to copy asset file");
		}
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
