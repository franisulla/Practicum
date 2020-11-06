package grids;

public class EulerGrid extends Grid {

    public EulerGrid(int N, double x0, double xt, double y0) {
        super(N, x0, xt);
        list.get(0).setYValue(y0);
        for (int i=1; i<=N; i++){
            double x = list.get(i-1).getXValue();
            double y = list.get(i-1).getYValue();
            list.get(i).setYValue(y + (xt-x0)*((1+y/x)*Math.log(1+y/x) + y/x)/N);
        }
        label = "Euler ";
    }
}
