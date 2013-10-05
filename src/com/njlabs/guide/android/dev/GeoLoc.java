package com.njlabs.guide.android.dev;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class GeoLoc extends SherlockActivity implements LocationListener{

    private TextView latitudeValue;
    private TextView longitudeValue;
    private TextView accuracyValue; 
    
    @SuppressWarnings("unused")
	private static final int PERIOD_SECONDS=1;
    private LocationManager mgr=null;
    private Location lastLocation=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.geo_loc);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        
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
		webView.loadUrl("file:///android_asset/code_snippets/geo_loc_manifest.html");	
		
        webView = (WebView) findViewById(R.id.webViewJava);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/code_snippets/geo_loc_java.html");
		
        latitudeValue = (TextView) findViewById(R.id.latitudeValue);
        longitudeValue = (TextView) findViewById(R.id.longitudeValue);
        accuracyValue = (TextView) findViewById(R.id.accuracyValue);
        mgr=(LocationManager)getSystemService(LOCATION_SERVICE);
        mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        mgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,this);
        Toast.makeText(getBaseContext(), "Waiting for location update ... ", Toast.LENGTH_LONG).show();
	}
	@Override
	  public void onDestroy() {
	    mgr.removeUpdates(this);
	    super.onDestroy();
	  }
	@Override
	  public void onPause() {

	    super.onPause();
	  }
	  @Override
	  public void onLocationChanged(Location loc) {
	    Location bestLocation=getBestLocation(loc);

	    if (bestLocation != lastLocation) {
	      lastLocation=bestLocation;
	      latitudeValue.setText(String.valueOf(bestLocation.getLatitude()));
	      longitudeValue.setText(String.valueOf(bestLocation.getLongitude()));
	      accuracyValue.setText(String.valueOf(bestLocation.getAccuracy()));
	    }
	    else
	    {
	    }
	  }

	  @Override
	  public void onProviderDisabled(String provider) {
	    // TODO Auto-generated method stub

	  }

	  @Override
	  public void onProviderEnabled(String provider) {
	    // TODO Auto-generated method stub

	  }

	  @Override
	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	  }

	  private Location getBestLocation(Location location) {
	    // start off by handling cases where we only have one
	    if (lastLocation == null) {
	      return(location);
	    }

	    Location older=
	        (lastLocation.getTime() < location.getTime() ? lastLocation
	            : location);
	    Location newer=(lastLocation == older ? location : lastLocation);

	    // older and less accurate fixes suck

	    if (older.getAccuracy() <= newer.getAccuracy()) {
	      return(newer);
	    }

	    // if older is within error radius of newer, assume
	    // not moving and go with older (since has better
	    // accuracy, else would have been caught by previous
	    // condition)

	    // ideally, this would really be "if the odds of
	    // the older being within the error radius of the
	    // newer are higher than 50%", taking into account
	    // the older one's accuracy as well -- the
	    // implementation of this is left as an exercise for the
	    // reader

	    if (newer.distanceTo(older) < newer.getAccuracy()) {
	      return(older);
	    }

	    // if all else fails, choose the newer one -- the device
	    // is probably moving, and so we are better off with the
	    // newer fix, even if less accurate

	    return(newer);
	    
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
