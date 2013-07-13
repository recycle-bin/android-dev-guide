package com.njlabs.guide.android.dev;

import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class GetPush extends BroadcastReceiver {
private static final String TAG = "MyCustomReceiver";
 
  @Override
  public void onReceive(Context context, Intent intent) {
    try {
      String action = intent.getAction();
      String channel = intent.getExtras().getString("com.parse.Channel");
      JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
 
      Log.d(TAG, "got action " + action + " on channel " + channel + " with:");
      String current_date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
      DatabaseHandler db = new DatabaseHandler(context);
      db.addAnnouncement(new Announcement(json.getString("alert"), json.getString("title"), current_date, "unread"));
      @SuppressWarnings("rawtypes")
	Iterator itr = json.keys();
      while (itr.hasNext()) {
        String key = (String) itr.next();
        Log.d(TAG, "..." + key + " => " + json.getString(key));
        Log.d(TAG," Date" + current_date);
      }
    } catch (JSONException e) {
      Log.d(TAG, "JSONException: " + e.getMessage());
    }
  }

@SuppressWarnings("unused")
private Context getApplicationContext() {
	// TODO Auto-generated method stub
	return null;
}
}
