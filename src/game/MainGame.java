package game;

import animations.PlayerAnimation;
import collisions.Collision;
import guis.GuiRenderer;
import guis.GuiTexture;
import guis.fontMeshCreator.FontType;
import guis.fontMeshCreator.GUIText;
import guis.fontRendering.TextMaster;
import gameItems.Player;
import levels.Level;
import org.joml.Vector2f;
import org.joml.Vector3f;
import particles.ParticleBatch;
import particles.ParticleEngine;
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

    private GuiRenderer guiRenderer;
    List<GuiTexture> guis = new ArrayList<GuiTexture>();

    private ParticleBatch particleBatch;
    private ParticleEngine particleEngine;


    public MainGame() {
        renderer = new MasterRenderer();
        camera = new Camera();
        loader = new Loader();
        level = new Level("level.txt");
        player = new Player(new Vector2f(60, 60));
        guiRenderer = new GuiRenderer();

        particleBatch = new ParticleBatch();
        particleEngine = new ParticleEngine();
        particleEngine.addParticleBatch(particleBatch);
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
        guiRenderer.init();

        particleBatch.init(1000, 0.01f, TextureCache.getTexture("/particleAtlas.png", 4));


        FontType font = new FontType(TextureCache.getTexture("/candara.png").getId(), new File("res/candara.fnt"));

        GUIText text = new GUIText("MARIO", 2, font, new Vector2f(0.1f,0.01f), 1f, false);
        text.setColour(1, 1, 1);
        GUIText text2 = new GUIText("0012345", 2, font, new Vector2f(0.1f, 0.05f), 1f, false);
        text2.setColour(1, 1, 1);
        GUIText text3 = new GUIText("WORLD", 2, font, new Vector2f(0.6f, 0.01f), 1f, false);
        text3.setColour(1, 1, 1);
        GUIText text4 = new GUIText("1-1", 2, font, new Vector2f(0.65f, 0.05f), 1f, false);
        text4.setColour(1, 1, 1);
        GUIText text5 = new GUIText("TIME", 2, font, new Vector2f(0.8f, 0.01f), 1f, false);
        text5.setColour(1, 1, 1);
        GUIText text6 = new GUIText("283", 2, font, new Vector2f(0.82f, 0.05f), 1f, false);
        text6.setColour(1, 1, 1);
        GUIText text7 = new GUIText("* 3", 2, font, new Vector2f(0.37f, 0.05f), 1f, false);
        text7.setColour(1, 1, 1);



        GuiTexture gui = new GuiTexture(TextureCache.getTexture("/player.png").getId(), new Vector2f(-0.3f, 0.83f), new Vector2f(0.05f, 0.05f));
        guis.add(gui);

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
            player.animate(PlayerAnimation.GO_LEFT);

        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            player.increasePosition(5*(float)Timer.elapsedTime, 0);
            player.animate(PlayerAnimation.GO_RIGHT);
        } else {
            if (player.isFacingRight()) {
                player.setTextureIndex(0);
            } else {
                player.setTextureIndex(15);
            }

        }
        if (window.isKeyPressed(GLFW_KEY_SPACE)) {
            if (!player.isInAir()) {
                player.jump();
            }
        }

        if (window.isKeyPressed(GLFW_KEY_P)) {
            particleBatch.addParticle(new Vector2f(player.getPosition().x + Player.SIZE/2 - particleBatch.SIZE/2, player.getPosition().y + Player.SIZE/2), new Vector2f(0, 1), new Vector3f(0, 0, 0));
        }

        if (player.isInAir()) {
            player.animate(PlayerAnimation.JUMP);

        }

        player.fall();

        Collision.collideWithLevel(player, level.getLevelData(), particleBatch);

    }

    @Override
    public void update(float interval) {

        particleEngine.update();

        camera.setPosition(new Vector2f(Math.max(300, player.getPosition().x), Math.max(250, player.getPosition().y)));
        camera.update();

    }

    @Override
    public void render(Window window) {

        for (Sprite sprite:sprites) {
            renderer.processSprite(sprite);
        }

        // render sprites
        renderer.render(camera, player);

        // render particles
        particleEngine.render(camera);

        // render gui textures
        guiRenderer.render(guis);

        // render gui texts
        TextMaster.render();

    }

    @Override
    public void cleanUp() {

        loader.cleanUp();
        renderer.cleanUp();
        TextMaster.cleanUp();
        guiRenderer.cleanUp();
        particleBatch.cleanUp();

    }

}
