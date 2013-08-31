package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import android.os.Bundle;
import android.content.Intent;
import android.text.Html;
import android.view.KeyEvent;
import android.widget.TextView;

public class Prerequisites extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prerequisites);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setSubtitle("First things first !");
		TextView item = (TextView) this.findViewById(R.id.textViewPrerequisites);
        item.setText(Html.fromHtml("<p><strong>Java experience:</strong> you don't need to know loads of Java to develop Android apps but it is important that you at least know the syntax and roughly how to do things. Experience in a similar language like C# or C++ would probably get you by, just so long as you know the difference between a package, class and that kind of thing</p><p><strong>Experience in another object-orientated language:</strong> Some experience in another language would also be useful because despite the fact that most Android development is in Java it is useful to understand how other languages do things because a lot of the Android specific Java has had influence from C++</p><p><strong>An understanding of how apps work:</strong> This would probably come with programming experience but you really aren’t going to get anywhere if you don’t get how the most basic apps. Reading a few tutorials can help fix this</p><p><strong>Experience creating user interfaces via code and and a visual designer:</strong>Android uses both XML layouts and pure Java code to create User Interfaces so provided that you have done something in both before you’ll probably be fine. I should imagine that it would probably be OK if you’ve done JavaScript DOM and HTML</p><p><strong>Basic knowledge of XML and SQLite:</strong> You won’t need to know how to do these perfectly but so long as you can create XML documents and edit them. I wouldn’t say SQLite knowledge is vital but it would be good to have some database knowledge</p><p><strong>Good resources for testing:</strong> The emulator is good but ot is always useful to either have at least one Android device for testing. It is also recommended to set up a few different devices in the emulator with different screen sizes and versions of Android because that will give you a chance to test in loads of different environments</p><br><br><p>SOURCE: Thomas' Interesting Blog - thomasinterestingblog.wordpress.com</p>"));
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
