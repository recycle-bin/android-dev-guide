package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

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
