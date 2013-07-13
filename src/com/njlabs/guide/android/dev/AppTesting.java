package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.SubMenu;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.KeyEvent;
import android.widget.TextView;

public class AppTesting extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_testing);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setSubtitle("Check whether it works...");
        TextView item = (TextView) this.findViewById(R.id.textViewAppTesting);
        item.setText(Html.fromHtml("<p>When it comes to testing your app, there are two options.<br>1) Testing it on a live Android Device<br>2) Testing it on an Android Emulator.<br><br>Though I'd recommend testing on both a device and an emulator. Because an emulator provides various different situations for testing such as different screen-sizes, varying internet speeds, different amount of RAM memory, different API Levels, etc.</p><h2>Run on a Real Device</h2><p>If you have a real Android-powered device, here's how you can install and run your app:</p>1) Plug in your device to your development machine with a USB cable. If you're developing on Windows, you might need to install the appropriate USB driver for your device. <br>2) Enable <strong>USB debugging</strong> on your device. <blockquote><p> On most devices running Android 3.2 or older, you can find the option under <strong>Settings >Applications >Development</strong>. <br><br>On Android 4.0 and newer, it's in <strong>Settings >Developer options</strong>. <br><br><strong>Note:</strong> On Android 4.2 and newer, <strong>Developer options</strong> is hidden by default. To make it available, go to<strong>Settings >About phone</strong> and tap <strong>Build number</strong> seven times. Return to the previous screen to find<strong>Developer options</strong>.</p></blockquote><p><strong>To run the app from Eclipse:</strong><br>1) Open one of your project's files and click <strong>Run</strong> <img src='eclipse-run.png'> from the toolbar.<br>2) In the <strong>Run as</strong> window that appears, select <strong>Android Application</strong> and click <strong>OK</strong>.</p><p>Eclipse installs the app on your connected device and starts it.</p><h2 id='Emulator'>Run on the Emulator</h2><p>Whether you're using Eclipse or the command line, to run your app on the emulator you need to first create an Android Virtual Device (AVD). An AVD is a device configuration for the Android emulator that allows you to model different devices.</p><p><strong>To create an AVD:</strong></p> Launch the Android Virtual Device Manager: <br><br>1) In Eclipse, click Android Virtual Device Manager <img src='avd_manager.png'>from the toolbar. <br>2) In the <em>Android Virtual Device Manager</em> panel, click <strong>New</strong>. <br>3) Fill in the details for the AVD. Give it a name, a platform target, an SD card size, and a skin (HVGA is default). <br>4) Click <strong>Create AVD</strong>. <br>5) Select the new AVD from the <em>Android Virtual Device Manager</em> and click <strong>Start</strong>. <br>6) After the emulator boots up, unlock the emulator screen.<p><strong>To run the app from Eclipse:</strong><br>1) Open one of your project's files and click <strong>Run</strong> <img src='eclipse-run.png'> from the toolbar. <br>2) In the <strong>Run as</strong> window that appears, select <strong>Android Application</strong> and click <strong>OK</strong>.</p><p>Eclipse installs the app on your AVD and starts it.</p>", new ImageGetter(), null));
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
    private class ImageGetter implements Html.ImageGetter {
    	 
    	public Drawable getDrawable(String source) {
    	        int id;
    	        if (source.equals("eclipse-run.png")) {
    	               id = R.drawable.eclipse_run;
    	        }
    	        else if (source.equals("avd_manager.png")) {
 	               id = R.drawable.avd_manager;
    	        }
    	        else {
    	            return null;
    	        }
    	 
    	       Drawable d = getResources().getDrawable(id);
    	       d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
    	       return d;
    	     }
    	};

}
