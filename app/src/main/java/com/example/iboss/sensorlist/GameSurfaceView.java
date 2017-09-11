package com.example.iboss.sensorlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by iboss on 2017/7/23.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder surfaceHolder;

    private void setupSurfaceHolder() {
        if (surfaceHolder == null) {
            surfaceHolder = getHolder();
        }
        surfaceHolder.setKeepScreenOn(true);
    }

    public static DrawThread myDrawThread = null;

    public void startThread()
    {
        if(myDrawThread!=null)
        {
            myDrawThread.setRunning(false);
            myDrawThread.interrupt();
            try {
                myDrawThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        myDrawThread=null;

        if (myDrawThread == null) {
            myDrawThread = new DrawThread(this);
            myDrawThread.setRunning(true);
            myDrawThread.start();
        }
    }

    Bitmap bearBmp=null;

    public GameSurfaceView(Context context,
                            AttributeSet attrs) {
        super(context, attrs);
        bearBmp= BitmapFactory.decodeResource(getResources(), R.drawable.bear);
        setupSurfaceHolder();
        startThread();
    }

    private void clearBG(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
    }

    public static float currX=0;
    public static float currY=0;

    protected void drawSomething(Canvas canvas) {
        clearBG(canvas);
        int width = this.getWidth();
        int height = this.getHeight();

        if(currX<0)
            currX=0;
        if(currY<0)
            currY=0;
        if(currX+bearBmp.getWidth()>width)
            currX=width-bearBmp.getWidth();
        if(currY+bearBmp.getHeight()>height)
            currY=height-bearBmp.getHeight();

        canvas.drawBitmap(bearBmp, currX, currY, null);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("[PCC]", "surfaceCreated!!");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("[PCC]", "surfaceChanged!!");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("[PCC]", "surfaceDestroyed!!");
    }
}




