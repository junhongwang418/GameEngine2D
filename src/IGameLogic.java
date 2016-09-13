/**
 * Created by one on 9/11/16.
 */
public interface IGameLogic {

    void init() throws Exception;

    void input(Window window);

    void update(float interval);

    void render(Window window);

}
