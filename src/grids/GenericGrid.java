package grids;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class GenericGrid<T> {
    public ArrayList<XYChart.Data<T, Double>> list;
    public String label;
}
