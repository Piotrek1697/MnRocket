package rocket_app.animation;

import rocket_app.rocket.RocketParameters;

import java.util.ArrayList;

public class AnimationData {

    private ArrayList<RocketParameters> rocketParametersEverySecond = new ArrayList<>();
    private static RocketAnimation rocketAnimation;


    public ArrayList<RocketParameters> getRocketParametersEverySecond() {
        return rocketParametersEverySecond;
    }

    public void setRocketParametersEverySecond(ArrayList<RocketParameters> rocketParametersEverySecond) {
        this.rocketParametersEverySecond = rocketParametersEverySecond;
    }

    public static void setRocketAnimation(RocketAnimation rocketAnimation) {
        AnimationData.rocketAnimation = rocketAnimation;
    }
}
