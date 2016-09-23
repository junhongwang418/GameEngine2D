package game;

import animations.PlayerAnimation;
import collisions.Collision;
import gameItems.Player;
import levels.Level;
import org.joml.Vector2f;
import renderers.MasterRenderer;
import sprites.Model;
import sprites.Sprite;
import utils.*;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by one on 9/17/16.
 */
public class MainGame implements IGameLogic {

    private MasterRenderer renderer;
    private Camera camera;
    private List<Sprite> sprites = new ArrayList<Sprite>();


    private Loader loader;
    private Level level;
    private Player player;


    public MainGame() {
        renderer = new MasterRenderer();
        camera = new Camera();
        loader = new Loader();
        level = new Level("level.txt");
        player = new Player(new Vector2f(60, 60));

    }

    @Override
    public void init(Window window) throws Exception {

        renderer.init();
        camera = new Camera();
        camera.init(window.getWidth(), window.getHeight());
        Model model = new Model(20, 20, "/blocks.png", 8);
        level.init(sprites);
        player.init();

    }

    @Override
    public void input(Window window) {
        if (window.isKeyPressed(GLFW_KEY_W)) {
            player.increasePosition(0, 5*(float)Timer.elapsedTime);

        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            player.increasePosition(0, 5*-(float)Timer.elapsedTime);
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            player.increasePosition(5*-(float)Timer.elapsedTime, 0);
            player.animate(PlayerAnimation.GO_RIGHT);

        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            player.increasePosition(5*(float)Timer.elapsedTime, 0);
            player.animate(PlayerAnimation.GO_LEFT);
        } else {
            if (player.isFacingRight()) {
                player.setTextureIndex(0);
            } else {
                player.setTextureIndex(11);
            }

        }
        if (window.isKeyPressed(GLFW_KEY_SPACE)) {
            if (!player.isInAir()) {
                player.jump();
            }
        }

        if (player.isInAir()) {
            player.animate(PlayerAnimation.JUMP);
        }


        player.fall();

        Collision.collideWithLevel(player, level.getLevelData());




    }

    @Override
    public void update(float interval) {

        camera.setPosition(new Vector2f(player.getPosition().x, 200));
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
