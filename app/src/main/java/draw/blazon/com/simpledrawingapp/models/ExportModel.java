package draw.blazon.com.simpledrawingapp.models;

import java.util.List;

public class ExportModel {
    private List<Coordinate> coordinateList;

    public ExportModel(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    @Override
    public String toString() {
        return "{coordinates: " + coordinateList.toString() + "}";
    }

}
