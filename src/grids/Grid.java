package grids;

import javafx.scene.chart.XYChart;
import java.util.ArrayList;


public class Grid extends GenericGrid<Double> {
    public int N;

    public Grid(int N, double x0, double xt){
        if (xt < x0 || N <= 0){
            //TODO throw exception!
        }
        double h = (xt - x0)/N;
        this.N = N;

        list = new ArrayList<>(N+1);
        for (int i=0; i<=N; i++) {
            XYChart.Data<Double, Double> pt = new XYChart.Data<>();
            pt.setXValue(x0+i*h);
            list.add(pt);
        }
    }
}
