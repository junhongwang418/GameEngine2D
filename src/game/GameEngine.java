package game;

import utils.Timer;
import utils.Window;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by one on 9/17/16.
 */
public class GameEngine implements Runnable {

    public static final int TARGET_UPS = 30;

    private final Window window;

    private final Thread gameLoopThread;

    private final Timer timer;

    private final IGameLogic gameLogic;

    public GameEngine(String title, int width, int height, IGameLogic gameLogic) throws Exception {
        this.gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
        this.window = new Window(title, width, height);
        this.gameLogic = gameLogic;
        this.timer = new Timer();
    }

    public void start() {
        gameLoopThread.run();
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void init() throws Exception {
        window.init();
//        timer.init();
        gameLogic.init(window);
    }

    protected void gameLoop() {

        float interval = 1f / TARGET_UPS;
        while (!window.windowShouldClose()) {
            long now = Timer.getTime();

            Timer.elapsedTime = (now - Timer.lastTime) / Timer.nanoSeconds;
            Timer.delta += Timer.elapsedTime;
            Timer.lastTime = now;

            input();

            if (Timer.delta >= 1.0) {
                update(interval);
                Timer.updates++;
                Timer.delta--;
            }

            render();

            Timer.frames++;

            if (System.currentTimeMillis() - Timer.timer > 1000) {
                Timer.timer += 1000;
                glfwSetWindowTitle(window.getWindowHandle(), "Game: "+Timer.updates + " ups | " + Timer.frames + " fps");
                Timer.updates = 0;
                Timer.frames = 0;
            }
        }
    }

    protected void input() {
        gameLogic.input(window);
    }

    protected void update(float interval) {
        gameLogic.update(interval);
    }

    protected void render() {
        gameLogic.render(window);
        window.update();
    }

}
