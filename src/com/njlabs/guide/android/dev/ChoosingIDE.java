package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Menu;

import android.os.Bundle;
import android.content.Intent;
import android.text.Html;
import android.widget.TextView;

public class ChoosingIDE extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choosing_ide);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setSubtitle("Integrated Development Environment");
        TextView item = (TextView) this.findViewById(R.id.textViewChoosingIDE);
        item.setText(Html.fromHtml("<p>Choosing the right IDE can make you or break you as a coder. Most developers would be lost without the comfort of their preferred IDE, which takes care of classpath, make files, command line arguments, etc. But the truth is ,Each developer chooses his own IDE. </p><h2>Eclipse vs IntelliJ</h2><p><strong>Plugins:</strong> Eclipse marketplace offers 1,276 plugins, and the Intellij Plugin Repository offers 727 plugins. This difference is not to be taken lightly, since plugins for new technologies will usually be developed mainly for Eclipse (e.g. Android, Drools, Activiti, etc). Moreover, Eclipse is easier to extend. When working on a specific technology most chances are that if a plugin exists, it will be an Eclipse plugin.</p><p><strong>Multiple projects:</strong> This is an Eclipse winner for sure. It has the ability to open multiple projects in the same window, giving the coder control over dependencies and relations. Intellij has an option to open one project with multiple modules, but we found it to be cumbersome, and in times a little buggy. If you are going to use a lot of projects together and hate to switch windows, Eclipse is your choice.</p><p><strong>Multiple languages:</strong> We have stated that we will only examine the Intellij Community Edition that supports Java, Groovy and Scala. However, if you plan to create a Python server, combined with Ajax &Html, joint with a java web server, or any other exotic language combinations, than Eclipse is your choice.</p><p><strong>Code completion &inspection:</strong> While Eclipse has the ability to add plugins such as checkstyle, this one definitely goes for Intellij. The default code completion and assistance in Intellij is faster and better. If you are a rookie developer, Intellij can improve your code.</p><p><strong>Usability: </strong>Intellij user experience is much easier to grasp. The learning curve in Intellij is by far faster. It seems using Intellij makes development easier and more natural. Dropdowns, code completion, quick view, project wizards, etc, are all possible both in Eclipse and Intellij, but the experience in Intellij is much more satisfying.</p><p><strong>Performance:</strong> The more plugins are installed on the IDE, the more heavy it is for your computer. However, saying that, Eclipse handles very large projects faster. Moreover, both of the IDE’s seems to be RAM junkies. Projects usually open faster in Eclipse, as Intellij indexes the entire project on startup, but while working on an existing project, Intellij works smoother. For example we have a huge SOAP project, which is impossible to work on with Intellij, so some of us even learn Eclipse just for that.</p><p><strong>Repository integration:</strong> Both of the IDE’s have SVN\\GIT\\etc plugins. No doubt Intellij’s plugin is more reliable, has better GUI and easier to use.</p><p><strong>GUI builder:</strong> We found that the built in Intellij GUI builder is more comfortable, and as mentioned above, usability wise its easier to learn, and more enjoyable to develop.</p><p>But for beginners I'd recommend going for an IDE that has a lot of Community support (in forums such as StackOverflow etc) , incase you run into any troubles or have doubts</p><p> This guide itself is based on the <strong>Eclipse IDE</strong>.</p>"));

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
