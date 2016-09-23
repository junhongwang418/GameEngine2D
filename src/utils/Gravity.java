package utils;

/**
 * Created by one on 9/22/16.
 */
public class Gravity {

    public static final float ACCELERATION_G = 2000f;

    public static float getDeltaY(float initialSpeed) {
        float deltaY = (float) (initialSpeed * 1/60 - 0.5*ACCELERATION_G*(1/60)*(1/60));
        return deltaY;
    }

}
