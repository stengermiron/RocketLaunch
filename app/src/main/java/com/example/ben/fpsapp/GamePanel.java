package com.example.ben.fpsapp;

/**
 * Created by Ben on 6/26/2015.
 */
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    public static final int WIDTH = 100 * 2;
    public static final int HEIGHT = 10000;
    private MainThread thread;
    private Background bg;

    public GamePanel(Context context)
    {
        super(context);


        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry)
        {
            try{thread.setRunning(false);
                thread.join();

            }catch(InterruptedException e){e.printStackTrace();}
            retry = false;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.skybackground5));
        bg.setVector(50);
        System.out.println("getWidth()");
        System.out.println(getWidth());
        System.out.println();
        System.out.println("getHeight");
        System.out.println(getHeight());
        System.out.println();
        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }
    public void update()
    {
        bg.update();
    }

    @Override
    public void draw(Canvas canvas){

        final float scaleFactorX = getWidth() / WIDTH;
        //final float scaleFactorY = getHeight() / HEIGHT;
        //System.out.println(scaleFactorX + " x");
        //System.out.println(scaleFactorY + " y");
        if(canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, 1);
            bg.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }
}
