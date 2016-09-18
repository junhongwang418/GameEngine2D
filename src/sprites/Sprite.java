package sprites;

import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by one on 9/17/16.
 */
public class Sprite {

    private Vector2f position;

    private Vector3f rotation;

    private Model model;

    private int textureIndex = 0;

    public Sprite(Vector2f position) {
        this.position = position;
        rotation = new Vector3f(0, 0, 0);
    }

    public Sprite(Vector2f position, int textureIndex) {
        this.position = position;
        rotation = new Vector3f(0, 0, 0);
        this.textureIndex = textureIndex;
    }

    public void init(Model model) {

        this.model = model;

    }

    public Model getModel() {
        return model;
    }

    public Vector2f getPosition() {
        return position;
    }

    public float getTextureXOffset() {
        int column = textureIndex%model.getTexture().getNumRows();
        return (float) column/model.getTexture().getNumRows();

    }

    public float getTextureYOffset() {
        int row = textureIndex/model.getTexture().getNumRows();
        return (float) row/model.getTexture().getNumRows();
    }

    public void increasePosition(float dx, float dy) {
        this.position.x += dx;
        this.position.y += dy;
    }

    public void setTextureIndex(int index) {
        this.textureIndex = index;
    }

    public int getTextureIndex() {
        return textureIndex;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
}
