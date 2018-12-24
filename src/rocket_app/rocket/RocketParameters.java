package rocket_app.rocket;

public class RocketParameters {

    private double height;
    private double velocity;
    private double mass;

    public RocketParameters(double height, double velocity, double mass) {
        this.height = height;
        this.velocity = velocity;
        this.mass = mass;
    }

    public double getHeight() {
        return height;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getMass() {
        return mass;
    }

    @Override
    public String toString() {
        return height+";"+velocity+";"+mass;
    }
}
