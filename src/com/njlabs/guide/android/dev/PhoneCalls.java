package com.njlabs.guide.android.dev;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class PhoneCalls extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_calls);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        WebView webView = (WebView) findViewById(R.id.webViewManifest);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/code_snippets/phone_manifest.html");		
        webView = (WebView) findViewById(R.id.webViewJava);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/code_snippets/phone_calls_java.html");			
	
	}
	public void CallDirect(View view)
	{
		EditText EnteredNumberField = (EditText) findViewById(R.id.EnteredNumber);
		String EnteredNumber=EnteredNumberField.getText().toString();
		if(EnteredNumber==null||EnteredNumber=="")
		{
			Toast.makeText(getApplicationContext(), "Please enter a number...", Toast.LENGTH_LONG).show();
		}
		else
		{
			Intent CallIntent = new Intent(Intent.ACTION_CALL);
			CallIntent.setData(Uri.parse("tel:"+EnteredNumber));
			startActivity(CallIntent);
		}
		
	}
	public void OpenDialer(View view)
	{
		EditText EnteredNumberField = (EditText) findViewById(R.id.EnteredNumber);
		String EnteredNumber=EnteredNumberField.getText().toString();
		if(EnteredNumber==null||EnteredNumber=="")
		{
			Toast.makeText(getApplicationContext(), "Please enter a number...", Toast.LENGTH_LONG).show();
		}
		else
		{
			Intent DialerIntent = new Intent(Intent.ACTION_DIAL);
			DialerIntent.setData(Uri.parse("tel:"+EnteredNumber));
			startActivity(DialerIntent);
		}
	}
	public void Clear(View view)
	{
		EditText EnteredNumberField = (EditText) findViewById(R.id.EnteredNumber);
		EnteredNumberField.setText(null);
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
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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

