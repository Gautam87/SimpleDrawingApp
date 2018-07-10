package draw.blazon.com.simpledrawingapp.models;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public String toString() {
        return super.toString();
    }

    public List<Coordinate> getCoordinates() {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(Math.round(getPoint().x - getSize()/2) , Math.round(getPoint().y - getSize()/2))); //top left
        coordinates.add(new Coordinate(Math.round(getPoint().x - getSize()/2) , Math.round(getPoint().y + getSize()/2))); //bottom left
        coordinates.add(new Coordinate(Math.round(getPoint().x + getSize()/2) , Math.round(getPoint().y + getSize()/2))); //bottom right
        coordinates.add(new Coordinate(Math.round(getPoint().x + getSize()/2) , Math.round(getPoint().y - getSize()/2))); //top right
        return coordinates;
    }

}
