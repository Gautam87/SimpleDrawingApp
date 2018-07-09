package draw.blazon.com.simpledrawingapp.models;

class Coordinate {
    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return " (" + x + "," + y + ")";
    }
}
