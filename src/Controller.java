import grids.ExactGrid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    public TextField textbox_x0;
    public TextField textbox_xt;
    public TextField textbox_y0;
    public TextField textbox_N;
    public LineChart chart1;

    XYChart.Series series_exact;
    XYChart.Series series_euler;
    XYChart.Series series_eulerIm;
    XYChart.Series series_runge_kutta;

    private void plot(){
        try {
            double x0 = Double.parseDouble(textbox_x0.getText());
            double xt = Double.parseDouble(textbox_xt.getText());
            double y0 = Double.parseDouble(textbox_y0.getText());
            int N = Integer.parseInt(textbox_N.getText());

            ExactGrid exactGrid = new ExactGrid(N, x0, xt);
            series_exact = new XYChart.Series("Exact Solution", FXCollections.observableArrayList(exactGrid.list));

            chart1.getData().addAll(series_exact);
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please input valid values!");
            alert.show();
        }
    }


    public void button_plot_click(ActionEvent actionEvent) {
        plot();
    }

    public void checkbox_ex_checkedChange(ActionEvent actionEvent) {
    }

    public void checkbox_euler_checkedChange(ActionEvent actionEvent) {
    }

    public void checkbox_euIm_checkedChange(ActionEvent actionEvent) {
    }

    public void checkbox_rk_checkedChange(ActionEvent actionEvent) {
    }
}
