package renderers;

import gameItems.Player;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import shaders.StaticShader;
import sprites.Model;
import sprites.Sprite;
import utils.Maths;

import java.util.List;
import java.util.Map;

/**
 * Created by one on 9/17/16.
 */
public class Renderer {

    private StaticShader shader;

    public void init() {
        shader = new StaticShader();
    }

    public void prepare() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void render(Map<Model, List<Sprite>> sprites) {
        for (Model model:sprites.keySet()) {
            prepareModel(model);
            List<Sprite> batch = sprites.get(model);
            for (Sprite sprite:batch) {
                prepareInstance(sprite);
                GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
            }
            unbindModel();
        }

    }

    public void render(Player player) {
        prepareModel(player.getModel());
        prepareInstance(player);
        GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
        unbindModel();
    }

    public void prepareModel(Model model) {
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        shader.loadNumRows(model.getTexture().getNumRows());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getId());
    }

    public void unbindModel() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    public void prepareInstance(Sprite sprite) {
        shader.loadTransformationMatrix(Maths.createTransformationMatrix(sprite.getPosition(), sprite.getRotation(), 0));
        shader.loadOffset(sprite.getTextureXOffset(), sprite.getTextureYOffset());
    }

    public StaticShader getShader() {
        return shader;
    }

    public void cleanUp() {
        shader.cleanUp();
    }

}
