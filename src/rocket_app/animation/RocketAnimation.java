package rocket_app.animation;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;

public class RocketAnimation {


    private GridPane mainGridPane;
    private GridPane minorGridPane;
    private double rocketSpeed;

    public RocketAnimation(GridPane mainGridPane, GridPane minorGridPane) {
        this.mainGridPane = mainGridPane;
        this.minorGridPane = minorGridPane;

    }


    public void gameLoop() {

        AnimationTimer animationTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                moveBackground();

            }
        };
        animationTimer.start();
    }


    private void moveBackground() {

        mainGridPane.setLayoutY(mainGridPane.getLayoutY() + getRocketSpeed());
        minorGridPane.setLayoutY(minorGridPane.getLayoutY() + getRocketSpeed());

        if (getRocketSpeed() > 0) {


            if (mainGridPane.getLayoutY() >= 500) {
                mainGridPane.setLayoutY(-500);
            }

            if (minorGridPane.getLayoutY() >= 500) {

                minorGridPane.setLayoutY(-500);

            }

        } else if (getRocketSpeed() < 0) {


            if (mainGridPane.getLayoutY() <= -500) {
                mainGridPane.setLayoutY(400);
            }

            if (minorGridPane.getLayoutY() <= -500) {

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
}
