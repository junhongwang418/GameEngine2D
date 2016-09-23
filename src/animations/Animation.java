package animations;

import sprites.Sprite;

/**
 * Created by one on 9/18/16.
 */
public abstract class Animation {

    protected int textureNumRows;

    protected int currentFrame;

    protected Sprite sprite;

    public Animation(Sprite sprite, int textureNumRows, int currentFrame) {
        this.sprite = sprite;
        this.textureNumRows = textureNumRows;
        this.currentFrame = currentFrame;
    }

    public abstract void nextFrame(int type);


}
