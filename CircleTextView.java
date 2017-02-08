package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import static java.lang.Math.max;

public class CircleTextView extends TextView {


    private static int PADDING = 0;
    boolean IS_CHECK = false;
    private Paint mCirclePaint;

    Context context;
    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Style.FILL);
        mCirclePaint.setAntiAlias(true);

    }

    void set_back_color(int color)
    {
        int myColor =
                context.getResources().getColor(color);
        mCirclePaint.setColor(myColor);
        invalidate();
    }

    void set_padding_around_text(int padding)
    {
        PADDING = padding;
        invalidate();
    }
    void set_check(boolean check)
    {
        IS_CHECK = check;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // draw circle at center of canvas
        if(IS_CHECK) {
            int ll = max(getMeasuredWidth(), getMeasuredHeight());
            float k = MyUtility.convertPixelsToDp((ll + PADDING * 2) / 2, context);
            canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, k, mCirclePaint);
            setTextColor(Color.WHITE);
        }
        else
            setTextColor(Color.BLACK);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // grow view dimensions to account for circle
        setMeasuredDimension(resolveSize(getMeasuredWidth()+(PADDING*2), widthMeasureSpec), resolveSize(getMeasuredHeight()+(PADDING*2), heightMeasureSpec));
    }

}