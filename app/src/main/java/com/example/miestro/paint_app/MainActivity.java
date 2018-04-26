package com.example.miestro.paint_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

private  Canvasview canvasview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvasview =(Canvasview)findViewById(R.id.canvas);

    }

    public  void clearcanvas(View v)
    {

        canvasview.clearCanvas();




    }

}
