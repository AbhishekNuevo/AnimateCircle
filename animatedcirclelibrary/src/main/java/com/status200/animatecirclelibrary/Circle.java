package com.status200.animatecirclelibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Circle extends View {



    private Paint mCircleGray;
    private float mRadius;
    private RectF mArcBounds = new RectF();
  int factor = 2;
    public Circle(Context context, int factor) {
        super(context);
        this.factor = factor;
        initPaints();
    }
    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    public Circle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private void initPaints() {


        mCircleGray = new Paint();
        mCircleGray.setStyle(Paint.Style.FILL);
        mCircleGray.setColor(Color.RED);


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mRadius = Math.min(w, h) / 2f;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        int size = Math.min(w, h);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mArcBounds.set(0 + factor,0 + factor,getWidth() - factor,getHeight() - factor);
      canvas.drawOval(mArcBounds,mCircleGray);

    }

}
