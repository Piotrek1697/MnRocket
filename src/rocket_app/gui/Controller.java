package rocket_app.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;
import rocket_app.animation.RocketAnimation;
import rocket_app.equations.RocketODE;
import rocket_app.rocket.RocketParameters;
import rocket_app.rocket.RocketState;
import rocket_app.rocket.RocketThread;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private GridPane mainGridPane;
    @FXML
    private GridPane minorGridPane;
    @FXML
    private GridPane rocketPane;
    @FXML
    private Button playGameBtn;
    @FXML
    private Label heightLabel;
    @FXML
    private Label velocityLabel;
    @FXML
    private Slider thrustSlider;
    @FXML
    private ProgressBar fuelBar;

    private RocketAnimation rocketAnimation;
    private ObservableList<RocketParameters> rocketParameters = FXCollections.observableArrayList();
    private RocketThread rocketThread;
    private RocketState rocketState;

    private final static double gravity = 1.63;
    private final static int flameVelocity = 636;
    private final static double dt = 0.1;
    private final static String rocketName = "Rocket1";
    private final static double rocketMass = 1000;
    private final static double fullTank = 1730.14;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rocketAnimation = new RocketAnimation(mainGridPane, minorGridPane);
        rocketAnimation.gameLoop();
        rocketAnimation.setRocketSpeed(3);

        fuelBar.setProgress(1);

        rocketParameters.addListener((ListChangeListener<RocketParameters>) c -> {

            updateLabelsValues();
            updateFuelProgress();

            System.out.println(rocketParameters.size());

        });
    }

    private void updateLabelsValues(){
        Platform.runLater(() -> {
            double height = rocketParameters.get(rocketParameters.size() - 1).getHeight();
            double velocity = rocketParameters.get(rocketParameters.size() - 1).getVelocity();

            heightLabel.textProperty().set(String.format("%.0f",height));
            velocityLabel.textProperty().set(String.format("%.2f",velocity));
        });
    }

    private void updateFuelProgress(){
        Platform.runLater(() -> {
            double fuelMass = rocketParameters.get(rocketParameters.size()-1).getMass() - rocketMass;
            double state = fuelMass/fullTank;

            if (state < 0.01)
                state = 0;

            fuelBar.setProgress(state);
            System.out.println(state);
        });
    }


    @FXML
    void playGameBtn(ActionEvent event) {
        FirstOrderDifferentialEquations rocketODE = new RocketODE(flameVelocity, gravity);
        FirstOrderIntegrator integrator = new EulerIntegrator(dt);

        rocketThread = new RocketThread();
        rocketThread.setMi(-16.5);
        rocketState = new RocketState(rocketODE, integrator, rocketParameters, rocketName);
        rocketThread.addObserver(rocketState);

        rocketThread.start();
    }

    @FXML
    public void exitApplication() {

        System.out.println("Exit App");
    }
}