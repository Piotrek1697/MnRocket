package rocket_app.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rocket_app.Main;
import rocket_app.rocket.RocketParameters;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

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

    public static void saveToJson(String fileName, ObservableList<Player> players) {
        Gson gson = new GsonBuilder().create();

        File file = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(players, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Player> loadFromJson(File file) {
        Gson gson = new GsonBuilder().create();

        ArrayList<Player> players = null;

        try (InputStream inputStream = new FileInputStream(file)) {
            Reader reader = new InputStreamReader(inputStream);
            players = gson.fromJson(reader, new TypeToken<ObservableList<Player>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return FXCollections.observableArrayList(players);
    }
}
