package rocket_app.rocket;

/**
 * Class created to storing parameters that describes rocket state.
 */
public class RocketParameters {
    private double height;
    private double velocity;
    private double mass;

    /**
     * @param height - meters above the moon [m]
     * @param velocity - [m/s]
     * @param mass - rocket + tank with fuel mass [kg]
     */
    public RocketParameters(double height, double velocity, double mass) {
        this.height = height;
        this.velocity = velocity;
        this.mass = mass;
    }

    /**
     * @return height of rocket above the moon [m]
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return velocity of rocket [m/s]
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * @return mass of rocket = rocket + fuel in tank mass [kg]
     */
    public double getMass() {
        return mass;
    }

    @Override
    public String toString() {
        return height+";"+velocity+";"+mass;
    }
}
