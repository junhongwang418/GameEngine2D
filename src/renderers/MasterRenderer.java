package renderers;

import gameItems.Player;
import sprites.Model;
import sprites.Sprite;
import utils.Camera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by one on 9/17/16.
 */
public class MasterRenderer {

    private Renderer renderer;

    private Map<Model, List<Sprite>> sprites = new HashMap<Model, List<Sprite>>();

    public MasterRenderer() {
        renderer = new Renderer();
    }

    public void init() {
        renderer.init();
    }

    public void render(Camera camera, Player player) {
//        System.out.println(sprites.size());
        renderer.prepare();
        renderer.getShader().start();
        renderer.getShader().loadCameraMatrix(camera.getCameraMatrix());
        renderer.render(sprites);
        renderer.render(player);
        renderer.getShader().stop();
        sprites.clear();
    }

    public void processSprite(Sprite sprite) {
        Model spriteModel = sprite.getModel();
        List<Sprite> batch = sprites.get(spriteModel);
        if (batch != null) {
            batch.add(sprite);
        } else {
            List<Sprite> newBatch = new ArrayList<Sprite>();
            newBatch.add(sprite);
            sprites.put(spriteModel, newBatch);
        }
    }

    public void cleanUp() {
        renderer.cleanUp();
    }

}
