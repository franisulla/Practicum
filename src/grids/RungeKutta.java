package grids;

public class RungeKutta extends Grid {

    public RungeKutta(int N, double x0, double xt, double y0) {
        super(N, x0, xt);
        list.get(0).setYValue(y0);
        for (int i=1; i<=N; i++){
            double x = list.get(i-1).getXValue();
            double y = list.get(i-1).getYValue();
            double h = (xt-x0)/N;
            double K1 = f(x, y);
            double K2 = f(x+h/2, y+h*K1/2);
            double K3 = f(x+h/2, y+h*K2/2);
            double K4 = f(x+h, y+h*K3);
            list.get(i).setYValue(y + h * (K1 + 2*K2 + 2*K3 + K4) / 6);
        }
        label = "Runge-Kutta ";
    }

    private double f (double x, double y){
        return (1+y/x)*Math.log(1+y/x) + y/x;
    }
}
