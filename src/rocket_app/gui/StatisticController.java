package rocket_app.gui;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import rocket_app.rocket.RocketParameters;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticController {

    @FXML
    private ScatterChart<Number, Number> statisticLineChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    private static XYChart.Series<Number, Number> statisticSeries = new XYChart.Series<>();
    private ObservableList<RocketParameters> rocketParameters;

    public void setRocketParameters(ObservableList<RocketParameters> rocketParameters) {
        this.rocketParameters = rocketParameters;
        rocketParametersListener();
    }

    private void rocketParametersListener(){
        rocketParameters.addListener((ListChangeListener<RocketParameters>) c -> {
            int parametersSize = rocketParameters.size();

            if (parametersSize == 10) {
                Platform.runLater(() -> {
                    statisticSeries.getData().add(new XYChart.Data<>(rocketParameters.get(parametersSize - 1).getVelocity(), rocketParameters.get(parametersSize - 1).getHeight()));
                });
            }
        });
    }

    @FXML
    public void initialize() {
        statisticLineChart.getData().add(statisticSeries);
        statisticLineChart.setLegendVisible(false);

        xAxis.setLabel("Velocity [m/s]");
        yAxis.setLabel("Height [m]");
    }
}
