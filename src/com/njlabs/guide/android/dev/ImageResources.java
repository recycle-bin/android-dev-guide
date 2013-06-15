package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Menu;

import android.os.Bundle;
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
        	textView.setText("Let's see how we can easily make a simple Alert dialog with one button to dismiss it.");
        }
        else if(title.equals("Bitmap Objects on Canvas"))
        {
        	textView.setText("Here we make an Alert DIalog with two buttons to get the respose of the user");
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                //Intent intent = new Intent(this, MainActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
            	finish();
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                return true;
            case R.id.AboutAppOption:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                return true;
            case R.id.BugReportOption:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                return true;
            case R.id.ContactOption:
                Intent intent3 = new Intent(this, MainActivity.class);
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


}
