package grids;

public class EulerImGrid extends Grid {

    public EulerImGrid(int N, double x0, double xt, double y0) {
        super(N, x0, xt);
        list.get(0).setYValue(y0);
        for (int i=1; i<=N; i++){
            double x = list.get(i-1).getXValue();
            double y = list.get(i-1).getYValue();
            double h = (xt-x0)/N;
            double K1 = f(x, y);
            double K2 = f(x+h, y+h*K1);
            list.get(i).setYValue(y + h * (K1 + K2) / 2);
        }
        label = "Improved Euler      ";
    }

    private double f (double x, double y){
        return (1+y/x)*Math.log(1+y/x) + y/x;
    }
}
