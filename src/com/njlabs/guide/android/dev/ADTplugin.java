package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.SubMenu;

import android.os.Bundle;
import android.content.Intent;
import android.text.Html;
import android.view.KeyEvent;
import android.widget.TextView;

public class ADTplugin extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adt_plugin);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setSubtitle("Android Development Tools");
        TextView item = (TextView) this.findViewById(R.id.textViewADTplugin);
        item.setText(Html.fromHtml("<h2>Installing the Eclipse Plugin</h2><p>Android offers a custom plugin for the Eclipse IDE, called Android Development Tools (ADT). This plugin provides a powerful, integrated environment in which to develop Android apps. It extends the capabilities of Eclipse to let you quickly set up new Android projects, build an app UI, debug your app, and export signed (or unsigned) app packages (APKs) for distribution. </p><blockquote>Note: If you prefer to work in a different IDE, you do not need to install Eclipse or ADT. Instead, you can directly use the SDK tools to build and debug your application.</blockquote><h2>Download the ADT Plugin </h2><p>•Start Eclipse, then select Help > Install New Software.<br>•Click Add, in the top-right corner.<br>•In the Add Repository dialog that appears, enter 'ADT Plugin' for the Name and the following URL for the Location:<blockquote><code><font color='#006699'>https://dl-ssl.google.com/android/eclipse/</font></code></blockquote>Click OK. <br>•If you have trouble acquiring the plugin, try using 'http' in the Location URL, instead of 'https' (https is preferred for security reasons). <br>•In the Available Software dialog, select the checkbox next to Developer Tools and click Next. <br>•In the next window, you'll see a list of the tools to be downloaded. Click Next. <br>•Read and accept the license agreements, then click Finish. <br>•If you get a security warning saying that the authenticity or validity of the software can't be established, click OK. <br>•When the installation completes, restart Eclipse. </p><h2>Configure the ADT Plugin</h2><p>Once Eclipse restarts, you must specify the location of your Android SDK directory: <br>•In the 'Welcome to Android Development' window that appears, select Use existing SDKs.<br>•Browse and select the location of the Android SDK directory you recently downloaded and unpacked.<br>•Click Next.<br><br>Your Eclipse IDE is now set up to develop Android apps, but you need to add the latest SDK platform tools and an Android platform to your environment. </p>"));

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
