package grids;

public class Grid_LTE extends Grid {
    public Grid_LTE(int N, double x0, double xt, Grid other, Grid exact) {
        super(N, x0, xt);
        label = other.label;
        for (int i=0; i<=N; i++){
            double y1 = other.list.get(i).getYValue();
            double y2 = exact.list.get(i).getYValue();
            list.get(i).setYValue(Math.abs(y1-y2));
        }
    }
}
