package com.njlabs.guide.android.dev;

import java.io.IOException;
import java.io.InputStream;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.SubMenu;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;

public class Dialogs extends SherlockActivity {
	
	String title=null;
	String codesnippet=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialogs);
		
		Bundle extras = getIntent().getExtras();
		title = extras.getString("title");
		codesnippet = extras.getString("codesnippet");
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(title);
        
        TextView textView = (TextView) findViewById(R.id.textViewDialogs);
        if(title.equals("Simple Dialog"))
        {
        	textView.setText("Let's see how we can easily make a simple Alert dialog with one button to dismiss it.");
        }
        else if(title.equals("Response Dialog"))
        {
        	textView.setText("Here we make an Alert DIalog with two buttons to get the respose of the user");
        }
        else if(title.equals("List Dialog"))
        {
        	textView.setText("Now we move on to an alert dialog with a list of items that the user can select.");
        }
        else if(title.equals("Custom Dialog"))
        {
        	textView.setText("This will be an ALert Dialog that will be cutom styled using a XML Layout file to have a Text Input and two buttons.");
        }        
        WebView webView = (WebView) findViewById(R.id.webViewXML);
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
        if(title.equals("Custom Dialog"))
        {
        	webView.getSettings().setJavaScriptEnabled(true);		
        	webView.loadUrl("file:///android_asset/code_snippets/"+codesnippet+"_xml.html");
        	
        }
        else
        {
        	webView.setVisibility(View.GONE);
        	TextView textView1 = (TextView) findViewById(R.id.textViewDialogs7);
        	textView1.setVisibility(View.GONE);
        	textView1 = (TextView) findViewById(R.id.textViewDialogsdum);
        	textView1.setVisibility(View.GONE);
        }
		
        WebView webView1 = (WebView) findViewById(R.id.webViewJava);
        webView1.setWebViewClient(new WebViewClient(){
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
		webView1.getSettings().setJavaScriptEnabled(true);
		webView1.loadUrl("file:///android_asset/code_snippets/"+codesnippet+"_java.html");
	}
	public void DemoDialog(View view)
	{
        if(title.equals("Simple Dialog"))
        {
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setTitle("The Dialog's Title").setIcon(R.drawable.info)
        	.setMessage("This is a simple message. Try to press the below button.")
        	      .setCancelable(false)
        	      .setPositiveButton("Press Me", new DialogInterface.OnClickListener() {
        	          public void onClick(DialogInterface dialog, int id) {
        	        	  Toast.makeText(getBaseContext(), "You clicked the button !", Toast.LENGTH_LONG).show();
        	          }
        	      });
        	AlertDialog alert = builder.create();
        	alert.show();
        }
        else if(title.equals("Response Dialog"))
        {
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setTitle("The Dialog's Title").setIcon(R.drawable.info)
        	.setMessage("This is a simple message. Try to press any one of the buttons below.")
        	      .setCancelable(false)
        	      .setPositiveButton("Yes !", new DialogInterface.OnClickListener() {
        	          public void onClick(DialogInterface dialog, int id) {
        	        	  Toast.makeText(getBaseContext(), "You clicked the YES !", Toast.LENGTH_LONG).show();
        	          }
        	      })
			.setNegativeButton("No !", new DialogInterface.OnClickListener() {
			          public void onClick(DialogInterface dialog, int id) {
			        	  Toast.makeText(getBaseContext(), "You clicked NO !", Toast.LENGTH_LONG).show();
			          }
			      });
        	AlertDialog alert = builder.create();
        	alert.show();       	
        }
        else if(title.equals("List Dialog"))
        {
        	final CharSequence[] items = {"Item 1", "Item 2", "Item 3"};
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setTitle("Select an Item !").setIcon(R.drawable.info);
        	builder.setItems(items, new DialogInterface.OnClickListener() {
        	   public void onClick(DialogInterface dialog, int item) {
        	       CharSequence choice = items[item];
        	       Toast.makeText(getBaseContext(), "You selected "+choice, Toast.LENGTH_LONG).show();
        	   }
        	});
        	AlertDialog alert = builder.create();
        	alert.show();
        }
        else if(title.equals("Custom Dialog"))
        {
        	AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        	LayoutInflater factory = LayoutInflater.from(this);
        	final View textEntryView = factory.inflate(R.layout.dialog_custom_demo, null);
        	          
        	alt_bld.setView(textEntryView).setCancelable(true).setNegativeButton("Cancel",
        	                   new DialogInterface.OnClickListener() {
        	                             public void onClick(DialogInterface dialog, int id) {
        	                            	  Toast.makeText(getBaseContext(), "You pressed cancel !", Toast.LENGTH_LONG).show();
        	                                      dialog.cancel();
        	                             }
        	                   })
        	                   .setPositiveButton("Submit !", new DialogInterface.OnClickListener() {
        	         	          public void onClick(DialogInterface dialog, int id) {
        	         	        	 EditText editText = (EditText) textEntryView.findViewById(R.id.EditText);
        	         	        	  String value = editText.getText().toString();
        	        	        	  Toast.makeText(getBaseContext(), "You have typed "+value, Toast.LENGTH_LONG).show();
        	        	          }
        	        	      });
        	                    
        	AlertDialog alert = alt_bld.create();
        	alert.setTitle("A custom Dialog Example");
        	alert.setIcon(R.drawable.info);
        	alert.show();
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
