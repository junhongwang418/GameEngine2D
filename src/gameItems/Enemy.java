package gameItems;

import animations.EnemyAnimation;
import org.joml.Vector2f;
import sprites.Model;
import sprites.Sprite;
import utils.Gravity;
import utils.Timer;

import java.util.Random;

/**
 * Created by one on 9/23/16.
 */
public class Enemy extends Sprite {

    private Vector2f velocity;

    private boolean isFacingRight = true;

    private boolean isInAir = false;

    public static final float WIDTH = 20;

    public static final float HEIGHT = 20;

    private static final int TEXTURE_NUM_ROWS = 8;

    private static final String TEXTURE_FILE_PATH = "/enemy.png";

    private float moveSpeed = 1;

    private float elapsedTime = 0;

    private final float MAX_ELAPSED_TIME = 100; // frame

    private final Random AI = new Random();

    private EnemyAnimation animation;

    // A.I.
    public void move() {

        if (elapsedTime == 0) {

            int number = AI.nextInt(2);

            if (number == 0) {
                isFacingRight = true;
            } else {
                isFacingRight = false;
            }

            if (isFacingRight) {
                velocity.x = moveSpeed;
            } else {
                velocity.x = -moveSpeed;
            }

        }

        increasePosition(velocity.x * (float)Timer.elapsedTime, 0);

        elapsedTime = (elapsedTime < MAX_ELAPSED_TIME) ? elapsedTime += Timer.elapsedTime : 0;

        if ((int)elapsedTime % 30 == 0) {
            animate();
        }


    }

    public void animate() {

        if (textureIndex == EnemyAnimation.RIGHT_FOOT) {
            animation.nextFrame(EnemyAnimation.LEFT_FOOT);
        } else {
            animation.nextFrame(EnemyAnimation.RIGHT_FOOT);
        }
    }

    public void fall() {

        increasePosition(0, Gravity.getDeltaY(velocity.y));

        velocity.y -= Gravity.ACCELERATION_G*(float)1/60;

    }

    public Enemy(Vector2f position, int index) {
        super(position, index);
        velocity = new Vector2f(0, 0);
        animation = new EnemyAnimation(this, TEXTURE_NUM_ROWS, 0);

    }

    public void init() throws Exception {
        super.init(new Model(WIDTH, HEIGHT, TEXTURE_FILE_PATH, TEXTURE_NUM_ROWS));
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }

    public boolean isInAir() {
        return isInAir;
    }

    public void setInAir(boolean inAir) {
        isInAir = inAir;
    }
}
