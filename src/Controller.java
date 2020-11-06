import grids.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class Controller {

    public TextField textbox_x0;
    public TextField textbox_xt;
    public TextField textbox_y0;
    public TextField textbox_N;
    public LineChart chart1;
    public LineChart chart2;
    public CheckBox checkbox_ex;
    public CheckBox checkbox_euler;
    public CheckBox checkbox_euIm;
    public CheckBox checkbox_rk;
    public NumberAxis ySol;
    public TabPane root;
    public NumberAxis yLt;
    public LineChart chart3;
    public TextField textbox_n0;
    public TextField textbox_nt;
    public NumberAxis yGTE;
    public CheckBox checkbox_euler1;
    public CheckBox checkbox_euIm1;
    public CheckBox checkbox_rk1;
    public NumberAxis xsx3;
    public NumberAxis xsx2;
    public NumberAxis xsx1;

    XYChart.Series series_exact;
    XYChart.Series series_euler;
    XYChart.Series series_eulerLTE;
    XYChart.Series series_eulerIm;
    XYChart.Series series_eulerImLTE;
    XYChart.Series series_runge_kutta;
    XYChart.Series series_runge_kuttaLTE;
    XYChart.Series series_eulerGTE;
    XYChart.Series series_eulerImGTE;
    XYChart.Series series_rungeKuttaGTE;

    @FXML
    public void initialize(){
        plot();
        plot2();
    }

    private void plot2() {
        chart3.getData().clear();
        try {
            double x0 = Double.parseDouble(textbox_x0.getText());
            double xt = Double.parseDouble(textbox_xt.getText());
            double y0 = Double.parseDouble(textbox_y0.getText());
            int N0 = Integer.parseInt(textbox_n0.getText());
            int Nt = Integer.parseInt(textbox_nt.getText());

            EulerGridGTE eulerGridGTE = new EulerGridGTE(N0, Nt, x0, xt, y0);
            EulerImGridGTE eulerImGridGTE = new EulerImGridGTE(N0, Nt, x0, xt, y0);
            RungeKuttaGTE rungeKuttaGTE = new RungeKuttaGTE(N0, Nt, x0, xt, y0);

            xsx3.setLowerBound(N0);
            xsx3.setLowerBound(Nt);
            xsx3.setTickUnit((Nt-N0)/10);

            yGTE.setLowerBound(rungeKuttaGTE.list.get(Nt-N0-1).getYValue());
            yGTE.setUpperBound(eulerGridGTE.list.get(0).getYValue());
            yGTE.setTickUnit(eulerGridGTE.list.get(0).getYValue()/10);

            series_eulerGTE = initSeries(eulerGridGTE);
            series_eulerImGTE = initSeries(eulerImGridGTE);
            series_rungeKuttaGTE = initSeries(rungeKuttaGTE);

            if (checkbox_euler1.isSelected()){
                chart3.getData().add(series_eulerGTE);
            }
            if (checkbox_euIm1.isSelected()){
                chart3.getData().add(series_eulerImGTE);
            }
            if (checkbox_rk1.isSelected()){
                chart3.getData().add(series_rungeKuttaGTE);
            }
        }
        catch (Exception e){
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please input valid values!");
            alert.show();
        }
    }

    private void plot(){
        chart1.getData().clear();
        chart2.getData().clear();
        try {
            double x0 = Double.parseDouble(textbox_x0.getText());
            double xt = Double.parseDouble(textbox_xt.getText());
            double y0 = Double.parseDouble(textbox_y0.getText());
            int N = Integer.parseInt(textbox_N.getText());

            ExactGrid exactGrid = new ExactGrid(N, x0, xt, y0);
            EulerGrid eulerGrid = new EulerGrid(N, x0, xt, y0);
            Grid_LTE euler_lte = new Grid_LTE(N, x0, xt, eulerGrid, exactGrid);
            EulerImGrid eulerImGrid = new EulerImGrid(N, x0, xt, y0);
            Grid_LTE eulerIm_lte = new Grid_LTE(N, x0, xt, eulerImGrid, exactGrid);
            RungeKutta rungeKutta = new RungeKutta(N, x0, xt, y0);
            Grid_LTE rungeKutta_lte = new Grid_LTE(N, x0, xt, rungeKutta, exactGrid);


            xsx1.setLowerBound(x0);
            xsx1.setUpperBound(xt);
            xsx1.setTickUnit((xt-x0)/10);

            xsx2.setLowerBound(x0);
            xsx2.setUpperBound(xt);
            xsx2.setTickUnit((xt-x0)/10);

            if (exactGrid.list.get(N).getYValue() > 0){
                ySol.setLowerBound(0);
                ySol.setUpperBound(exactGrid.list.get(N).getYValue());
                ySol.setTickUnit(exactGrid.list.get(N).getYValue()/10);
            }
            else{
                ySol.setUpperBound(0);
                ySol.setLowerBound(exactGrid.list.get(N).getYValue());
                ySol.setTickUnit(exactGrid.list.get(N).getYValue()/10);

            }

            yLt.setLowerBound(0);
            yLt.setUpperBound(euler_lte.list.get(N).getYValue());
            yLt.setTickUnit(euler_lte.list.get(N).getYValue()/10);

            series_exact = initSeries(exactGrid);
            series_euler = initSeries(eulerGrid);
            series_eulerLTE = initSeries(euler_lte);
            series_eulerIm = initSeries(eulerImGrid);
            series_eulerImLTE = initSeries(eulerIm_lte);
            series_runge_kutta = initSeries(rungeKutta);
            series_runge_kuttaLTE = initSeries(rungeKutta_lte);

            if (checkbox_euler.isSelected()){
                chart1.getData().add(series_euler);
                chart2.getData().add(series_eulerLTE);
            }
            if (checkbox_euIm.isSelected()){
                chart1.getData().add(series_eulerIm);
                chart2.getData().add(series_eulerImLTE);
            }
            if (checkbox_rk.isSelected()){
                chart1.getData().add(series_runge_kutta);
                chart2.getData().add(series_runge_kuttaLTE);
            }
            if (checkbox_ex.isSelected()){
                chart1.getData().add(series_exact);
            }
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please input valid values!");
            alert.show();
        }
    }

    private XYChart.Series initSeries(GenericGrid grid){
        return new XYChart.Series(grid.label, FXCollections.observableArrayList(grid.list));
    }


    public void button_plot_click(ActionEvent actionEvent) {
        plot();
    }


    private void checkbox(XYChart chart, CheckBox checkBox, XYChart.Series series){
        if (checkBox.isSelected()){
            chart.getData().add(series);
        }
        else{
            chart.getData().remove(series);
        }
    }

    public void checkbox_ex_checkedChange(ActionEvent actionEvent) {
        checkbox(chart1, checkbox_ex, series_exact);
    }

    public void checkbox_euler_checkedChange(ActionEvent actionEvent) {
        checkbox(chart1, checkbox_euler, series_euler);
        checkbox(chart2, checkbox_euler, series_eulerLTE);
    }

    public void checkbox_euler_checkedChange2(ActionEvent actionEvent) {
        checkbox(chart3, checkbox_euler1, series_eulerGTE);
    }

    public void checkbox_euIm_checkedChange(ActionEvent actionEvent) {
        checkbox(chart1, checkbox_euIm, series_eulerIm);
        checkbox(chart2, checkbox_euIm, series_eulerImLTE);
    }
    public void checkbox_euIm_checkedChange2(ActionEvent actionEvent) {
        checkbox(chart3, checkbox_euIm1, series_eulerImGTE);
    }

    public void checkbox_rk_checkedChange(ActionEvent actionEvent) {
        checkbox(chart1, checkbox_rk, series_runge_kutta);
        checkbox(chart2, checkbox_rk, series_runge_kuttaLTE);
    }
    public void checkbox_rk_checkedChange2(ActionEvent actionEvent) {
        checkbox(chart3, checkbox_rk1, series_rungeKuttaGTE);
    }

    
    
    public void button_plot_click2(ActionEvent actionEvent) {
        plot2();
    }
}
