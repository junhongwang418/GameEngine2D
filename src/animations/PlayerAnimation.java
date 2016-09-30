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
            case GO_LEFT:
                if (((Player)sprite).isFacingRight()) {
                    currentFrame = 16;
                    ((Player)sprite).setFacingRight(false);
                }
                if (currentFrame <= 10) {
                    currentFrame = 16;
                }
                ((Player)sprite).setTextureIndex(--currentFrame);
                break;
            case GO_RIGHT:
                if (!((Player)sprite).isFacingRight()) {
                    currentFrame = 0;
                    ((Player)sprite).setFacingRight(true);
                }
                if (currentFrame >= 4) {
                    currentFrame = 0;
                }
                ((Player)sprite).setTextureIndex(++currentFrame);
                break;
            case JUMP:
                if (((Player)sprite).isFacingRight()) {
                    if (((Player)sprite).getVelocity().y < 0) {
                        currentFrame = 7;
                    } else {
                        currentFrame = 6;
                    }

                } else {

                    if (((Player)sprite).getVelocity().y < 0) {
                        currentFrame = 8;
                    } else {
                        currentFrame = 9;
                    }

                }

                ((Player)sprite).setTextureIndex(currentFrame);
                break;
            default:
                break;
        }

    }
}
