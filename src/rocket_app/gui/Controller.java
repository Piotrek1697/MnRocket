package rocket_app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import rocket_app.animation.RocketAnimation;

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
    void playGameBtn(ActionEvent event) {
    }

    RocketAnimation rocketAnimation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rocketAnimation = new RocketAnimation(mainGridPane,minorGridPane);
        rocketAnimation.gameLoop();
        rocketAnimation.setRocketSpeed(0.5);

    }
}