package rocket_app.animation;

import rocket_app.rocket.RocketParameters;

import java.util.concurrent.TimeUnit;

public class AnimationData {

    private double velocity;
    private double height;
    private static RocketAnimation rocketAnimation;
    private RocketParameters rocketParameter;


    public RocketParameters getRocketParameter() {
        return rocketParameter;
    }

    public void setRocketParameter(RocketParameters rocketParameter) {
        this.rocketParameter = rocketParameter;
        setVelocityRatio();
        setHeightRatio();
    }

    public static void setRocketAnimation(RocketAnimation rocketAnimation) {
        AnimationData.rocketAnimation = rocketAnimation;
    }


    private void setVelocityRatio() {
        // 100 m/s is equal 10px per frame
        velocity = rocketParameter.getVelocity()/10;
        setRealRocketSpeed(velocity);
    }


    private void setRealRocketSpeed(double velocity) {
        rocketAnimation.setRocketSpeed(velocity);
    }

    private void setHeightRatio() {
        height = rocketParameter.getHeight()/10;
        setRealRocketHeight(height);
    }

    private void setRealRocketHeight(double height) {
        rocketAnimation.setRocketHeight(height);
    }


}
