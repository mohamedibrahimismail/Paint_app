package com.example.miestro.paint_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by MIESTRO on 19/01/2018.
 */

public class Canvasview extends View {

    public int width;
    public int height;
    private Bitmap bitmap;
    private Canvas canvas;
    private Path path;
    private Paint paint;
    private float x,y;
    private  static final float tolerance = 5;
    Context context;

    public Canvasview(Context context,AttributeSet attributeSet) {
        super(context,attributeSet);
        this.context=context;
        path = new Path();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(4f);





    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path,paint);
    }

    private void onStartTouch(float x , float y){

        path.moveTo(x,y);
        this.x = x;
        this.y = y;

    }

    private void moveTouch(float x , float y){

       float dx = Math.abs( x-this.x);
       float dy = Math.abs( y-this.y);


        if(dx >= tolerance || dy >= tolerance){

            path.quadTo(this.x,this.y,(x+this.x)/2,(y+this.y)/2);
            this.x = x;
            this.y = y;
        }

    }

    public void clearCanvas(){

        path.reset();
        invalidate();


    }

    private  void upTouch(){

        path.lineTo(x,y);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                onStartTouch(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
               upTouch();
                invalidate();
                break;

        }

        return true;
    }
}
