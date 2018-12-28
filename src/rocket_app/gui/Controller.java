package rocket_app.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    private RocketAnimation rocketAnimation;
    private ObservableList<RocketParameters> rocketParameters = FXCollections.observableArrayList();

    @FXML
    void playGameBtn(ActionEvent event) {

        rocketAnimation = new RocketAnimation(mainGridPane,minorGridPane);
        rocketAnimation.gameLoop();
        rocketAnimation.setRocketSpeed(3);

        FirstOrderDifferentialEquations rocketODE = new RocketODE(636,1.63);
        FirstOrderIntegrator integrator = new EulerIntegrator(0.1);


        RocketThread rocketThread = new RocketThread();
        RocketState rocketState = new RocketState(rocketODE,integrator,rocketParameters,rocketAnimation,"Rocket1");
        rocketThread.addObserver(rocketState);

        rocketThread.start();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    @FXML
    public void exitApplication() {

        System.out.println("Exit App");
    }
}