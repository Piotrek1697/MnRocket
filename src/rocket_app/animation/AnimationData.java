package rocket_app.animation;

import rocket_app.rocket.RocketParameters;

import java.util.concurrent.TimeUnit;

public class AnimationData {

    private double velocity;
    private static RocketAnimation rocketAnimation;
    private RocketParameters rocketParameter;


    public RocketParameters getRocketParameter() {
        return rocketParameter;
    }

    public void setRocketParameter(RocketParameters rocketParameter) {
        this.rocketParameter = rocketParameter;
    }

    public static void setRocketAnimation(RocketAnimation rocketAnimation) {
        AnimationData.rocketAnimation = rocketAnimation;
    }

    /*private void getVelocityFromArray() {

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
    }*/


    private void setVelocityRatio() {
        // 100 m/s is equal 10px per frame
        velocity = velocity / 10;
        setRealRocketSpeed(velocity);
    }


    private void setRealRocketSpeed(double velocity) {
        rocketAnimation.setRocketSpeed(velocity);
    }

}
