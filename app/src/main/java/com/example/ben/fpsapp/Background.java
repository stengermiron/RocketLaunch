package com.example.ben.fpsapp;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int x, y, dy;

    public Background(Bitmap res){

        y = -1500;
        image = res;
    }

    public void update() {

        if(y < 0) {
            y += 5;
        }
        if (y >= 0) {
            y = -1500;
        }
    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(image, x, y, null);

    }

    public void setVector(int dy){
        this.dy = dy;

    }
}
