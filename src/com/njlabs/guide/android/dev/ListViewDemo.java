package com.njlabs.guide.android.dev;

import android.view.MenuItem;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class ListViewDemo extends ListActivity  {

	/// THE LIST ITEMS TO BE SHOWN
	static final String[] LIST_ITEMS = new String[] { "Item 1", "Item 2", "Item 3",
		"Item 4", "Item 5", "Item 6", "Item 7", "Item 8","Item 9", "Item 10" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB)
		{
			ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
		}
        /// WE DON'T NEED TO USE setContentView(R.layout.list_view_demo);
		/// AS THE LAYOUT IS PROGRAMATTICALLY SET BY THE LIST ADAPTER
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_view_demo,LIST_ITEMS));
		 
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		
		/// CREATE AN ONCLICK LISTENER TO KNOW WHEN EACH ITEM IS CLICKED
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
