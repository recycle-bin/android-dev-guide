package com.njlabs.guide.android.dev;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class AudioVideo extends SherlockActivity {

	MediaPlayer AudioInternal;
	MediaPlayer AudioExternal;
	VideoView videoPlayer;
	boolean ai=false;
	boolean ae=false;
	boolean vid=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audio_video);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        WebView webView = (WebView) findViewById(R.id.webViewXMLAudioVideo);
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
		webView.loadUrl("file:///android_asset/code_snippets/audio_video_xml.html");	
		
        webView = (WebView) findViewById(R.id.webViewJavaAudioVideo);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/code_snippets/audio_video_java.html");	
	}
	public void PlayAudioInternal(View view)
	{
		View b = findViewById(R.id.btn_start_audio_internal);
		b.setVisibility(View.GONE);		
		AudioInternal = MediaPlayer.create(getBaseContext(), R.raw.sample);
		AudioInternal.start();
		b = findViewById(R.id.btn_stop_audio_internal);
		b.setVisibility(View.VISIBLE);
		ai=true;
	}
	public void StopAudioInternal(View view)
	{
		ai=false;
		View b = findViewById(R.id.btn_stop_audio_internal);
		b.setVisibility(View.GONE);	
		AudioInternal.stop();
		b = findViewById(R.id.btn_start_audio_internal);
		b.setVisibility(View.VISIBLE);
		
	}
	public void PlayAudioExternal(View view) throws IllegalStateException, IOException
	{
		ae=true;		   
		View b = findViewById(R.id.btn_start_audio_external);
		b.setVisibility(View.GONE);	
		AudioExternal = new MediaPlayer();
		AudioExternal.setDataSource("http://njlabs.kovaideals.com/api/agad/sample.mp3");
		AudioExternal.prepare();
		AudioExternal.start();
		b = findViewById(R.id.btn_stop_audio_external);
		b.setVisibility(View.VISIBLE);	 
	}
	public void StopAudioExternal(View view)
	{
		ae=false;
		View b = findViewById(R.id.btn_stop_audio_external);
		b.setVisibility(View.GONE);	
		AudioExternal.stop();
		b = findViewById(R.id.btn_start_audio_external);
		b.setVisibility(View.VISIBLE);
	}
	public void PlayVideo(View view)
	{
		View b = findViewById(R.id.btn_start_video);
		b.setVisibility(View.GONE);	
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		videoPlayer = (VideoView) findViewById(R.id.videoView);
		//if you want the controls to appear
		videoPlayer.setMediaController(new MediaController(this));
		Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sample_video); //do not add any extension
		//if your file is named sherif.mp4 and placed in /raw
		//use R.raw.sherif
		videoPlayer.setVideoURI(video);
		videoPlayer.start();	
		b = findViewById(R.id.btn_stop_video);
		b.setVisibility(View.VISIBLE);	
	}
	public void StopVideo(View view)
	{
		View b = findViewById(R.id.btn_stop_video);
		b.setVisibility(View.GONE);	
		videoPlayer.stopPlayback();
		b = findViewById(R.id.btn_start_video);
		b.setVisibility(View.VISIBLE);
	}
	@Override
    protected void onPause() {
        super.onPause();
        if(ai==true)
        {
        	AudioInternal.stop();
        }
        if(ae==true)
        {
        	AudioExternal.stop();
        }
        if(vid==true)
        {
        	videoPlayer.stopPlayback();
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
