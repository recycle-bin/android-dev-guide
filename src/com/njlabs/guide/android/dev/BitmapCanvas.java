package com.njlabs.guide.android.dev;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class BitmapCanvas extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new BitmapView(this));
    }
  
    class BitmapView extends View {
        public BitmapView(Context context) {
            super(context);
        }
  
        @Override
        public void onDraw(Canvas canvas) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sample_img);
            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(bmp, 10, 10, null);
        }
    }
}