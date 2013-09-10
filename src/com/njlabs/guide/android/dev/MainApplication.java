package com.njlabs.guide.android.dev;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

import com.parse.Parse;

@ReportsCrashes(
        formKey = "",
        formUri = "https://njlabs.cloudant.com/acra-agad/_design/acra-storage/_update/report",
        reportType = org.acra.sender.HttpSender.Type.JSON,
        httpMethod = org.acra.sender.HttpSender.Method.PUT,
        formUriBasicAuthLogin="oduesentstencertmerectra",
        formUriBasicAuthPassword="I5uFsNUGvvpiMW73YAbmSD8o",
        mode = ReportingInteractionMode.TOAST,
        forceCloseDialogAfterToast = false, // optional, default false
        resToastText = R.string.crash_toast_text
        )
public class MainApplication extends Application {
	
	public void onCreate() { 
		super.onCreate();
		//AppRater.app_launched(this);
	    Parse.initialize(this, "pkJJlHY3G50SV7njK0IB4va3AWufykgt0g7eDHi2", "nJlB4f3Hq1avivVu7RGMV6vmS9BMOE4KoPsekeHE"); 
	    ACRA.init(this);
	}
}
