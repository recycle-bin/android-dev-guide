package com.njlabs.guide.android.dev;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class AndroidSDK extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_sdk);
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setSubtitle("Software Development Kit");
        TextView item = (TextView) this.findViewById(R.id.textViewAndroidSDK);
        item.setText(Html.fromHtml("<h2> The Android SDK </h2><div align='justify'><p>The <strong>Android software development kit</strong> (SDK) includes a comprehensive set of development tools. These include a debugger, libraries, a handset emulator based on <strong>QEMU</strong>, documentation, sample code, and tutorials. Currently supported development platforms include computers running Linux (any modern desktop Linux distribution), Mac OS X 10.5.8 or later, Windows XP or later;for the moment one cannot develop Android software on Android itself. The officially supported integrated development environment (IDE) is Eclipse using the <strong>Android Development Tools </strong>(ADT) Plugin, though <strong>IntelliJ IDEA IDE </strong>(all editions) fully supports Android development out of the box, and <strong>NetBeans IDE</strong> also supports Android development via a plugin. Additionally, developers may use any text editor to edit Java and XML files, then use command line tools (<strong>Java Development Kit </strong>and <strong>Apache Ant</strong> are required) to create, build and debug Android applications as well as control attached Android devices (e.g., triggering a reboot, installing software package(s) remotely). </p><p>Enhancements to Android's SDK go hand in hand with the overall Android platform development. The SDK also supports older versions of the Android platform in case developers wish to target their applications at older devices. Development tools are downloadable components, so after one has downloaded the latest version and platform, older platforms and tools can also be downloaded for compatibility testing.</p></div><h2> What's inside the SDK ?</h2><div align='justify'><p>The Android SDK is composed of modular packages that you can download separately using the Android SDK Manager. For example, when the SDK Tools are updated or a new version of the Android platform is released, you can use the SDK Manager to quickly download them to your environment. Simply follow the procedures described in Adding Platforms and Packages.</p><p>There are several different packages available for the Android SDK. The table below describes most of the available packages and where they're located once you download them.</p></div><h2> Available Packages </h2><div align='justify'><p><strong>SDK Tools: </strong>Contains tools for debugging and testing, plus other utilities that are required to develop an app. If you've just installed the SDK starter package, then you already have the latest version of this package. Make sure you keep this up to date.<br/><strong>located inside </strong><font color='#006600' >/[SDK]/tools/</font></p><p><strong>SDK Platform-tools: </strong>Contains platform-dependent tools for developing and debugging your application. These tools support the latest features of the Android platform and are typically updated only when a new platform becomes available. These tools are always backward compatible with older platforms, but you must be sure that you have the latest version of these tools when you install a new SDK platform.<br/><strong>located inside </strong><font color='#006600' >/[SDK]/platform-tools/</font></p><p><strong>Documentation: </strong>An offline copy of the latest documentation for the Android platform APIs.<br/><strong>located inside </strong><font color='#006600' >/[SDK]/docs/</font></p><p><strong>SDK Platform: </strong>There's one SDK Platform available for each version of Android. It includes an android.jar file with a fully compliant Android library. In order to build an Android app, you must specify an SDK platform as your build target.<br/><strong>located inside </strong><font color='#006600' >/[SDK]/platforms/[android-version]/</font></p><p><strong>System Images: </strong>Each platform version offers one or more different system images (such as for ARM and x86). The Android emulator requires a system image to operate. You should always test your app on the latest version of Android and using the emulator with the latest system image is a good way to do so.<br/><strong>located inside </strong><font color='#006600' >/[SDK]/platforms/[android-version]/</font></p><p><strong>Sources for Android SDK: </strong>A copy of the Android platform source code that's useful for stepping through the code while debugging your app.<br/><strong>located inside </strong><font color='#006600' >/[SDK]/sources/</font></p><p><strong>Samples for SDK: </strong>A collection of sample apps that demonstrate a variety of the platform APIs. These are a great resource to browse Android app code. The API Demos app in particular provides a huge number of small demos you should explore.<br/><strong>located inside </strong><font color='#006600' >/[SDK]/platforms/[android-version]/samples/</font></p><p><strong>Google APIs: </strong>An SDK add-on that provides both a platform you can use to develop an app using special Google APIs and a system image for the emulator so you can test your app using the Google APIs.<br/><strong>located inside </strong><font color='#006600' >/[SDK]/add-ons/</font></p><p><strong>Android Support: </strong>A static library you can include in your app sources in order to use powerful APIs that aren't available in the standard platform. For example, the support library contains versions of the <em>Fragment</em> class that's compatible with Android 1.6 and higher (the class was originally introduced in Android 3.0) and the <em>ViewPager</em> APIs that allow you to easily build a side-swipeable UI.<br/><strong>located inside </strong><font color='#006600' >/[SDK]/extras/android/support/</font></p><p><strong>Google Play Billing: </strong>Provides the static libraries and samples that allow you to integrate billing services in your app with Google Play.<br/><strong>located inside  </strong><font color='#006600' >/[SDK]/extras/google/</font></p><p><strong>Google Play Licensing: </strong>Provides the static libraries and samples that allow you to perform license verification for your app when distributing with Google Play.<br/><strong>located inside </strong><font color='#006600' >/[SDK]/extras/google/</font></p></div>"));

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
