package gameItems;

import org.joml.Vector2f;
import sprites.Model;
import sprites.Sprite;
import utils.Loader;

/**
 * Created by one on 9/17/16.
 */
public class Player extends Sprite {

    private boolean isFacingRight = true;

    public Player(Vector2f position) {
        super(position);
    }

    public void init() throws Exception {
        super.init(new Model(50, 50, "/lock_man.png", 8));
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }
}
