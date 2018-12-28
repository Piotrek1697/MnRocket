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

        if(getRocketSpeed() > 0) {


            if (mainGridPane.getLayoutY() >= 400) {
                mainGridPane.setLayoutY(-400);
            }

            if (minorGridPane.getLayoutY() >= 400) {

                minorGridPane.setLayoutY(-400);

            }

        }else if(getRocketSpeed() < 0){



            if (mainGridPane.getLayoutY() <= -400) {
                mainGridPane.setLayoutY(400);
            }

            if (minorGridPane.getLayoutY() <= -400) {

                minorGridPane.setLayoutY(400);

            }

        }else {

            System.out.println("Prędkość równa 0");

        }
    }

    public void setRocketSpeed(double rocketSpeed) {
        this.rocketSpeed = rocketSpeed;
    }

    public double getRocketSpeed(){

        return rocketSpeed;

    }

}
