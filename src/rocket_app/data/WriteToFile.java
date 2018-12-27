package rocket_app.data;

import javafx.collections.ObservableList;
import rocket_app.rocket.RocketParameters;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteToFile {

    public static void saveToTxt(String fileName, ObservableList<RocketParameters> rocketParameters){
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            StringBuilder values = new StringBuilder();

            for (RocketParameters r : rocketParameters){
                values.append(r.getHeight()+";"+r.getVelocity()+";"+r.getMass()+"\n");
            }

            printWriter.println(values.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
