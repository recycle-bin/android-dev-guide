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

public class KeyTerms extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.key_terms);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setSubtitle("Important Words for you !");
		TextView item = (TextView) this.findViewById(R.id.textViewKeyTerms);
        item.setText(Html.fromHtml("<font size=4><p><strong>Activity:</strong> Activity is the presenter of a single screen in the application. It is the only one who has certain abilities, like displaying Views, menus, alerts and notifications. It also has the ability to open a new Activity, which means opening a new screen. <br>• Activity is a class which derives from <em>android.app.Activity</em>.<br>• An application needs to have at least one Activity. <br>• All Activities must be declared in the manifest file.</p><p><strong>ADT Plugin:</strong> Android Developer Tools is designed to give you a powerful, integrated environment in which to build Android applications. ADT lets you quickly let up new Android projects, create an application UI, debug your application using <strong>Android SDK tools</strong>, and even export <em>signed</em> or <em>unsigned</em> .apk files to distribute you application.</p><p><strong>Application Programming Interface (API):</strong> an interface used by applications to communicate with each other. An <strong>API </strong>is a library that may include specification for routines, data structures, object classes, and variables. <strong>API</strong>s are particularly useful in extending a platform. </p><p><strong>Density-Independent pixels (dp)</strong>: A virtual pixel unit equivalent to one physical pixel on an average, “medium”density 160 dpi screen.  On higher ppi screens, dp’s are scaled to retain a constant size (px=dp * (dpi/160)).  For example, on a 200 dpi screen, the dp would take up 1.25 physical pixels.</p><p><strong>Drawable</strong>: A compiled visual resource that can be used as a background, title, or other part of the screen. A drawable is typically loaded into another UI element, for example as a background image. A drawable is not able to receive events, but does assign various other properties to enable subclasses such as animation objects or image libraries. Many drawable objects are loaded from drawable resource files — xml or bitmap files that describe the image.</p><p><strong>Intent:</strong> Intent is the negotiator between two activities or between two applications. It gives the ability to pass messages and data between the two entities.When writing applications for mobile, Intent is very handy, since it gives access to a lot of the OS services like opening the camera, a browser, displaying notifications and so on.</p><p><strong>Nine-patch /9-patch/Ninepatch Image</strong>: A resizable bitmap resource that can be used for backgrounds or other images on the device.</p><p><strong>OpenGL ES</strong>: A cross-platform API for full-function <strong>2D and 3D graphics</strong> on embedded systems.  Android provides OpenGL ES libraries that you can use for fast, complex 3D images. It is harder to use than a Canvas object, but better for 3D objects.</p><p><strong>Service:</strong> A Service is an application which has the ability to run in the background without displaying any user interface.A Service is a class which derives from <em>android.app.Service</em>.All Services must be declared in the manifest file.</p><p><strong>Splash Screen: </strong>A splash screen is an image that appears while Android is loading.  Splash screens cover the entire screen or simply a rectangle near the center of the screen. The splash screens of operating systems and some applications that expect to be run full-screen usually cover the entire screen.</p><p><strong>Surface:</strong> An object representing a block of memory that gets composited to the screen. A Surface holds a <strong>Canvas object</strong> for drawing, and provides various helper methods to draw layers and resize the surface. You should not use this class directly;use <em>SurfaceView</em> instead.</p><p><strong>SurfaceView</strong>: A View object that wraps a Surface for drawing, and exposes methods to specify its size and format dynamically. A SurfaceView provides a way to draw independently of the UI thread for resource-intensive operations (such as games or camera previews), but it uses extra memory as a result. SurfaceView supports both Canvas and OpenGL ES graphics.</p><p><strong>View:<em> </em></strong> An object that draws to a rectangular area on the screen and handles click, keystroke, and other interaction events. A <em>View</em> is a base class for most layout components of an <em>Activity</em> or <em>Dialog</em> screen (text boxes, windows, and so on). It receives calls from its parent object (see <em>Viewgroup</em>, below)to draw itself, and informs its parent object about where and how big it would like to be (which may or may not be respected by the parent).</p><p><strong>Viewgroup:<em> </em></strong> A container object that groups a set of <em>child Views</em>. The viewgroup is responsible for deciding where views are positioned and how large they can be, as well as for calling each to draw itself when appropriate. Some viewgroups are invisible and are for layout only, while others have an intrinsic UI (for instance, a scrolling list box).</p><p><strong>Widget</strong>:  A slice or certain view of an application that can be placed on one of your homescreens, for quick and easy access.  Because a widget is fully implemented, it handles measuring and drawing itself and responding to screen events.</p><p><strong>Window</strong>: In an Android application, an object derived from the abstract class Window that specifies the elements of a generic window, such as the look and feel (title bar text, location and content of menus, and so on). Dialog and Activity use an implementation of this class to render a window. You do not need to implement this class or use windows in your application.</p></font>"));
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
