package rocket;

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



    public void playGameBtn(ActionEvent actionEvent) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);


    }


    public  void drawShapes(GraphicsContext gc){

        gc.setFill(Color.GOLD);
        gc.fillOval(115,60,30,30);

    }

}
