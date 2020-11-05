package grids;

public class ExactGrid extends Grid {

    public ExactGrid(int N, double x0, double xt) {
        super(N, x0, xt);
        for (int i=0; i<N; i++){
            //TODO change sin to exact
            list.get(i).setYValue(Math.sin(list.get(i).getXValue()));
        }
    }
}
