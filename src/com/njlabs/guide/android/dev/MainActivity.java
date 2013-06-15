package com.njlabs.guide.android.dev;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.njlabs.guide.android.dev.adapters.ExpandableListAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
 
public class MainActivity extends SherlockActivity {
 
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> mainCategory;
    ExpandableListView expListView;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Android Development");
        actionBar.setSubtitle("A small Guide !");
        
        createGroupList();
 
        createCollection();
 
        expListView = (ExpandableListView) findViewById(R.id.category_list);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, mainCategory);
        expListView.setAdapter(expListAdapter);
 
        //setGroupIndicatorToRight();
 
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                if (selected.equals("Intoduction"))
                {
                	Intent it  = new Intent(getBaseContext(), Introduction.class);
               		startActivity(it);
                }
                else if (selected.equals("Key Terms"))
                {
                	Intent it  = new Intent(getBaseContext(), KeyTerms.class);
               		startActivity(it);
                }
                else if (selected.equals("Prerequisites"))
                {
                	Intent it  = new Intent(getBaseContext(), Prerequisites.class);
               		startActivity(it);
                }
                else if (selected.equals("Publishing to Google Play"))
                {
                	Intent it  = new Intent(getBaseContext(), GooglePlayPublishing.class);
               		startActivity(it);
                }
                else if (selected.equals("The Android SDK"))
                {
                	Intent it  = new Intent(getBaseContext(), AndroidSDK.class);
               		startActivity(it);
                }
                else if (selected.equals("Choosing an IDE"))
                {
                	Intent it  = new Intent(getBaseContext(), ChoosingIDE.class);
               		startActivity(it);
                }
                else if (selected.equals("ADT Plugin for Eclipse"))
                {
                	Intent it  = new Intent(getBaseContext(), ADTplugin.class);
               		startActivity(it);
                }
                else if (selected.equals("Creating your first project"))
                {
                	Intent it  = new Intent(getBaseContext(), FirstApp.class);
               		startActivity(it);
                }
                else if (selected.equals("Folder Structure"))
                {
                	Intent it  = new Intent(getBaseContext(), FolderStructure.class);
               		startActivity(it);
                }
                else if (selected.equals("Testing the app"))
                {
                	Intent it  = new Intent(getBaseContext(), AppTesting.class);
               		startActivity(it);
                }
                else if (selected.equals("Views"))
                {
                	final CharSequence[] items = {"List View","Spinner"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Select one to know more !");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                               	if(items[item]=="Layouts")
                               	{
                               		Intent it  = new Intent(getBaseContext(), MainActivity.class);
                               		startActivity(it);
                               	}
                               	else if(items[item]=="Spinner")
                               	{
                               		Intent it  = new Intent(getBaseContext(), SpinnerDropdown.class);
                               		startActivity(it);
                               	}
                               	else if(items[item]=="List View")
                               	{
                               		Intent it  = new Intent(getBaseContext(), ListView.class);
                               		startActivity(it);
                               	}    
                                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                          }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if (selected.equals("Dialogs"))
                {
                	final CharSequence[] items = {"Simple Dialog", "Response Dialog","List Dialog","Custom Dialog"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Select one to know more !");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                               	if(items[item]=="Simple Dialog")
                               	{
                               		Log.d("ENtered","Simple");
                               		Intent it  = new Intent(getBaseContext(), Dialogs.class);
                               		it.putExtra("title",items[item]);
                               		it.putExtra("codesnippet","simple_dialog");
                               		startActivity(it);
                               	}
                               	else if(items[item]=="Response Dialog")
                               	{
                               		Intent it  = new Intent(getBaseContext(), Dialogs.class);
                               		it.putExtra("title",items[item]);
                               		it.putExtra("codesnippet","response_dialog");
                               		startActivity(it);
                               	}
                               	else if(items[item]=="List Dialog")
                               	{
                               		Intent it  = new Intent(getBaseContext(), Dialogs.class);
                               		it.putExtra("title",items[item]);
                               		it.putExtra("codesnippet","list_dialog");
                               		startActivity(it);
                               	}   
                               	else if(items[item]=="Custom Dialog")
                               	{
                               		Intent it  = new Intent(getBaseContext(), Dialogs.class);
                               		it.putExtra("title",items[item]);
                               		it.putExtra("codesnippet","custom_dialog");
                               		startActivity(it);
                               	}
                          }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                	
                }
                else if (selected.equals("Notifications"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Image Resources"))
                {
                	final CharSequence[] items = {"Project Images", "Bitmap Objects","Drawing on Canvas"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Select one to know more !");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                               	if(items[item]=="Project Images")
                               	{
                               		Intent it  = new Intent(getBaseContext(), MainActivity.class);
                               		startActivity(it);
                               	}
                               	else if(items[item]=="Bitmap Objects")
                               	{
                               		Intent it  = new Intent(getBaseContext(), MainActivity.class);
                               		startActivity(it);
                               	}
                               	else if(items[item]=="Drawing on Canvas")
                               	{
                               		Intent it  = new Intent(getBaseContext(), MainActivity.class);
                               		startActivity(it);
                               	}   
                                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                          }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if (selected.equals("Regular Thread"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("A Thread with a handler"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("An AsyncTask"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Timer"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("What is an Intent?"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Opening a new screen"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Intents Quick reference"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Network"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Audio and Video"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Storage"))
                {
                	final CharSequence[] items = {"Shared Preferences", "Internal Memory","External Memory"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Select one to know more !");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                               	if(items[item]=="Shared Preferences")
                               	{
                               		Intent it  = new Intent(getBaseContext(), MainActivity.class);
                               		startActivity(it);
                               	}
                               	else if(items[item]=="Internal Memory")
                               	{
                               		Intent it  = new Intent(getBaseContext(), MainActivity.class);
                               		startActivity(it);
                               	}
                               	else if(items[item]=="External Memory")
                               	{
                               		Intent it  = new Intent(getBaseContext(), MainActivity.class);
                               		startActivity(it);
                               	}   
                                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                          }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                	
                }
                else if (selected.equals("Camera"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Contacts"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Phone Calls"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("SMS and MMS"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Geo Location"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Screen Parameters"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Vibration"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("What is an NDK ?"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else if (selected.equals("Using it"))
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
                else
                {
                	Intent it  = new Intent(getBaseContext(), MainActivity.class);
               		startActivity(it);
                }
           		overridePendingTransition(R.anim.fadein,R.anim.fadeout); 
           		
                return true;
            }
        });
    }
 
    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Basics");
        groupList.add("Initial Setup");
        groupList.add("User Interface");
        groupList.add("Threading");
        groupList.add("Intents and types");
        groupList.add("Android System APIs");
        groupList.add("Native Development Kit");
    }
 
    private void createCollection() {
        // preparing subCategories collection(child)
        String[] basics = {"Intoduction","Key Terms","Prerequisites","Publishing to Google Play"};
        String[] initialSetup = { "The Android SDK","Choosing an IDE","ADT Plugin for Eclipse","Creating your first project","Folder Structure","Testing the app" };
        String[] userInterface = { "Views","Dialogs","Notifications","Image Resources" };
        String[] threading = { "Regular Thread","A Thread with a handler","An AsyncTask","Timer" };
        String[] intentsTypes = { "What is an Intent?","Opening a new screen","Intents Quick reference" };
        String[] androidSystemApis = { "Network","Audio and Video","Storage","Camera","Contacts","Phone Calls","SMS and MMS","Geo Location","Screen Parameters","Vibration" };
        String[] ndk = { "What is an NDK ?","Using it" };
 
        mainCategory = new LinkedHashMap<String, List<String>>();
 
        for (String sub_category : groupList) {
            if (sub_category.equals("Basics")) {
                loadChild(basics);
            } else if (sub_category.equals("Initial Setup"))
                loadChild(initialSetup);
            else if (sub_category.equals("User Interface"))
                loadChild(userInterface);
            else if (sub_category.equals("Threading"))
                loadChild(threading);
            else if (sub_category.equals("Intents and types"))
                loadChild(intentsTypes);
            else if (sub_category.equals("Android System APIs"))
                loadChild(androidSystemApis);
            else
            	loadChild(ndk);
 
            mainCategory.put(sub_category, childList);
        }
    }
 
    private void loadChild(String[] subCategoriesList) {
        childList = new ArrayList<String>();
        for (String subCategoryText : subCategoriesList)
            childList.add(subCategoryText);
    }
 
    @SuppressWarnings("unused")
	private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
 
        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }
 
    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
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
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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