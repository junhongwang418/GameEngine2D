package gameItems;

import org.joml.Vector2f;
import sprites.Model;
import sprites.Sprite;

/**
 * Created by one on 10/4/16.
 */
public class Castle extends Sprite {

    public static final float SIZE = 200;

    private static final String TEXTURE_FILE_PATH = "/castle.png";

    public Castle(Vector2f position) {
        super(position);
    }

    public void init() throws Exception {
        super.init(new Model(SIZE, SIZE, TEXTURE_FILE_PATH));
    }

}
