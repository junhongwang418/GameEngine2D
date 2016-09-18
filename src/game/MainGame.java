package game;

import gameItems.Player;
import levels.Level;
import org.joml.Vector2f;
import org.joml.Vector3f;
import renderers.MasterRenderer;
import sprites.Model;
import sprites.Sprite;
import utils.Camera;
import utils.Loader;
import utils.Timer;
import utils.Window;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;

/**
 * Created by one on 9/17/16.
 */
public class MainGame implements IGameLogic {

    private MasterRenderer renderer;
    private Camera camera;
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private Sprite sprite;
    private Loader loader;
    private Level level;
    private Player player;

    public MainGame() {
        renderer = new MasterRenderer();
        camera = new Camera();
        loader = new Loader();
        sprite = new Sprite(new Vector2f(0, 0));
        sprites.add(sprite);
        level = new Level("level.txt");
        player = new Player(new Vector2f(0, 0));

    }

    @Override
    public void init(Window window) throws Exception {

        renderer.init();
        camera = new Camera();
        camera.init(window.getWidth(), window.getHeight());
        Model model = new Model(50, 50, "/faces.png", 2);
        sprite.init(model);
        level.init(sprites, model);
        player.init();
    }

    @Override
    public void input(Window window) {
        if (window.isKeyPressed(GLFW_KEY_W)) {
            player.increasePosition(0, 0.1f*(float)Timer.delta);

        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            player.increasePosition(0, 0.1f*-(float)Timer.delta);
        } else if (window.isKeyPressed(GLFW_KEY_A)) {
            player.increasePosition(0.1f*-(float)Timer.delta, 0);
            int frame = player.getTextureIndex();
            if (player.isFacingRight()) {
                frame = 11;
                player.setFacingRight(false);
            }
            if (frame >= 21) {
                frame = 11;
            }
            player.setTextureIndex(++frame);

        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            player.increasePosition(0.1f*(float)Timer.delta, 0);
            player.setRotation(new Vector3f(0, 0, 0));
            int frame = player.getTextureIndex();
            if (!player.isFacingRight()) {
                frame = 1;
                player.setFacingRight(true);
            }
            if (frame >= 10) {
                frame = 1;
            }
            player.setTextureIndex(++frame);
        } else {
            if (player.isFacingRight()) {
                player.setTextureIndex(0);
            } else {
                player.setTextureIndex(11);
            }

        }

    }

    @Override
    public void update(float interval) {


        camera.setPosition(player.getPosition());
        camera.update();

    }

    @Override
    public void render(Window window) {
        for (Sprite sprite:sprites) {
            renderer.processSprite(sprite);
        }

        renderer.render(camera, player);
    }

    @Override
    public void cleanUp() {
        loader.cleanUp();
        renderer.cleanUp();
    }

}
