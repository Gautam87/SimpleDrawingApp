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

import java.util.ArrayList;

public class DrawingView extends View {

    private Paint mSquarePaint;
    private ArrayList<Point> mSquarePoints;
    private float mSize = 400.0f;

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        mSquarePoints = new ArrayList<>();
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

        for (Point point : mSquarePoints) {
            if (point != null) {
                canvas.drawRect(point.x - mSize / 2, point.y - mSize / 2,
                        point.x + mSize / 2,
                        point.y + mSize / 2, mSquarePaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        mSquarePoints.add(new Point(Math.round(touchX), Math.round(touchY)));
        postInvalidate();
        return true;
    }

}
