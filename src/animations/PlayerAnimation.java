package animations;

import gameItems.Player;

/**
 * Created by one on 9/18/16.
 */
public class PlayerAnimation extends Animation {

    public static final int GO_RIGHT = 0;
    public static final int GO_LEFT = 1;
    public static final int JUMP = 2;

    public PlayerAnimation(Player player, int textureNumRows, int currentFrame) {
        super(player, textureNumRows, currentFrame);
    }

    @Override
    public void nextFrame(int type) {

        switch (type) {
            case GO_RIGHT:
                if (((Player)sprite).isFacingRight()) {
                    currentFrame = 11;
                    ((Player)sprite).setFacingRight(false);
                }
                if (currentFrame >= 21) {
                    currentFrame = 11;
                }
                ((Player)sprite).setTextureIndex(++currentFrame);
                break;
            case GO_LEFT:
                if (!((Player)sprite).isFacingRight()) {
                    currentFrame = 1;
                    ((Player)sprite).setFacingRight(true);
                }
                if (currentFrame >= 10) {
                    currentFrame = 1;
                }
                ((Player)sprite).setTextureIndex(++currentFrame);
                break;
            case JUMP:
                if (((Player)sprite).isFacingRight()) {
                    if (((Player)sprite).getVelocity().y < 0) {
                        currentFrame = 23;
                    } else {
                        currentFrame = 22;
                    }

                } else {

                    if (((Player)sprite).getVelocity().y < 0) {
                        currentFrame = 28;
                    } else {
                        currentFrame = 27;
                    }

                }

                ((Player)sprite).setTextureIndex(currentFrame);
                break;
            default:
                break;
        }

    }
}
