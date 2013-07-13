package com.njlabs.guide.android.dev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
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
        
        webView.getSettings().setJavaScriptEnabled(true);
        if(title.equals("Shared Preferences"))
        {
        	textView.setText("Shared Preferences can Store private primitive data in key-value pairs. (Supports Boolean, STring, Float, Long and Int");
        	webView.loadUrl("file:///android_asset/code_snippets/"+codesnippet+"_java.html");
        }
        else if(title.equals("Internal Memory"))
        {
        	textView.setText("You can Store private data on the Internal memory. (any type of file)");
        	webView.loadUrl("file:///android_asset/code_snippets/"+codesnippet+"_java.html");
        }
        else if(title.equals("External Memory"))
        {
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
        	SharedPreferences preferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        	SharedPreferences.Editor editor = preferences.edit();
        	editor.putString("Demo Text", EnteredText);        
        	editor.commit();
        }
        else if(title.equals("Internal Memory"))
        {

        }
        else if(title.equals("External Memory"))
        {

        }
        editText.setText(null, TextView.BufferType.EDITABLE);
        status.setText("Stored !");
    }
    public void Read(View view)
    {
    	String ReadText=null;
        if(title.equals("Shared Preferences"))
        {
        	SharedPreferences preferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        	ReadText = preferences.getString("Demo Text", null);
        }
        else if(title.equals("Internal Memory"))
        {

        }
        else if(title.equals("External Memory"))
        {

        }
        editText.setText(ReadText, TextView.BufferType.EDITABLE);
        status.setText("Retrieved ! ");
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
