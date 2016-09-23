package gameItems;

import animations.PlayerAnimation;
import org.joml.Vector2f;
import sprites.Model;
import sprites.Sprite;
import utils.Gravity;
import utils.Loader;

/**
 * Created by one on 9/17/16.
 */
public class Player extends Sprite {

    private Vector2f velocity;

    private boolean isFacingRight = true;

    private boolean isInAir = false;

    private PlayerAnimation animation;

    public float jumpPower = 0;

    public static final float SIZE = 30;

    private static final int TEXTURE_NUM_ROWS = 8;

    private static final String TEXTURE_FILE_PATH = "/lock_man.png";

    public Player(Vector2f position) {
        super(position);
        velocity = new Vector2f(0, 0);
        animation = new PlayerAnimation(this, TEXTURE_NUM_ROWS, 0);
    }

    public void init() throws Exception {
        super.init(new Model(SIZE, SIZE, TEXTURE_FILE_PATH, TEXTURE_NUM_ROWS));
    }

    public void jump() {

        velocity.y = 700;
        isInAir = true;

    }

    public void fall() {

        increasePosition(0, Gravity.getDeltaY(velocity.y));

        velocity.y -= Gravity.ACCELERATION_G*(float)1/60;

    }


    public boolean isFacingRight() {
        return isFacingRight;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }

    public void animate(int type) {
        animation.nextFrame(type);
    }

    public boolean isInAir() {
        return isInAir;
    }

    public void setInAir(boolean inAir) {
        isInAir = inAir;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }
}
