package rocket_app.rocket;

import javafx.scene.image.Image;

public enum RocketImage {
    ROCKET(new Image(RocketImage.class.getResourceAsStream("/images/rockets/mainRocket.png"))),
    ROCKET_1_10(new Image(RocketImage.class.getResourceAsStream("/images/rockets/mainRocket1_10.png"))),
    ROCKET_10_20(new Image(RocketImage.class.getResourceAsStream("/images/rockets/mainRocket10_20.png"))),
    ROCKET_20_30(new Image(RocketImage.class.getResourceAsStream("/images/rockets/mainRocket20_30.png"))),
    ROCKET_30_40(new Image(RocketImage.class.getResourceAsStream("/images/rockets/mainRocket30_40.png"))),
    ROCKET_40_50(new Image(RocketImage.class.getResourceAsStream("/images/rockets/mainRocket40_50.png"))),
    ROCKET_50_60(new Image(RocketImage.class.getResourceAsStream("/images/rockets/mainRocket50_60.png"))),
    ROCKET_60_70(new Image(RocketImage.class.getResourceAsStream("/images/rockets/mainRocket60_70.png"))),
    ROCKET_70_80(new Image(RocketImage.class.getResourceAsStream("/images/rockets/mainRocket70_80.png"))),
    ROCKET_80_90(new Image(RocketImage.class.getResourceAsStream("/images/rockets/mainRocket80_90.png"))),
    ROCKET_90_100(new Image(RocketImage.class.getResourceAsStream("/images/rockets/mainRocket90_100.png")));

    private final Image image;


    RocketImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
