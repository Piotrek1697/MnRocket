package rocket_app.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;
import rocket_app.animation.AnimationData;
import rocket_app.animation.RocketAnimation;
import rocket_app.data.FileManager;
import rocket_app.data.Player;
import rocket_app.equations.RocketODE;
import rocket_app.rocket.RocketParameters;
import rocket_app.rocket.RocketState;
import rocket_app.rocket.RocketThread;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;

public class Controller {

    @FXML
    private Label endHeadFuelLabel;
    @FXML
    private Label endHeadSpeedLabel;
    @FXML
    private Label endFuelLabel;
    @FXML
    private Label endSpeedLabel;
    @FXML
    private Label mnRocketLabel;
    @FXML
    private Button restartGameBtn;
    @FXML
    private GridPane playBackground;
    @FXML
    private GridPane groundZeroPane;
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
    @FXML
    private Label fuelLabel;
    @FXML
    private Label powerLabel;
    @FXML
    private Button statistisWindowBtn;
    @FXML
    private ImageView rocketImage;
    @FXML
    private Label endGameStatusLabel;
    @FXML
    private TableView<Player> leadersTable;
    @FXML
    private TableColumn<?, ?> rankColumn;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> fuelColumn;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button addButton;


    private static final DecimalFormat df2 = new DecimalFormat(".##");
    private RocketAnimation rocketAnimation;
    private ObservableList<RocketParameters> rocketParameters = FXCollections.observableArrayList();
    private ObservableList<Player> players = FXCollections.observableArrayList();
    private RocketThread rocketThread = new RocketThread();
    private RocketState rocketState;

    private final static double gravity = 1.63;
    private final static int flameVelocity = 636;
    private final static double dt = 0.1;

    private final static String rocketName = "Rocket1";
    private final static double rocketMass = 1000;
    private final static double fullTank = 1730.14;
    private final static double fullPower = -16.5;
    private final static double sliderValue = 0;

    private final static double heightStart = 50000;
    private final static double velocityStart = -150;

    private double finalFuelMass;
    private double finalSpeed;

    private final static String playersJsonPath = "res/files/playersData.json";

    private static Controller controller;

    /**
     * The method initializes fundamental functions of program.
     * Sets start-up look and listens thread to calls methods.
     */

    @FXML
    public void initialize() {
        controller = this;

        rocketAnimation = new RocketAnimation(mainGridPane, minorGridPane, groundZeroPane);
        rocketAnimation.gameLoop();
        rocketAnimation.setRocketSpeed(3);
        AnimationData.setRocketAnimation(rocketAnimation);

        fuelBar.setProgress(1);

        rocketParameters.addListener((ListChangeListener<RocketParameters>) c -> {

            updateLabelsValues();
            updateFuelProgress();
            setThrustOfRocket();
            setRocketImage();

        });

        initializeTable();
        loadJsonToTable();

        thrustSlider.setOnKeyPressed(event -> handleMovementKeys(event));
    }

    /**
     * The method loads Json file witch lists of records
     * and sets its in record table.
     */
    private void loadJsonToTable() {
        File jsonFile = new File(playersJsonPath);
        if (!jsonFile.exists()) {
            System.out.println("File doesn't exists");
        } else {
            players = FileManager.loadFromJson(jsonFile);
        }

        leadersTable.setItems(players);
        sortPlayers();
    }

    /**
     * The method initializes record Table and its look.
     */
    private void initializeTable() {
        String color = "-fx-background-color:  #d0b1b1";

        leadersTable.setStyle(color);
        fuelColumn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
        fuelColumn.setStyle(color);
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        rankColumn.setStyle(color);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        nameColumn.setStyle(color);
    }

    /**
     * The method updates height and velocity labels.
     */
    private void updateLabelsValues() {
        Platform.runLater(() -> {
            if (rocketParameters.size() != 0) {
                double height = rocketParameters.get(rocketParameters.size() - 1).getHeight();
                double velocity = rocketParameters.get(rocketParameters.size() - 1).getVelocity();

                heightLabel.textProperty().set(String.format("%.0f", height));
                velocityLabel.textProperty().set(String.format("%.2f", velocity));
            }
        });
    }

    /**
     * The method updates fuel bar and fuel label.
     */
    private void updateFuelProgress() {
        Platform.runLater(() -> {
            if (rocketParameters.size() != 0) {
                double fuelMass = rocketParameters.get(rocketParameters.size() - 1).getMass() - rocketMass;
                double state = fuelMass / fullTank;

                if (state < 0.001) {
                    state = 0;
                    thrustSlider.setValue(state);
                    thrustSlider.setDisable(true);
                }

                fuelLabel.textProperty().set(String.format("%.0f", state * 100));
                fuelBar.setProgress(state);
            }
        });
    }

    /**
     * The method sets Mi to integrator depending on slider value.
     */
    private void setThrustOfRocket() {
        Platform.runLater(() -> {
            rocketThread.setMi((thrustSlider.getValue() / 100) * fullPower);
        });
    }

    /**
     * The method starts new thread witch calculates euler integrator.
     * Sets thrust slider and fuel bar and supports labels.
     * @param event - ActionEvent which listens that player Click START button.
     */

    @FXML
    void playGameBtn(ActionEvent event) {
        FirstOrderDifferentialEquations rocketODE = new RocketODE(flameVelocity, gravity);
        FirstOrderIntegrator integrator = new EulerIntegrator(dt);

        thrustSlider.setValue(sliderValue);
        powerLabel.setText(Double.toString(sliderValue));
        powerLabel.textProperty().bindBidirectional(thrustSlider.valueProperty(), NumberFormat.getIntegerInstance());

        rocketState = new RocketState(rocketODE, integrator, rocketParameters, rocketName, new double[]{heightStart, velocityStart, rocketMass + fullTank});
        rocketThread.addObserver(rocketState);

        rocketThread.start();

        playBackground.setLayoutX(600);
        addButton.setTranslateX(0);
        nameTextField.setTranslateX(0);
    }

    /**
     * The methods opens new window with lineChart next to Main Window.
     * It draws graph height in function of speed.
     * @param event - ActionEvent which listens that player Click STATISTIC button.
     * @throws Exception - throws exception of load new controller
     */
    @FXML
    void pressStatWindowBtn(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("statisticWindow.fxml"));
        Parent statisticRoot = fxmlLoader.load();
        Stage statisticStage = new Stage();
        statisticStage.setTitle("Chart");
        Scene statisticScene = new Scene(statisticRoot);
        statisticScene.getStylesheets().addAll("styles/chartSymbol.css");
        statisticStage.setScene(statisticScene);
        statisticStage.setResizable(false);

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        statisticStage.setX(5 * primScreenBounds.getWidth() / 8);
        statisticStage.setY(primScreenBounds.getHeight() / 6);

        statisticStage.show();

        StatisticController statisticController = fxmlLoader.getController();
        statisticController.setRocketParameters(rocketParameters);

    }

    /**
     * The method sets image of rocket depending on slider value
     */

    private void setRocketImage() {
        Platform.runLater(() -> {
            rocketImage.setImage(rocketAnimation.getImage((thrustSlider.getValue())));
        });
    }

    /**
     * The method clears arraylists and observablelists of previous game.
     * Starts new thread, resets fuelBar.
     * @param event - ActionEvent which listens that player Click RESTART button.
     */

    @FXML
    void restartGameBtn(ActionEvent event) {
        rocketParameters.clear();
        rocketAnimation = new RocketAnimation(mainGridPane, minorGridPane, groundZeroPane);
        rocketAnimation.gameLoop();
        AnimationData.setRocketAnimation(rocketAnimation);
        rocketThread.removeObserver(rocketState);
        endGameStatusLabel.setTranslateX(-300);
        fuelBar.setProgress(1);
        thrustSlider.setDisable(false);

        playGameBtn(event);
        restartGameBtn.setTranslateX(0);
        playBackground.setLayoutX(600);
        StatisticController.getStatisticSeries().getData().clear();

    }

    /**
     * The method sets EndLayout and values of labels after landing on moon.
     */
    public void onGroundReached() {
        Platform.runLater(() -> {

            setEndWindow();
            finalFuelMass = rocketParameters.get(rocketParameters.size() - 1).getMass() - rocketMass;
            finalSpeed = rocketParameters.get(rocketParameters.size() - 1).getVelocity();

            endFuelLabel.textProperty().set(String.valueOf(df2.format(finalFuelMass) + " kg"));
            endSpeedLabel.textProperty().set(String.valueOf(df2.format(finalSpeed) + "m/s"));

            RocketParameters parameter = rocketParameters.get(rocketParameters.size() - 1);
            if (parameter.getVelocity() >= -2) {
                endGameStatusLabel.textProperty().set("Success");
                addButton.setTranslateX(176);
                nameTextField.setTranslateX(354);
            } else
                endGameStatusLabel.textProperty().set("Failed");
        });
    }

    /**
     * The method adds name and score of player witch successfully landed.
     * @param event - ActionEvent which listens that player Click ADD button.
     */
    @FXML
    void addPlayer(ActionEvent event) {
        String playerName = nameTextField.textProperty().getValue();
        players.add(new Player(playerName, Math.round(finalFuelMass * 100.0) / 100.0));
        sortPlayers();
    }

    /**
     * The method sort scores of players which is better.
     */

    private void sortPlayers() {
        Collections.sort(players);

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setRank(i + 1);
        }
        leadersTable.refresh();
    }

    /**
     * The method sets Layouts of Labels, Buttons and Grid depending on Start or End game.
     */

    public void setEndWindow() {
        playBackground.setLayoutX(0);
        restartGameBtn.setTranslateX(192);
        playGameBtn.setTranslateX(-192);
        endGameStatusLabel.setTranslateX(300);
        mnRocketLabel.setTranslateX(-300);
        endHeadFuelLabel.setTranslateX(223);
        endFuelLabel.setTranslateX(223);
        endHeadSpeedLabel.setTranslateX(299);
        endSpeedLabel.setTranslateX(299);
    }

    /**
     * The method sets value of thrustSlider by pushing button by player.
     * @param event - HeyEvent which listens that player push W,S or arrows UP, DOWN button on keyboard.
     */

    public void handleMovementKeys(KeyEvent event) {

        switch (event.getCode()) {
            case W:
                thrustSlider.setValue(thrustSlider.getValue() + 1);
                break;
            case S:
                thrustSlider.setValue(thrustSlider.getValue() - 1);
                break;
            case UP:
                thrustSlider.setValue(thrustSlider.getValue() + 1);
                break;
            case DOWN:
                thrustSlider.setValue(thrustSlider.getValue() - 1);
                break;
        }
        event.consume();
    }

    /**
     *      * @return object controller of Controller class.
     */

    public static Controller getController() {
        return controller;
    }


    /**
     * THe metod closes the application and saves the lists of players records.
     */
    @FXML
    public void exitApplication() {
        System.out.println("Exit App");
        FileManager.saveToJson(playersJsonPath, players);
    }

}