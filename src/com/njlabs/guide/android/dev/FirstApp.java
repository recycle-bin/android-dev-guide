package com.njlabs.guide.android.dev;

import java.io.IOException;
import java.io.InputStream;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetManager;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class FirstApp extends SherlockActivity {
	int pos_x=0;
	int pos_y=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_app);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setSubtitle("So... Let's get started !");
		TextView item = (TextView) this.findViewById(R.id.textViewFirstApp1);
        item.setText(Html.fromHtml("<p>Ok then... Let's get started with our first app ... And as I have already said this guide is based on the<strong> Eclipse IDE</strong> (<em>Juno SR2 to be specific</em>)... </p><p>•First, open up <strong>Eclipse</strong>.</p><p>•Then select <strong>File » New » Project » Android Application Project</strong>.</p>"));
		
		item = (TextView) this.findViewById(R.id.textViewFirstApp2);
        item.setText(Html.fromHtml("<p><br>•Press <strong>Next</strong>.</p><p>•Now, give a <strong>name for your application</strong> (<em>this will be shown in the Play Store and in the device</em>)</p><p>•The <strong>package name is important</strong>. It should remain the same for the entire lifetime of your Application (<em>irrespective of any new versions</em>).</p><p>•A package name is usually in the form of com.organaisationname.appname (<em>or</em>) any other <strong>valid Java Package name</strong>.</p><p>•<strong>Minimum Required SDK</strong> is the lowest android version that your app will support</p><p>•<strong>Target SDK</strong> is the newest android version that your app will support</p><p>•<strong>Compile with</strong> is usually the most recent Android Version</p><p>•Select a <strong>theme</strong> for your app.</p>"));

		item = (TextView) this.findViewById(R.id.textViewFirstApp3);
        item.setText(Html.fromHtml("<p><br>•Then press <strong>Next</strong></p><p>•You don't generally have to change anything here. So <strong>Next</strong> we go...</p>"));
        
        item = (TextView) this.findViewById(R.id.textViewFirstApp4);
        item.setText(Html.fromHtml("<p><br>•Select and format an <strong>icon</strong> for your application (<em>or you could even use a text/clipart</em>)</p><p>•Press <strong>Next</strong></p>"));

        item = (TextView) this.findViewById(R.id.textViewFirstApp5);
        item.setText(Html.fromHtml("<p><br>•Now we'll select a <strong>Blank Activity</strong> for our project. (<em>This will have an Action bar and optional navigation elements like tabs/swipe etc.</em>)</p><p>•Press <strong>Next</strong></p>"));
        
        item = (TextView) this.findViewById(R.id.textViewFirstApp6);
        item.setText(Html.fromHtml("<p><br>•Now give a <strong>name for your Activity</strong> (<em>better to follow Java naming conventions</em>)</p><p>•Give a <strong>name for the xml layout file</strong> for that Activity</p><p>•Press <strong>finish</strong>!</p>"));
       
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
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/code_snippets/firstapp_xml.html");
		
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
		webView1.loadUrl("file:///android_asset/code_snippets/firstapp_java.html");
	}
	public void zoomin(View view)
	{
		String pageNumber = (String) view.getTag(); 
   		Intent it  = new Intent(getBaseContext(), ImageViewer.class);
   		it.putExtra("image",pageNumber);
   		startActivity(it);
   		overridePendingTransition(R.anim.appear,R.anim.appear);
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
