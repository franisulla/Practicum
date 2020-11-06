package grids;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class RungeKuttaGTE extends GenericGrid<Integer>{


    public RungeKuttaGTE(int n0, int nt, double x0, double xt, double y0) {
        this.list = new ArrayList<>(nt-n0);
        this.label = "Runge-Kutta";
        for (int i=n0; i<nt; i++){
            RungeKutta rungeKutta = new RungeKutta(i, x0, xt, y0);
            ExactGrid exactGrid = new ExactGrid(i, x0, xt, y0);

            double y1 = rungeKutta.list.get(i).getYValue();
            double y2 = exactGrid.list.get(i).getYValue();
            XYChart.Data<Integer, Double> pt = new XYChart.Data<>();
            pt.setXValue(i);
            pt.setYValue(Math.abs(y1-y2));
            list.add(pt);
        }
    }
}
