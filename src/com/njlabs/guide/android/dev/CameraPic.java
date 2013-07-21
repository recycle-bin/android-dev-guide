package com.njlabs.guide.android.dev;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraPic extends Activity {
	final static String DEBUG_TAG = "CameraPic";
	private Camera camera;
	private int cameraId = 0;
	ImageView ImgPreview;
	String path;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		ImgPreview = (ImageView) findViewById(R.id.result);
		path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()+"/com.njlabs.guide.android.dev/sample_picture.jpg";     

		// CHECK IF THERE IS A CAMERA
		if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) 
		{
			Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG).show();
		}
		else
		{
			cameraId = findRearFacingCamera();
			if (cameraId < 0) 
			{
				Toast.makeText(this, "No rear facing facing camera found.",Toast.LENGTH_LONG).show();
			}
			else
			{
				camera = Camera.open(cameraId);
			}
		}
	}

	public void TakePic(View view) 
	{
		camera.takePicture(null, null,new PhotoHandler(getApplicationContext()));
		Bitmap bmp = BitmapFactory.decodeFile(path);
		ImgPreview.setImageBitmap(bmp);
	}

	private int findRearFacingCamera() 
	{
		int cameraId = -1;
		// GET THE REAR FACING CAMERA
		int numberOfCameras = Camera.getNumberOfCameras();
		for (int i = 0; i < numberOfCameras; i++) 
		{
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == CameraInfo.CAMERA_FACING_BACK) // Use CAMERA_FACING_FRONT to CAMERA_FACING_BACK get fron Camera access
			{
				Log.d(DEBUG_TAG, "Camera found");
				cameraId = i;
				break;
			}
		}
		return cameraId;
	}

	@Override
	protected void onPause() 
	{
		if (camera != null) 
		{
			camera.release();
			camera = null;
		}
		super.onPause();
	}
} 
