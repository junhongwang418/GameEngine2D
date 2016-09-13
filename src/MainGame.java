/**
 * Created by one on 9/11/16.
 */
public class MainGame implements IGameLogic {

    private Renderer renderer;
    private Sprite sprite;
    private StaticShader shader;

    public MainGame() {
        renderer = new Renderer();
        sprite = new Sprite(-1, -1, 2, 2);

    }

    @Override
    public void init() throws Exception {

        sprite.init();
        shader = new StaticShader();

    }

    @Override
    public void input(Window window) {

    }

    @Override
    public void update(float interval) {

    }

    @Override
    public void render(Window window) {
        renderer.prepare();
        shader.start();
        renderer.render(sprite);
        shader.stop();
    }

}
