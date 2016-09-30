package gameItems;

import org.joml.Vector2f;
import sprites.Model;
import sprites.Sprite;

/**
 * Created by one on 9/18/16.
 */
public class Tile extends Sprite {

    public static final float SIZE = 40;

    private static final String TEXTURE_FILE_PATH = "/struct.png";

    private static final int TEXTURE_NUM_ROWS = 1;

    public Tile(Vector2f position, int index) {
        super(position, index);
    }

    public void init() throws Exception {
        super.init(new Model(SIZE, SIZE, TEXTURE_FILE_PATH, TEXTURE_NUM_ROWS));
    }

}


