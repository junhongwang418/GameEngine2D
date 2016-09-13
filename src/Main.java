/**
 * Created by one on 9/11/16.
 */
public class Main {

    public static void main(String[] args) {

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
