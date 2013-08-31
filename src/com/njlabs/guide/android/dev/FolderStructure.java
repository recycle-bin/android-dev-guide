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
import android.view.View;
import android.widget.TextView;

public class FolderStructure extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.folder_structure);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setSubtitle("The arrangement of the files ...");
        TextView item = (TextView) this.findViewById(R.id.textViewFolderStructure);
        item.setText(Html.fromHtml("<p><strong>The '<em>AndroidManifest.xml</em>' file :</strong><br>Every application must have an AndroidManifest.xml file (with precisely that name) in its root directory. The manifest presents essential information about the application to the Android system, information the system must have before it can run any of the application's code.</p><p><strong>The '<em>src</em>' folder :</strong><br>This folder contains the .java classes for your Android Application and the packages (eg. Activities, Handlers etc.).</p><p><strong>The '<em>res</em>' folder :</strong><br>This folder is also called as the resources folder. The key sub-folders present are:</p><p>    <strong>The '<em>drawable</em>' folders:</strong><br>    These folders contain the various images resources used by your application segregated according to their resolutions for various screen sizes in different folders nameley drawable-hdpi,drawable-mdpi,drawable-xhdpi,drawable-xxhdpi,drawable-nodpi.</p><p>        <strong>-hdpi:</strong> Resources for high-density (hdpi) screens (~240dpi).<br>        <strong>-mdpi:</strong> Resources for medium-density (mdpi) screens (~160dpi). <br>        <strong>-xhdpi:</strong> Resources for extra high-density (xhdpi) screens (~320dpi).<br>        <strong>-xxhdpi:</strong> Resources for extra high-density (xhdpi) screens (~480dpi).<br>        <strong>-nodpi:</strong> Resources for all densities. These are density-independent resources. <blockquote>Handling of the correct images for each density is handled by the Android system.</blockquote><p>    <strong>The '<em>layout</em>' folder:</strong><br>    This folder contains the XML Layout files for each Activity which has the display layout.</p><p>     <strong>The '<em>menu</em>' folder:</strong><br>    This folder contains the XML Layout files for the option menus in your application.</p><p>     <strong>The 'values' folder:</strong><br>    This folder contains Global Strings, Constants, Styles, Colours, Dimensions etc.</p><p><strong>The '<em>assets</em>' folder:</strong>This folder is not created by default. It is used to hold other misc resources such as html files, Audio, Video files, etc. </p>"));
	}
	public void zoomin(View view)
	{
		String pageNumber = (String) view.getTag(); 
   		Intent it  = new Intent(getBaseContext(), ImageViewer.class);
   		it.putExtra("image",pageNumber);
   		startActivity(it);
   		overridePendingTransition(R.anim.appear,R.anim.appear);
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
