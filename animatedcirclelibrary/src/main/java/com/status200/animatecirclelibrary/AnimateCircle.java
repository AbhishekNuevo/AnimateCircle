package com.status200.animatecirclelibrary;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class AnimateCircle {
    private Context context;
    private int oneCycleTime;
    private int minimumSize;
    private  Runnable r;
    private int factor = 40;
    private boolean increase = true;
    private  int increment = 0;
    private  Handler handler;

    private FrameLayout layout;
    private Circle circle;
    private FrameLayout.LayoutParams params;
    private long delay = 0;
    private boolean pause = false; private boolean stopAnimation = false;
    private TextView tView;
    public  AnimateCircle(Context context,FrameLayout layout,int oneCycleTimeInsecond, int minimumSize){
        this.context = context;
        this.oneCycleTime = oneCycleTime;
        this.minimumSize = minimumSize;
        this.layout = layout;
        params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        this.delay = ( oneCycleTimeInsecond * 1000 ) / 160 ;
        tView = new TextView(context);
        tView.setText("Inhale");
        tView.setTextColor(Color.parseColor("#ffffff"));
    }

    public void startAnimation(){
        stopAnimation = false;   pause = false;
        handler = new Handler();

        r = new Runnable() {
            public void run() {
                Log.e("Animation","factor " + factor );
                if(!pause) {// if pause if false then execute
                    layout.removeAllViews();

                    circle = new Circle(context, factor);
                    layout.setLayoutParams(params);
                    layout.addView(circle,0);
                    layout.addView(tView, 1);
                    tView.setGravity(Gravity.CENTER );



                    if (factor >= 200) {
                        increase = false;

                    } else if (factor <= 40) {
                        increase = true;

                    }
                    if (increase) {
                        factor = factor + 1;
                        tView.setText("Exhale");
                        //tView.setTextSize(TypedValue.COMPLEX_UNIT_SP, factor/3);


                    } else {
                        factor = factor - 1;
                        tView.setText("Inhale");
                        //tView.setTextSize(TypedValue.COMPLEX_UNIT_SP, factor/3);

                    }
                }

                if ( stopAnimation ){
                    handler.removeCallbacks(r);


                }else {
                    handler.postDelayed(this, delay);
                }

            }
        };

        handler.postDelayed(r, delay);


    }

    public void pause(){
        pause = true;
    }
    public void resume(){
        pause = false;
    }
    public boolean getPause(){
        return pause;
    }
    public void stop(){
      this.stopAnimation = true;
        pause = true;
    }
}
