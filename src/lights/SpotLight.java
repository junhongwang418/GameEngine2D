package lights;


import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by one on 10/1/16.
 */
public class SpotLight extends Light {

    private Vector2f direction;

    private float coneAngle;

    public SpotLight(Vector2f position, Vector3f color, Vector2f direction, float coneAngle) {
        super(position, color);
        this.direction = direction;
        this.coneAngle = coneAngle;
    }


    public Vector2f getDirection() {
        return direction;
    }

    public void setDirection(Vector2f direction) {
        this.direction = direction;
    }

    public float getConeAngle() {
        return coneAngle;
    }

    public void setConeAngle(float coneAngle) {
        this.coneAngle = coneAngle;
    }


}
