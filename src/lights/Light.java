package lights;

import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by one on 9/30/16.
 */
public class Light {


    public static final int LINEAR_ATTENUATION = 1;
    public static final int INVERSE_LINEAR_ATTENUATION = 2;

    protected Vector2f position;

    protected Vector3f color;

    protected int attenuationType = 0;

    public Light(Vector2f position, Vector3f color) {
        this.position = position;
        this.color = color;
    }

    public Light(Vector2f position, Vector3f color, int attenuationType) {
        this.position = position;
        this.color = color;
        this.attenuationType = attenuationType;
    }

    public void increasePosition(float dx, float dy) {
        position.x += dx;
        position.y += dy;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public int getAttenuationType() {
        return attenuationType;
    }

    public void setAttenuationType(int attenuationType) {
        this.attenuationType = attenuationType;
    }
}
