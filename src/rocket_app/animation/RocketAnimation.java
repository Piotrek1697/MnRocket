package rocket_app.animation;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.GridPane;

public class RocketAnimation {

    private AnimationTimer animationTimer;
    private GridPane gridPane1;
    private GridPane gridPane2;
    private double rocketSpeed;

    public RocketAnimation(GridPane gridPane1, GridPane gridPane2) {
        this.gridPane1 = gridPane1;
        this.gridPane2 = gridPane2;

    }


    public void gameLoop() {

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                moveBackground();

            }
        };
        animationTimer.start();
    }


    private void moveBackground() {

        if(getRocketSpeed() > 0) {

            gridPane1.setLayoutY(gridPane1.getLayoutY() + getRocketSpeed());
            gridPane2.setLayoutY(gridPane2.getLayoutY() + getRocketSpeed());

            if (gridPane1.getLayoutY() >= 400) {
                gridPane1.setLayoutY(-400);
            }

            if (gridPane2.getLayoutY() >= 400) {

                gridPane2.setLayoutY(-400);

            }

        }else if(getRocketSpeed() < 0){

            gridPane1.setLayoutY(gridPane1.getLayoutY() + getRocketSpeed());
            gridPane2.setLayoutY(gridPane2.getLayoutY() + getRocketSpeed());

            if (gridPane1.getLayoutY() <= -400) {
                gridPane1.setLayoutY(400);
            }

            if (gridPane2.getLayoutY() <= -400) {

                gridPane2.setLayoutY(400);

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
