package draw.blazon.com.simpledrawingapp.models;

import android.graphics.Point;

public class SquareItem {
    private Point mPoint;
    private float mSize;
    private int mColor;

    public SquareItem(Point mPoint, float mSize, int mColor) {
        this.mPoint = mPoint;
        this.mSize = mSize;
        this.mColor = mColor;
    }

    public Point getPoint() {
        return mPoint;
    }

    public void setPoint(Point mPoint) {
        this.mPoint = mPoint;
    }

    public float getSize() {
        return mSize;
    }

    public void setSize(float mSize) {
        this.mSize = mSize;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }
}
