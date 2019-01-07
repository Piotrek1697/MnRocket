package rocket_app.animation;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import rocket_app.rocket.RocketImage;

public class RocketAnimation {


    private GridPane groundZeroPane;
    private GridPane mainGridPane;
    private GridPane minorGridPane;
    private double rocketSpeed;
    private double rocketHeight = 1;
    private AnimationTimer animationTimer;


    public RocketAnimation(GridPane mainGridPane, GridPane minorGridPane, GridPane groundZeroPane) {
        this.mainGridPane = mainGridPane;
        this.minorGridPane = minorGridPane;
        this.groundZeroPane = groundZeroPane;

    }


    public void gameLoop() {

        animationTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                moveBackground();
                moveGroundZero();
            }
        };
        animationTimer.start();
    }

    private void moveBackground() {

        mainGridPane.setLayoutY(mainGridPane.getLayoutY() + rocketSpeed);
        minorGridPane.setLayoutY(minorGridPane.getLayoutY() + rocketSpeed);

        if (rocketSpeed > 0) {

            if (mainGridPane.getLayoutY() >= 400)
                mainGridPane.setLayoutY(-400);

            if (minorGridPane.getLayoutY() >= 400)
                minorGridPane.setLayoutY(-400);

        } else if (rocketSpeed < 0) {

            if (mainGridPane.getLayoutY() <= -400)
                mainGridPane.setLayoutY(400);


            if (minorGridPane.getLayoutY() <= -400)
                minorGridPane.setLayoutY(400);

        } else
            System.out.println("Velocity 0");

    }

    public void setRocketSpeed(double rocketSpeed) {
        this.rocketSpeed = rocketSpeed;
    }

    public void setRocketHeight(double rocketHeight) {
        this.rocketHeight = rocketHeight;
    }

    private void moveGroundZero() {

        if (rocketHeight == 0 && rocketSpeed < -0.2) {
            System.out.println("Rocked Crashed");
            stopBackground();
        } else if (rocketHeight == 0 && rocketSpeed > -0.2) {
            System.out.println("Landed Succesfully");
            stopBackground();
        }

        groundZeroPane.setLayoutY(rocketHeight * 10);
    }

    private void stopBackground() {
        mainGridPane.setLayoutY(0);
        minorGridPane.setLayoutY(-400);
        animationTimer.stop();
    }

    public Image getImage(double power){

        Image mainRocket = RocketImage.ROCKET.getImage();

            if(power > 0)
                mainRocket = RocketImage.ROCKET_1_10.getImage();

            if(power > 10)
                mainRocket = RocketImage.ROCKET_10_20.getImage();

            if(power > 20)
                mainRocket = RocketImage.ROCKET_20_30.getImage();

            if(power > 30)
               mainRocket = RocketImage.ROCKET_30_40.getImage();

            if(power >40)
                mainRocket = RocketImage.ROCKET_40_50.getImage();

            if(power > 50)
                mainRocket = RocketImage.ROCKET_50_60.getImage();

            if(power > 60)
               mainRocket = RocketImage.ROCKET_60_70.getImage();

            if(power > 70)
            mainRocket = RocketImage.ROCKET_70_80.getImage();

            if(power > 80)
            mainRocket = RocketImage.ROCKET_80_90.getImage();

            if(power > 90)
            mainRocket = RocketImage.ROCKET_90_100.getImage();

        return mainRocket;
    }
}