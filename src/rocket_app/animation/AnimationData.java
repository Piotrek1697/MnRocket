package rocket_app.animation;

import rocket_app.rocket.RocketParameters;

import java.util.concurrent.TimeUnit;

/**
 * Class supports data which are necessary to animating game.
 */

public class AnimationData {

    private static RocketAnimation rocketAnimation;
    private RocketParameters rocketParameter;

    /**
     * The method sets rocketParameters object.
     * @param rocketParameter - object of RocketParameters class.
     */
    public void setRocketParameter(RocketParameters rocketParameter) {
        this.rocketParameter = rocketParameter;
        setVelocityRatio();
        setHeightRatio();
    }

    /**
     * The method sets rocketAnimation object.
     * @param rocketAnimation - object of rocketAnimation class.
     */
    public static void setRocketAnimation(RocketAnimation rocketAnimation) {
        AnimationData.rocketAnimation = rocketAnimation;

    }

    /**
     * The method converts value of Velocity for animation in scale.
     */
    private void setVelocityRatio() {
        // 100 m/s is equal 10px per frame
        double velocity = rocketParameter.getVelocity() / 10;
        setRealRocketSpeed(velocity);
    }

    /**
     * The method sets RocketSpeed.
     * @param velocity - double value of velocity
     */
    private void setRealRocketSpeed(double velocity) {
        rocketAnimation.setRocketSpeed(velocity);
    }

    /**
     * The method converts value of Height for animation in scale.
     */
    private void setHeightRatio() {
        double height = rocketParameter.getHeight() / 10;
        setRealRocketHeight(height);
    }

    /**
     * The method sets RocketHeight.
     * @param height - double value of height
     */
    private void setRealRocketHeight(double height) {
        rocketAnimation.setRocketHeight(height);
    }

}
