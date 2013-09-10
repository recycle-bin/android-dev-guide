package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.SubMenu;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

public class ImageResources extends SherlockActivity {
	
	String title=null;
	String codesnippet=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_resources);
		
		Bundle extras = getIntent().getExtras();
		title = extras.getString("title");
		codesnippet = extras.getString("codesnippet");
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(title);
        
        TextView textView = (TextView) findViewById(R.id.textViewImageResources);
        if(title.equals("Project Images"))
        {
        	textView.setText("You can display some images in your application to make it look beautiful (or) maybe images are an integral part of your application. So, let's see how we can add images.\n Place the images in the drawable folders (depending on screen size)");
        }
        else if(title.equals("Bitmap Objects on Canvas"))
        {
        	textView.setText("Android also allows you actually 'draw' any bitmap object on a canvas.");
        }
        WebView webView = (WebView) findViewById(R.id.webViewXML);
        if(title.equals("Project Images"))
        {
        	Button button = (Button) findViewById(R.id.demo_btn_ImageResources);
        	button.setVisibility(View.GONE);
        	
        	ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        	imageView.setImageResource(R.drawable.sample_img);
        	
        	webView.getSettings().setJavaScriptEnabled(true);		
        	webView.loadUrl("file:///android_asset/code_snippets/"+codesnippet+"_xml.html");
        	
        }
        else
        {
        	webView.setVisibility(View.GONE);
        	TextView textView1 = (TextView) findViewById(R.id.textViewImageResources7);
        	textView1.setVisibility(View.GONE);
        	textView1 = (TextView) findViewById(R.id.textViewImageResourcesdum);
        	textView1.setVisibility(View.GONE);
        	
        	TextView textView2 = (TextView) findViewById(R.id.textView1);
        	textView2.setVisibility(View.GONE);
        	textView2 = (TextView) findViewById(R.id.textView2);
        	textView2.setVisibility(View.GONE);
        	textView2 = (TextView) findViewById(R.id.textView5);
        	textView2.setVisibility(View.GONE);
        	
        	ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        	imageView.setVisibility(View.GONE);
        	imageView = (ImageView) findViewById(R.id.imageView2);
        	imageView.setVisibility(View.GONE);
        }
		
        WebView webView1 = (WebView) findViewById(R.id.webViewJava);
		webView1.getSettings().setJavaScriptEnabled(true);		
		webView1.loadUrl("file:///android_asset/code_snippets/"+codesnippet+"_java.html");
	}
	public void DemoImageResources(View view)
	{
		Intent intent1 = new Intent(this, BitmapCanvas.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
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
