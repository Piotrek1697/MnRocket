package rocket_app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import rocket_app.gui.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/mainWindow.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        primaryStage.setTitle("Lunar Landing");
        Scene scene = new Scene(root, 290, 395);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        primaryStage.setOnCloseRequest(event -> {
            controller.exitApplication();
            Platform.exit();
            System.exit(0);
        });

        scene.setOnKeyPressed(event -> {
            controller.handleMovementKeys(event);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
