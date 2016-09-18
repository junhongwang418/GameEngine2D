package game;

import utils.Timer;
import utils.Window;

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
//        float elapsedTime;
//        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;
//
//        boolean running = true;
//        while (running && !window.windowShouldClose()) {
//            elapsedTime = timer.getElapsedTime();
//            accumulator += elapsedTime;
//
//            input();
//
//            while (accumulator >= interval) {
//                update(interval);
//                accumulator -= interval;
//            }
//
//            render();
//
//        }
        while (!window.windowShouldClose()) {
            long now = Timer.getTime();
            Timer.delta += (now - Timer.lastTime) / Timer.nanoSeconds;
            Timer.lastTime = now;

            input();

            if (Timer.delta >= 1.0) {
                update(interval);
                Timer.updates++;
                Timer.delta--;
            }

            render();

            Timer.frames++;

            if (System.currentTimeMillis() -Timer.timer > 1000) {
                Timer.timer += 1000;
                System.out.println(Timer.updates + " ups, " + Timer.frames + " fps");
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
