package com.njlabs.guide.android.dev;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class SpeechRecDemo extends SherlockActivity {
	
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1001;
	private ListView mlvTextMatches;
	private Button mbtSpeak;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speech_rec_demo);
		mlvTextMatches = (ListView) findViewById(R.id.lvTextMatches);
		mbtSpeak = (Button) findViewById(R.id.btSpeak);
		checkVoiceRecognition();
	}
	public void checkVoiceRecognition() 
	{
		// Check if voice recognition is present
		PackageManager pm = getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
		if (activities.size() == 0) {
		mbtSpeak.setEnabled(false);
		mbtSpeak.setText("Voice recognizer not present");
		Toast.makeText(this, "Voice recognizer not present",Toast.LENGTH_SHORT).show();
		}
	}

	public void speak(View view) 
	{
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		
		// Specify the calling package to identify your application
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
		
		//There are two form of language model available
		//1.LANGUAGE_MODEL_WEB_SEARCH : For short phrases
		//2.LANGUAGE_MODEL_FREE_FORM  : If not sure about the words or phrases and its domain.
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		// Specify how many results you want to receive. The results will be
		// sorted where the first result is the one with higher confidence.
		intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 6);
		//Start the Voice recognizer activity for the result.
		startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == VOICE_RECOGNITION_REQUEST_CODE)
		{
			//If Voice recognition is successful then it returns RESULT_OK
			if(resultCode == RESULT_OK) 
			{
			
			ArrayList<String> textMatchList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);			
			if (!textMatchList.isEmpty()) 
			{
				// populate the Matches
				mlvTextMatches.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,textMatchList));
			}			
			//Result code for various error.
			}
			else if(resultCode == RecognizerIntent.RESULT_AUDIO_ERROR)
			{
				showToastMessage("Audio Error");
			}
			else if(resultCode == RecognizerIntent.RESULT_CLIENT_ERROR)
			{
				showToastMessage("Client Error");
			}
			else if(resultCode == RecognizerIntent.RESULT_NETWORK_ERROR)
			{
				showToastMessage("Network Error");
			}
			else if(resultCode == RecognizerIntent.RESULT_NO_MATCH)
			{
				showToastMessage("No Match");
			}
			else if(resultCode == RecognizerIntent.RESULT_SERVER_ERROR)
			{
				showToastMessage("Server Error");
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	void showToastMessage(String message){
		 Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	 }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.speech_rec_demo, menu);
		return true;
	}

}
