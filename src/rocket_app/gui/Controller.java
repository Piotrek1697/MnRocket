package rocket_app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button playGameBtn;
    @FXML
    private Canvas canvas;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
    }

    public void playGameBtn(ActionEvent actionEvent) {
        System.out.println("PLAY");
    }

    public  void drawShapes(GraphicsContext gc){

        gc.setFill(Color.GOLD);
        gc.fillOval(115,60,30,30);

    }

}
