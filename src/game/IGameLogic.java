package game;

import utils.Window;

/**
 * Created by one on 9/17/16.
 */
public interface IGameLogic {

    void init(Window window) throws Exception;

    void input(Window window);

    void update(float interval);

    void render(Window window);

    void cleanUp();

}
