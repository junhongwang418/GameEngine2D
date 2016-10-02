package lights;

import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by one on 10/1/16.
 */
public class PointLight extends Light {

    private float size;

    public PointLight(Vector2f position, Vector3f color, float size) {
        super(position, color);
        this.size = size;
    }

    public PointLight(Vector2f position, Vector3f color, int attenuationType, float size) {
        super(position, color, attenuationType);
        this.size = size;
    }


    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
