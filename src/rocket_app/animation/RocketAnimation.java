package rocket_app.animation;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;

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
}
