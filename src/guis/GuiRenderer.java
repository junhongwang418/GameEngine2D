package guis;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import utils.Loader;
import utils.Maths;

import java.util.List;

/**
 * Created by one on 9/24/16.
 */
public class GuiRenderer {

    private int vaoID;
    private GuiShader shader;

    public GuiRenderer() {

    }

    public void init() {
        float[] positions = { -1, 1, -1, -1, 1, 1, 1, -1 };
        vaoID = Loader.loadToVAO(positions);
        shader = new GuiShader();
    }

    public void render(List<GuiTexture> guis) {
        shader.start();
        GL30.glBindVertexArray(vaoID);
        GL20.glEnableVertexAttribArray(0);
        // render
        for (GuiTexture gui:guis) {
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTexture());
            Matrix4f matrix = Maths.createTransformationMatrix(gui.getPosition(), gui.getScale());
            shader.loadTransformation(matrix);
            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 6);
        }
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
    }

    public void cleanUp() {
        shader.cleanUp();
    }

}
