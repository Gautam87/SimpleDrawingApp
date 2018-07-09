package draw.blazon.com.simpledrawingapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

    private Paint mSquarePaint;
    private Point mPoint;
    private float mSize = 100.0f;

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    /*Setup square paint with color and stroke styles*/
    private void setupPaint() {
        mSquarePaint = new Paint();
        mSquarePaint.setColor(Color.BLUE);
        mSquarePaint.setAntiAlias(true);
        mSquarePaint.setStrokeWidth(3);
        mSquarePaint.setStyle(Paint.Style.STROKE);
    }

    // Draw each circle onto the view
    @Override
    protected void onDraw(Canvas canvas) {
        if (mPoint != null) {
            canvas.drawRect(mPoint.x, mPoint.y,
                    mPoint.x + mSize,
                    mPoint.y + mSize, mSquarePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        mPoint = new Point(Math.round(touchX), Math.round(touchY));
        postInvalidate();
        return true;
    }

}
