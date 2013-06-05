package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.content.Intent;
import android.text.Html;
import android.widget.TextView;

public class Introduction extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.introduction);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
		TextView item = (TextView) this.findViewById(R.id.textViewIntoduction);
        item.setText(Html.fromHtml("<h2 align='justify'>Android ?</h2><p align='justify'><font size='4'><strong>Android</strong> is a <em>Linux-based</em> operating system designed primarily for touchscreen mobile devices such as smartphones and tablet computers. Initially developed by <em>Android, Inc.</em>, which <strong>Google</strong> backed financially and later bought in 2005, Android was unveiled in 2007 along with the founding of the Open Handset Alliance: a consortium of hardware, software, and telecommunication companies devoted to advancing open standards for mobile devices. The first <em>Android-powered</em> phone was sold in <strong>October 2008</strong>.</font></p><font size='4'>• Linux Based<br>• Core Libraries written <strong>C</strong>, <strong>C++</strong> and <strong>Java</strong><br>• All the Android Applications are basically written in <strong>Java</strong>, an <em>Object Oriented Programming Language</em></font><h2 align='justify'>Object-Oriented Programming ? </h2><p align='justify'><font size='4'><strong>Object-oriented programming (<em>OOP</em>)</strong> is a programming paradigm that represents concepts as '<strong>objects</strong>' that have data fields (attributes that describe the <em>object</em>) and associated procedures known as methods. <em>Objects</em>, which are usually instances of <em>classes</em>, are used to interact with one another to design applications and computer programs.<em>C++ </em>and <em>Java</em> are examples of object-oriented programming languages. </font></p><h2 align='justify'>Java ? </h2><p align='justify'><font size='4'><strong>Java</strong> is a <em>general-purpose, concurrent, class-based, object-oriented computer programming language</em> that is specifically designed to have as few implementation dependencies as possible. It is intended to let application developers '<em>write once, run anywhere</em>'(<strong>WORA</strong>), meaning that code that runs on one platform does not need to be recompiled to run on another. Java applications are typically compiled to bytecode (class file) that can run on any <em>Java virtual machine</em> (<strong>JVM</strong>) regardless of computer architecture. Java is, as of 2012, one of the most popular programming languages in use, particularly for client-server web applications, with a reported 10 million users. Java was originally developed by <strong>James Gosling</strong> at <strong>Sun Microsystems</strong> (which has since merged into Oracle Corporation) and released in 1995 as a core component of Sun Microsystems' Java platform. The language derives much of its syntax from <strong>C</strong> and <strong>C++</strong>, but it has fewer low-level facilities than either of them.</font></p>"));
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
