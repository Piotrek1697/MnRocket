package rocket_app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rocket_app.gui.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/mainWindow.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        primaryStage.setTitle("Lunar Landing");
        primaryStage.setScene(new Scene(root, 301, 405));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            controller.exitApplication();
            Platform.exit(); //all thread exit
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
