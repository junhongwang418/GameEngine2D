package game;

import org.lwjgl.Version;

/**
 * Created by one on 9/17/16.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("**** Hello LWJGL " + Version.getVersion() + "! ****");

        try {
            IGameLogic gameLogic = new MainGame();
            GameEngine gameEng = new GameEngine("GAME", 600, 480, gameLogic);
            gameEng.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
