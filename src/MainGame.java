import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by one on 9/11/16.
 */

public class MainGame implements IGameLogic {

    private Renderer renderer;
    private Camera camera;
    private Sprite sprite;
    private StaticShader shader;

    public MainGame() {
        renderer = new Renderer();

        camera = new Camera();


    }

    @Override
    public void init(Window window) throws Exception {


        shader = new StaticShader();
        camera.init(window.getWidth(), window.getHeight());
        sprite = new Sprite(0, 0, window.getWidth()/2, window.getHeight()/2);
        sprite.init("flag.png");

    }

    @Override
    public void input(Window window) {
        if (window.isKeyPressed(GLFW_KEY_W)) {
            camera.increasePosition(0, 1);
        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            camera.increasePosition(0, -1);
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            camera.increasePosition(-1, 0);
        }
        if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.increasePosition(1, 0);
        }
        if (window.isKeyPressed(GLFW_KEY_Q)) {
            camera.increaseScale(0.1f);
        }
        if (window.isKeyPressed(GLFW_KEY_E)) {
            camera.increaseScale(-0.1f);
        }
    }

    @Override
    public void update(float interval) {
        camera.update();
    }

    @Override
    public void render(Window window) {
        renderer.prepare();
        renderer.render(shader, camera, sprite);
    }

}
