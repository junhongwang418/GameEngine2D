package particles;

import org.joml.Vector2f;
import org.joml.Vector3f;
import sprites.Texture;
import utils.Timer;

/**
 * Created by one on 9/25/16.
 */
public class Particle {

    private Vector2f position;
    private Vector2f velocity;
    private Vector3f color;
    private float life;

    public Particle() {
        position = new Vector2f(0, 0);
        velocity = new Vector2f(0, 0);
        life = 0;
    }


    public void update() {
        position.x += velocity.x * Timer.elapsedTime;
        position.y += velocity.y * Timer.elapsedTime;
    }

    public void decay(float decayRate) {
        life -= decayRate * Timer.elapsedTime;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }


}
