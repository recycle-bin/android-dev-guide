package com.njlabs.guide.android.dev;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class Notifications extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notifications);
		
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
		
        WebView webView1 = (WebView) findViewById(R.id.webViewJava);
		webView1.getSettings().setJavaScriptEnabled(true);		
		webView1.loadUrl("file:///android_asset/code_snippets/notifications_java.html");
	}
	public void DemoNotifications(View view)
	{
		// Prepare intent which is triggered if the
		// notification is selected		
		Intent intent = new Intent(); // This is a blank intent. No ACtivity is opened
		
		//Intent intent = new Intent(this, ActivityToOpen.class); // USE THIS TO OPEN ACTIVITY ON CLICK
		
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		int currentapiVersion = android.os.Build.VERSION.SDK_INT; // GET CURRENT API LEVEL
		
		if (currentapiVersion < android.os.Build.VERSION_CODES.JELLY_BEAN)
		{
			// USING COMPATIBILY CLASS NotificationCompat FOR API < 16 (JELLY_BEAN) From android-support-v4.jar
			NotificationCompat.Builder noti = new NotificationCompat.Builder(this)
	        .setContentTitle("This is the Notification Title")
	        .setContentText("Here is the the message that you can display to the user. ")
	        .setSmallIcon(R.drawable.ic_launcher)
	        .setContentIntent(pIntent)
	        .addAction(R.drawable.accept, "Button1", pIntent)
	        .addAction(R.drawable.cancel, "Button2", pIntent);		

			NotificationManager notificationManager = 
					(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			
			// For Hiding the notification after its selected
			noti.setAutoCancel(true);
			notificationManager.notify(0, noti.build());			
						
		}
		else if(currentapiVersion >= android.os.Build.VERSION_CODES.JELLY_BEAN)
		{
			// USING DEFAULT CLASS FOR API >= 16 (JELLY_BEAN)
			Notification noti = new Notification.Builder(this)
	        .setContentTitle("This is the Notification Title")
	        .setContentText("Here is the the message that you can display to the user. ")
	        .setSmallIcon(R.drawable.ic_launcher)
	        .setContentIntent(pIntent)
	        .addAction(R.drawable.accept, "Button1", pIntent)
	        .addAction(R.drawable.cancel, "Button2", pIntent).build();	    
	  
			NotificationManager notificationManager = 
					(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			// For Hiding the notification after its selected
			noti.flags |= Notification.FLAG_AUTO_CANCEL;
			notificationManager.notify(0, noti); 			
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
