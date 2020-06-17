package com.example.mygame;

import android.graphics.Canvas;

class DrawThread extends Thread {

    GameSurfaceView myView;
    private boolean running = false;

    public DrawThread(GameSurfaceView view) {
        myView = view;
    }
    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        while(running){
            Canvas canvas = myView.getHolder().lockCanvas();
            if(canvas != null) {
                synchronized (myView.getHolder()) {
                    myView.drawSomething(canvas);
                }
                myView.getHolder().unlockCanvasAndPost(canvas);
            }
            else{
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}