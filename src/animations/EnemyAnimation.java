package animations;

import gameItems.Enemy;

/**
 * Created by one on 10/4/16.
 */
public class EnemyAnimation extends Animation {

    public static final int LEFT_FOOT = 0;
    public static final int RIGHT_FOOT = 1;


    public EnemyAnimation(Enemy enemy, int textureNumRows, int currentFrame) {
        super(enemy, textureNumRows, currentFrame);
    }

    @Override
    public void nextFrame(int type) {
        switch (type) {
            case RIGHT_FOOT:
                currentFrame = RIGHT_FOOT;
                ((Enemy)sprite).setTextureIndex(1);
                break;
            case LEFT_FOOT:
                currentFrame = LEFT_FOOT;
                ((Enemy)sprite).setTextureIndex(0);
                break;
            default:
                break;
        }
    }
}
