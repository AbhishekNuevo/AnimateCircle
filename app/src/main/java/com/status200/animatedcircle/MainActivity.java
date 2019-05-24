package com.status200.animatedcircle;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
   String TAG = "MainActivity"; Handler handler;
     ImageView cirCularImage; ConstraintLayout.LayoutParams lp;
    FrameLayout layout;
    Circle circle; Button button,stopButton;
    FrameLayout.LayoutParams params;  TextView tview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        stopButton = findViewById(R.id.stopButton);

        tview = new TextView(this);
        tview.setText("Inhale");

         layout = (FrameLayout)findViewById(R.id.content);
//        params = new FrameLayout.LayoutParams(
//                FrameLayout.LayoutParams.MATCH_PARENT,
//                FrameLayout.LayoutParams.MATCH_PARENT);
//        layout.setLayoutParams(params);
//        layout.addView(circle,0);
//        layout.addView(tview, 1);
//        tview.setGravity(Gravity.CENTER );

       // circle.setLayoutParams(new FrameLayout.LayoutParams(100, 100));
        //params.addRule(RelativeLayout.CENTER_IN_PARENT);

      //  tview.setLayoutParams(params);

      //  layout.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);

        final AnimateCircle animateCircle = new AnimateCircle(this,layout,2500,30,85,"#ff8900");
        animateCircle.setTextSize(10);
        //animateCircle.setHoldText("");
        animateCircle.startAnimation();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animateCircle.getPause()){
                    animateCircle.resume();
                }else{
                    animateCircle.pause();
                }

            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animateCircle.getPause()){
                    animateCircle.startAnimation();
                }else{
                    animateCircle.stop();
                }
            }
        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                layout.removeAllViews();
//
//                circle = new Circle(MainActivity.this, 2);
//                layout.setLayoutParams(params);
//                layout.addView(circle,0);
//                layout.setGravity(Gravity.CENTER_VERTICAL | Gravity.TOP);
//                handler.postDelayed(this, 400);
//            }
//        }, 5000);
    }
    Runnable r;
    int factor = 1;
    boolean increase = true;
    int increment = 0;
    @Override
    protected void onResume() {
        super.onResume();
//        handler = new Handler();
//
//         r = new Runnable() {
//            public void run() {
//                layout.removeAllViews();
//
//                circle = new Circle(MainActivity.this, factor);
//                layout.setLayoutParams(params);
//                layout.addView(circle,0);
//                layout.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
//                if( increase ){
//                    factor = factor + 1;
//
//                }else {
//                    factor = factor - 1;
//                }
//
//                if (factor >= 200 ){
//                    increase = false;
//                }else if (factor <= 40){
//                    increase = true;
//                }
//
//                handler.postDelayed(this, 50);
//
//            }
//        };
//
//        handler.postDelayed(r, 50);
    }
//
//    private void chnageSize(){
//
//    }
//    int i = 50;
//    private void animateView(){
//
//        lp.width = i + 20;
//        lp.height = i + 20;
//        cirCularImage.setLayoutParams(lp);
//        cirCularImage.requestLayout();
//    }


}
