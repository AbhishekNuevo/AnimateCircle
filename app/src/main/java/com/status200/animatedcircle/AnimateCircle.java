package com.status200.animatedcircle;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnimateCircle {
    private Context context;
    private int oneCycleTime;
    private int minimumSize = 110;
    private int maxSize = 200;
    private  Runnable r;
    private int factor = 200;
    private boolean increase = true;
    private  int increment = 0;
    private  Handler handler;

    private FrameLayout layout;
    private Circle circle;
    private FrameLayout.LayoutParams params;
    private long delay = 0, delayTime = 0;
    private boolean pause = false; private boolean stopAnimation = false;
    private TextView tView; Float constantValue = 700f; boolean hold = false; int holdCycle = 0;
    public  AnimateCircle(Context context,FrameLayout layout,long oneCycleTimeInsecond, int minimumSize, int maxSize){
        this.context = context;
        this.oneCycleTime = oneCycleTime;
        this.minimumSize = minimumSize;
        this.maxSize = maxSize;
        this.layout = layout;
        params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        this.delay =  oneCycleTimeInsecond / ( maxSize - minimumSize ) + 20;
        tView = new TextView(context);
        tView.setText("Inhale");
        tView.setTextColor(Color.parseColor("#ffffff"));
        delayTime = this.delay - 20;
        Log.e("Animation delay","" + delay);
    }
   int previousFactor = factor; ;
    public void startAnimation(){
        stopAnimation = false;   pause = false;
        handler = new Handler();

        r = new Runnable() {
            public void run() {
                Log.e("Animation","factor " + factor + hold );
                if (hold){
                    delay = delayTime + 20;
                    holdCycle = holdCycle + 1;
                    tView.setText("HOLD");
                    if (holdCycle > 20){
                        hold = false;
                        holdCycle = 0;
                    }
                }
                if(!pause && !hold) {// if pause if false then execute or when hold is false then run
                    layout.removeAllViews();

                    circle = new Circle(context, factor);
                    layout.setLayoutParams(params);
                    layout.addView(circle,0);
                    layout.addView(tView, 1);
                    tView.setGravity(Gravity.CENTER );



                    if (factor >= maxSize) {
                        increase = false;
                        hold = true;

                    } else if (factor <= minimumSize) {
                        increase = true;
                        hold = true;
                    }
                    float textSize = factor / 3 ;
                    if (increase) {
                        factor = factor + 1;
                        tView.setText("EXHALE");
                        tView.setTextSize(TypedValue.COMPLEX_UNIT_SP, constantValue/textSize);


                    } else {
                        factor = factor - 1;
                        tView.setText("INHALE");
                        tView.setTextSize(TypedValue.COMPLEX_UNIT_SP, constantValue/textSize);

                    }

                    if(maxSize - factor < 10 || factor - minimumSize < 10){
                        if(factor > previousFactor){// rise


                            if (factor <= 155){//slow speed
                                delay = delay - 5;
                            }else if (factor > 155){// speed fast
                                delay = delay + 5;
                            }
                            Log.e("Animation","factor " + factor + "fall" + "delay " + delay );
                        }else if (factor < previousFactor){// fall

                            if (factor <= 155){//slow speed
                                delay = delay + 5;
                            }else if (factor > 155){// speed fast
                                delay = delay - 5;
                            }
                            Log.e("Animation","factor " + factor + "rise" + "delay " + delay);
                        }
                    }else{
                        delay = delayTime;
                        Log.e("Animation","factor " + factor + "comeOut delay" + delay );
                    }
                    previousFactor = factor;
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
