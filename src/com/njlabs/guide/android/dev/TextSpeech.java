package com.njlabs.guide.android.dev;

import java.io.IOException;
import java.io.InputStream;

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
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class TextSpeech extends SherlockActivity {

	TextToSpeech talker;
	String EnteredText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_speech);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        WebView webView = (WebView) findViewById(R.id.webViewJava);
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
		webView.loadUrl("file:///android_asset/code_snippets/text_speech.html");				
	
	}
	public void Speak(View view)
	{
		EditText EnteredTextField = (EditText) findViewById(R.id.text);
		EnteredText=EnteredTextField.getText().toString();
		if(EnteredText==null||EnteredText==""||EnteredText==" ")
		{
			Toast.makeText(getBaseContext(), "Please enter something...", Toast.LENGTH_SHORT).show();
		}
		else
		{
			talker = new TextToSpeech(getBaseContext(), new MyOnInitListener());
		}
	}
	class MyOnInitListener implements OnInitListener
	{
        @Override
        public void onInit(int status) {
	        talker.speak(EnteredText, TextToSpeech.QUEUE_FLUSH, null);
        }

        public void onDestroy() {
	        if (talker != null) {
		        talker.stop();
		        talker.shutdown();
	        }
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
