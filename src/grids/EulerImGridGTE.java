package grids;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class EulerImGridGTE extends GenericGrid<Integer>{


    public EulerImGridGTE(int n0, int nt, double x0, double xt, double y0) {
        this.list = new ArrayList<>(nt-n0);
        this.label = "Euler Improved        ";
        for (int i=n0; i<nt; i++){
            EulerImGrid eulerImGrid = new EulerImGrid(i, x0, xt, y0);
            ExactGrid exactGrid = new ExactGrid(i, x0, xt, y0);

            double y1 = eulerImGrid.list.get(i).getYValue();
            double y2 = exactGrid.list.get(i).getYValue();
            XYChart.Data<Integer, Double> pt = new XYChart.Data<>();
            pt.setXValue(i);
            pt.setYValue(Math.abs(y1-y2));
            list.add(pt);
        }
    }
}
