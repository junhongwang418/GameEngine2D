package game;

import animations.PlayerAnimation;
import collisions.Collision;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import gameItems.Player;
import levels.Level;
import org.joml.Vector2f;
import renderers.MasterRenderer;
import sprites.Model;
import sprites.Sprite;
import sprites.TextureCache;
import utils.*;

import java.io.File;
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
        TextMaster.init();


        FontType font = new FontType(TextureCache.getTexture("/arial.png").getId(), new File("res/arial.fnt"));

        String lyrics =
                "I remember when we broke up the first time " +
                "Saying, This is it, I've had enough, cause like " +
                "We hadn't seen each other in a month " +
                "When you said you needed space. (What?) " +
                "Then you come around again and say " +
                " Baby, I miss you and I swear I'm gonna change, trust me. " +
                "Remember how that lasted for a day? " +
                "I say,  I hate you, we break up, you call me, I love you." +
                "Ooh, we called it off again last night" +
                "But ooh, this time I'm telling you, I'm telling you" +
                "We are never ever ever getting back together," +
                "We are never ever ever getting back together," +
                "You go talk to your friends, talk to my friends, talk to me" +
                "But we are never ever ever ever getting back together" +
                "Like, ever..." + "I'm really gonna miss you picking fights" +
                "And me falling for it screaming that I'm right" +
                "And you would hide away and find your peace of mind" +
                "With some indie record that's much cooler than mine" +
                "Ooh, you called me up again tonight" +
                "But ooh, this time I'm telling you, I'm telling you" +
                "We are never, ever, ever, ever getting back together" +
                "We are never, ever, ever, ever getting back together" +
                "You go talk to your friends, talk to my friends, talk to me" +
                "But we are never ever ever ever getting back together";
        
        GUIText text = new GUIText(lyrics, 1, font, new Vector2f(0f,0f), 1f, false);

    }

    @Override
    public void input(Window window) {
        if (window.isKeyPressed(GLFW_KEY_W)) {
            player.increasePosition(0, 0);

        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            player.increasePosition(0, 5*-(float)Timer.elapsedTime);
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            player.increasePosition(-5*(float)Timer.elapsedTime, 0);
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
        TextMaster.render();
    }

    @Override
    public void cleanUp() {
        loader.cleanUp();
        renderer.cleanUp();
        TextMaster.cleanUp();
    }

}
