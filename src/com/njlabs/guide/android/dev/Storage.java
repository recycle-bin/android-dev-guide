package com.njlabs.guide.android.dev;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class Storage extends SherlockActivity {
	
	String title=null;
	String codesnippet=null;
	EditText editText;
	TextView status;
	Boolean StatusError=false;	
	
	boolean mExternalStorageAvailable = false;
	boolean mExternalStorageWriteable = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storage);
		
		Bundle extras = getIntent().getExtras();
		title = extras.getString("title");
		codesnippet = extras.getString("codesnippet");
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setSubtitle(title);     
        status = (TextView)findViewById(R.id.textViewStatus);
        editText = (EditText)findViewById(R.id.editText);
        TextView textView = (TextView) findViewById(R.id.textView);
        WebView webView = (WebView) findViewById(R.id.webViewJavaStorage);
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
        if(title.equals("Shared Preferences"))
        {
        	textView.setText("Shared Preferences can Store private primitive data in key-value pairs. (Supports Boolean, String, Float, Long and Int");
        	webView.loadUrl("file:///android_asset/code_snippets/"+codesnippet+"_java.html");
        }
        else if(title.equals("Internal Memory"))
        {
        	textView.setText("You can Store private data on the Internal memory. (any type of file)");
        	webView.loadUrl("file:///android_asset/code_snippets/"+codesnippet+"_java.html");
        }
        else if(title.equals("External Memory"))
        {
            TextView textViewManifest = (TextView) findViewById(R.id.textViewManifest);
            WebView webViewManifest = (WebView) findViewById(R.id.webViewManifest);
            TextView DummyManifest = (TextView) findViewById(R.id.DummyManifest);
            textViewManifest.setVisibility(View.VISIBLE);
            webViewManifest.setVisibility(View.VISIBLE);
            DummyManifest.setVisibility(View.VISIBLE);
            
            webViewManifest.setWebViewClient(new WebViewClient(){
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
            webViewManifest.getSettings().setJavaScriptEnabled(true);
            webViewManifest.loadUrl("file:///android_asset/code_snippets/storage_manifest.html");
        	// GET EXTERNAL MEMORY READ_WRITE STATE 
        	String state = Environment.getExternalStorageState();
        	if (Environment.MEDIA_MOUNTED.equals(state)) {
        	    // We can read and write the media
        	    mExternalStorageAvailable = mExternalStorageWriteable = true;
        	} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
        	    // We can only read the media
        	    mExternalStorageAvailable = true;
        	    mExternalStorageWriteable = false;
        	} else {
        	    // Something else is wrong. It may be one of many other states, but all we need
        	    //  to know is we can neither read nor write
        	    mExternalStorageAvailable = mExternalStorageWriteable = false;
        	}
        	textView.setText("You can store Store public data on the shared external storage. (any type of file)");
        	webView.loadUrl("file:///android_asset/code_snippets/"+codesnippet+"_java.html");
        }
        status.setText(" ");
	}
    public void Store(View view)
    {
    	String EnteredText=editText.getText().toString();

        if(title.equals("Shared Preferences"))
        {   
        	///
        	/// STORE TO SHARED PREFERENCES
        	///
        	SharedPreferences preferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        	SharedPreferences.Editor editor = preferences.edit();
        	editor.putString("Demo Text", EnteredText);        	
        	editor.commit();
        	StatusError=false;
        }
        else if(title.equals("Internal Memory"))
        {
        	///
        	///  STORE TO INTERNAL MEMORY
        	///
        	FileOutputStream fop = null;
			try 
			{
				// OPEN FILE OUTPUT STREAM
				fop = openFileOutput("data_file.txt", Context.MODE_PRIVATE);
				StatusError=false;
			} 
			catch (FileNotFoundException e) 
			{
				StatusError=true;
			}  
        	try 
        	{
        		if(!StatusError)
        		{
        			// WRITE DATA TO STREAM
					fop.write(EnteredText.getBytes());
					StatusError=false;
        		}
        	} 
        	catch (IOException e) 
        	{
				StatusError=true;
			}
        	try 
        	{
        		if(!StatusError)
        		{
        			// FLUSH STREAM
					fop.flush();
					StatusError=false;
        		}
			} 
        	catch (IOException e) 
        	{
				StatusError=true;
			}
        	try 
        	{
        		// CLOSE THE STREAM
				fop.close();
				StatusError=false;
			} 
        	catch (IOException e) 
        	{
				StatusError=true;
			}
        }
        else if(title.equals("External Memory"))
        {
        	///
        	/// STORE TO EXTERNAL MEMORY
        	///
        	if(mExternalStorageAvailable==false||mExternalStorageWriteable==false)
        	{
        		StatusError=true;
        	}
        	FileOutputStream fop = null;
        	File path = null;
        	if(!StatusError)
			{
	        	path = Environment.getExternalStorageDirectory();	        	
			}
			try 
			{
				if(!StatusError)
				{
					fop = new FileOutputStream(new File(path, "data_file.txt"));
					StatusError=false;
				}
			} 
			catch (FileNotFoundException e) 
			{
				StatusError=true;
			}
        	try 
        	{
				if(!StatusError)
				{
					fop.write(EnteredText.getBytes());
					StatusError=false;
				}
			} 
        	catch (IOException e) 
        	{
				StatusError=true;
			}
        	try 
        	{
				if(!StatusError)
				{
					fop.flush();
					StatusError=false;
				}
			} 
        	catch (IOException e) 
        	{
				StatusError=true;
			}
        	try 
        	{
				if(!StatusError)
				{
	        		fop.close();
					StatusError=false;
				}
			} 
        	catch (IOException e) 
        	{
				StatusError=true;
			}

        }
        if(StatusError==false)
        {
	        editText.setText(null, TextView.BufferType.EDITABLE);
	        status.setText("Stored !");
        }
        else
        {
        	if(title.equals("External Memory"))
        	{
        		status.setText("FileNotFound / IOException ! (Check if SD Card is present and writable)");
        	}
        	else
        	{
        		status.setText("FileNotFound / IOException");
        	}
        }
    }
    public void Read(View view)
    {
    	String ReadText=null;
        if(title.equals("Shared Preferences"))
        {
        	///
        	/// READ FROM SHARED PREFERENCES
        	///
        	SharedPreferences preferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        	ReadText = preferences.getString("Demo Text", null);   
        	StatusError=false;
        }
        else if(title.equals("Internal Memory"))
        {
        	///
        	/// READ FROM INTERNAL MEMORY
        	///
        	int ch;
        	StringBuilder str = new StringBuilder();
        	FileInputStream fs = null;
			try 
			{
				// OPEN THE FILE
				fs = openFileInput("data_file.txt");
				StatusError=false;
			} 
			catch (FileNotFoundException e) 
			{
				StatusError=true;
			}
        	try 
        	{    
        		if(!StatusError)
        		{	
        			// GET EACH CHARACHTER
					while ((ch = fs.read()) != -1)
					{
						str.append((char) ch);
					}
					StatusError=false;
					// WRITE TO STRING
					ReadText=str.toString();
        		}
        	} 
        	catch (IOException e) 
        	{
				StatusError=true;
			}        	
        }
        else if(title.equals("External Memory"))
        {
        	if(mExternalStorageAvailable==false)
        	{
        		StatusError=true;
        	}
        	File path=null;
        	FileInputStream fs = null;
        	///
        	/// READ FROM EXTRENAL MEMORY
        	///
        	int ch;
        	StringBuilder str = new StringBuilder();
        	if(!StatusError)
        	{
	        	// GET EXTERNAL STORAGE DIRECTORY FILE PATH
	        	path = Environment.getExternalStorageDirectory();
        	}
        	try 
        	{
        		if(!StatusError)
        		{
					fs = new FileInputStream(new File(path, "data_file.txt"));
					StatusError=false;
        		}
			}
        	catch (FileNotFoundException e) 
        	{
				StatusError=true;
			}
        	try 
        	{
        		if(!StatusError)
        		{	
					while ((ch = fs.read()) != -1)
					{
						str.append((char) ch);
					}
					ReadText=str.toString();
					StatusError=false;
        		}
			} 
        	catch (IOException e) 
        	{
				StatusError=true;
			}
        	try 
        	{
        		if(!StatusError)
        		{
					fs.close();
					StatusError=false;
        		}
			} 
        	catch (IOException e) 
        	{
				StatusError=true;
			}

        }
        if(StatusError==false)
        {	
	        editText.setText(ReadText, TextView.BufferType.EDITABLE);
	        status.setText("Retrieved ! ");
        }
        else
        {
        	if(title.equals("External Memory"))
        	{
        		status.setText("Make sure You have saved something ! (Check if SD Card is present)");
        	}
        	else
        	{
        		status.setText("Make sure You have saved something !");
        	}
        }
    }
    public void Clear(View view)
    {
    	editText.setText(null, TextView.BufferType.EDITABLE);
    	status.setText("Cleared ! ");
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
            case R.id.ContactOption:
                Intent intent3 = new Intent(this, ContactMe.class);
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
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
                mainMenu.performIdentifierAction(subMenu1.getItem().getItemId(), 0);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
