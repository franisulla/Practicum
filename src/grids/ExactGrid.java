package grids;

public class ExactGrid extends Grid {

    public ExactGrid(int N, double x0, double xt, double y0) {
        super(N, x0, xt);
        double c = Math.log(Math.abs((y0+x0)/x0))/x0;
        for (int i=0; i<=N; i++){
            double x = list.get(i).getXValue();
            double y = x*Math.exp(c*x)-x;
            list.get(i).setYValue(y);
        }
        label = "Exact Solution";
    }
}
