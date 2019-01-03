package rocket_app.animation;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;

public class RocketAnimation {


    private GridPane groundZeroPane;
    private GridPane mainGridPane;
    private GridPane minorGridPane;
    private double rocketSpeed;
    private double rocketHeight = 1;
    private AnimationTimer animationTimer;
    Image image;


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

        mainGridPane.setLayoutY(mainGridPane.getLayoutY() + getRocketSpeed());
        minorGridPane.setLayoutY(minorGridPane.getLayoutY() + getRocketSpeed());

        if (getRocketSpeed() > 0) {


            if (mainGridPane.getLayoutY() >= 400) {
                mainGridPane.setLayoutY(-400);
            }

            if (minorGridPane.getLayoutY() >= 400) {

                minorGridPane.setLayoutY(-400);

            }

        } else if (getRocketSpeed() < 0) {


            if (mainGridPane.getLayoutY() <= -400) {
                mainGridPane.setLayoutY(400);
            }

            if (minorGridPane.getLayoutY() <= -400) {

                minorGridPane.setLayoutY(400);

            }

        } else {

            System.out.println("Velocity 0");

        }
    }

    public void setRocketSpeed(double rocketSpeed) {
        this.rocketSpeed = rocketSpeed;
    }

    public double getRocketSpeed() {

        return rocketSpeed;

    }

    public void setRocketHeight(double rocketHeight) {
        this.rocketHeight = rocketHeight;
    }

    public double getRocketHeight() {
        return rocketHeight;
    }

    private void moveGroundZero() {

        if (getRocketHeight() == 0 && getRocketSpeed() < -0.2) {
            stopBackground();
            System.out.println("Rocked Crashed");
        } else if (getRocketHeight() == 0 && getRocketSpeed() > -0.2) {
            System.out.println("Landed Succesfully");
            stopBackground();
        }

        groundZeroPane.setLayoutY(getRocketHeight() * 10);
        //groundZeroPane.setLayoutY(getRocketHeight()*(Math.abs(getRocketSpeed()))+10);

    }

    private void stopBackground() {
        mainGridPane.setLayoutY(0);
        minorGridPane.setLayoutY(-400);
        animationTimer.stop();
    }

    public Image getImage(double power){


            image = new Image(getClass().getClassLoader().getResource("images/rockets/mainRocket.png").toString());

            if(power > 0)
                image = new Image(getClass().getClassLoader().getResource("images/rockets/mainRocket1_10.png").toString());

            if(power > 10)
                image = new Image(getClass().getClassLoader().getResource("images/rockets/mainRocket10_20.png").toString());

            if(power > 20)
                image = new Image(getClass().getClassLoader().getResource("images/rockets/mainRocket20_30.png").toString());

            if(power > 30)
               image = new Image(getClass().getClassLoader().getResource("images/rockets/mainRocket30_40.png").toString());

            if(power >40)
                image = new Image(getClass().getClassLoader().getResource("images/rockets/mainRocket40_50.png").toString());

            if(power > 50)
                image = new Image(getClass().getClassLoader().getResource("images/rockets/mainRocket50_60.png").toString());

            if(power > 60)
               image = new Image(getClass().getClassLoader().getResource("images/rockets/mainRocket60_70.png").toString());

            if(power > 70)
            image = new Image(getClass().getClassLoader().getResource("images/rockets/mainRocket70_80.png").toString());

            if(power > 80)
            image = new Image(getClass().getClassLoader().getResource("images/rockets/mainRocket80_90.png").toString());

            if(power > 90)
            image = new Image(getClass().getClassLoader().getResource("images/rockets/mainRocket90_100.png").toString());

        return image;
    }
}