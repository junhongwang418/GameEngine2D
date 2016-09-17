import java.util.HashMap;
import java.util.Map;

/**
 * Created by one on 9/15/16.
 */
public class TextureCache {

    private static Map<String, Texture> textureMap = new HashMap<String, Texture>();

    public static Texture getTexture(String texturePath) throws Exception {

        // look up the texture and see if its in the map
        Texture batch = textureMap.get(texturePath);
        if (batch == null) {
            Texture newBatch = new Texture(texturePath);
            textureMap.put(texturePath, newBatch);
            return newBatch;
        } else {
            return batch;
        }
    }

}
