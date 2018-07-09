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

import draw.blazon.com.simpledrawingapp.models.SquareItem;

public class DrawingView extends View {

    private Paint mSquarePaint;
    private ArrayList<SquareItem> mSquareItems;
    private float mSize = 10.0f;
    //initial value
    private int mSelectedColor = Color.BLACK;

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        mSquareItems = new ArrayList<>();
        mSquarePaint = getPaintByColor(mSelectedColor);
    }

    /*Setup square paint with color and stroke styles*/
    private void setupPaint() {
        mSquarePaint = new Paint();
        mSquarePaint.setColor(mSelectedColor);
        mSquarePaint.setAntiAlias(true);
        mSquarePaint.setStrokeWidth(3);
        mSquarePaint.setStyle(Paint.Style.STROKE);
    }

    // Draw each circle onto the view
    @Override
    protected void onDraw(Canvas canvas) {
        for (SquareItem item : mSquareItems) {
            if (item != null && item.getPoint() != null) {
                Point p = item.getPoint();
                mSquarePaint.setColor(item.getColor());
                canvas.drawRect(p.x - item.getSize() / 2, p.y - item.getSize() / 2,
                        p.x + item.getSize() / 2,
                        p.y + item.getSize() / 2, mSquarePaint);
            }
        }
        //reset paint color to selected value
        mSquarePaint.setColor(mSelectedColor);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        Point p = new Point(Math.round(touchX), Math.round(touchY));
        SquareItem squareItem = new SquareItem(p, mSize, mSquarePaint.getColor());
        mSquareItems.add(squareItem);
        postInvalidate();
        return true;
    }

    public Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }

    public void setSize(float size) {
        mSize = 10.0f + size;
    }

}
