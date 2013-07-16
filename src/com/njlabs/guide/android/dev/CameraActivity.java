package com.njlabs.guide.android.dev;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

public class CameraActivity extends Activity {
	private Preview mPreview;
	private Camera mCamera;

	class Preview extends SurfaceView implements SurfaceHolder.Callback {
		SurfaceHolder mHolder;

		@SuppressWarnings("deprecation")
		Preview(Context context) {
			super(context);
			mHolder = getHolder();
			mHolder.addCallback(this);
			mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}

		public void surfaceCreated(SurfaceHolder holder) {
			try {
				mCamera.setPreviewDisplay(holder);
				//takePicture();
			} catch (IOException exception) {
				mCamera.release();
				mCamera = null;
			}
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}

		public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
			Camera.Parameters parameters = mCamera.getParameters();
			parameters.setPreviewSize(w, h);
			mCamera.setParameters(parameters);
			mCamera.startPreview();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		mCamera=Camera.open();
		mCamera.getParameters().setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
		mCamera.setDisplayOrientation(90);
		mPreview = new Preview(this);
		setContentView(mPreview);
	}


	private void takePicture(){
		
		Camera.PictureCallback jpegCallback; 
		
		jpegCallback=new Camera.PictureCallback() {
			
			public void onPictureTaken(byte[] data, Camera camera) {
				
				if(data!=null)
				{
					File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
					FileOutputStream fop = null;
					try{
						fop = new FileOutputStream(new File(path, "TestImage.jpg"));
						fop.write("Data to be written".getBytes());
						fop.flush();
						fop.close();
					}
					catch (Exception e)
					{
						Log.d("CAMERA_DEBUG","Exception"+e.toString());
					}
					Log.d("DEBUG","data length="+data.length);
					Log.d("DEBUG","data="+data);
				}
				else
				{
					Log.d("DEBUG","DATA IS NULL HERE !!!");
				}
			}
		};
		
		mCamera.takePicture(null, null,null, jpegCallback);
	}

	protected void finishActivity(){
		Log.d("DEBUG","CameraAcivity finished");
		mPreview.invalidate();
		mCamera.release();
		this.finish();
	}
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
        		takePicture();
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

}
