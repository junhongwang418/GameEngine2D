package utils;

/**
 * Created by one on 9/17/16.
 */
public class Timer {


    public static long lastTime = System.nanoTime();

    public static double delta = 0.0;

    public static double nanoSeconds = 1000000000.0 / 60.0;

    public static long timer = System.currentTimeMillis();

    public static int updates = 0;

    public static int frames = 0;

    public static long getTime() {
        return System.nanoTime();
    }

    public static double elapsedTime = 0;

    public static long lastFrame = getTime();

    public static int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;
        return delta;
    }


}
