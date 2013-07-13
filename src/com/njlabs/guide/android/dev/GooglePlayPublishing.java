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

public class GooglePlayPublishing extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.google_play_publishing);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setSubtitle("Giving the world your app !");
		TextView item = (TextView) this.findViewById(R.id.textViewGooglePlayPublishing);
        item.setText(Html.fromHtml("<h3>Register for a publisher account</h3><p>The first step is to visit the Google Play Developer Console and register for a publisher account.</p><p>Here's what you will do during registration:</p><blockquote><strong>1.</strong> Visit the Google Play Developer Console at <font color='#006699'> https://play.google.com/apps/publish/ </font>.<br><strong>2.</strong> Enter basic information about your <strong>developer identity</strong> — developer name, email address, and so on. You can modify this information later.<br><strong>3. </strong>Read and accept the <strong>Developer Distribution Agreement</strong> that applies to your country or region. Note that apps and store listings that you publish on Google Play must comply with the Developer Program Policies and US export law,<br><strong>4.</strong> Pay a <strong>$25 USD registration fee</strong> using Google Wallet. If you don't have a Google Wallet account, you can quickly set one up during the process.</li></blockquote><p>When your registration is verified, you’ll be notified at the email address you specified during registration.</p><h3>Set up a Google Wallet Merchant account</h3><p>If you want to sell products on Google Play — priced apps, in-app products, or subscriptions — you will also need to set up a Google Wallet Merchant Account.</p><p>To set up a Merchant account from the Developer Console:</p><blockquote><strong>1.</strong><strong>Sign in</strong> to your Google Play Developer Console at <font color='#006699'>https://play.google.com/apps/publish/</font><br><strong>2.</strong> Open <strong>Financial reports</strong> on the side navigation.<br><strong>3.</strong> Click <strong>Setup a Merchant Account now</strong>.</blockquote><p>This takes you to the Google Wallet site to sign up as a Merchant;you'll need information about your business available to complete this step.</p><h3>Explore the Developer Console</h3><p>When your registration is verified, you can sign in to your Developer Console, which will be the home for your app publishing operations and tools on Google Play.</p>"));
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
}
