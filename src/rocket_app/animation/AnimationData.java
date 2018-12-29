package rocket_app.animation;

import rocket_app.rocket.RocketParameters;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AnimationData {

    private double velocity;
    private ArrayList<RocketParameters> rocketParametersEverySecond = new ArrayList<>();
    private static RocketAnimation rocketAnimation;


    public ArrayList<RocketParameters> getRocketParametersEverySecond() {
        return rocketParametersEverySecond;
    }

    public void setRocketParametersEverySecond(ArrayList<RocketParameters> rocketParametersEverySecond) {
        this.rocketParametersEverySecond = rocketParametersEverySecond;
        getVelocityFromArray();
    }

    public static void setRocketAnimation(RocketAnimation rocketAnimation) {
        AnimationData.rocketAnimation = rocketAnimation;
    }

    private void getVelocityFromArray() {

        for (int i = 0; i <= rocketParametersEverySecond.size() - 1; i++) {

            try {
                TimeUnit.MILLISECONDS.sleep(100);
                velocity = rocketParametersEverySecond.get(i).getVelocity();
                System.out.println("Velocity " + velocity);
                System.out.println(rocketParametersEverySecond.get(i).getHeight());
                setVelocityRatio();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setVelocityRatio() {
        // 100 m/s is equal 10px per frame
        velocity = velocity / 10;
        setRealRocketSpeed(velocity);
    }


    private void setRealRocketSpeed(double velocity) {
        rocketAnimation.setRocketSpeed(velocity);
    }

}
