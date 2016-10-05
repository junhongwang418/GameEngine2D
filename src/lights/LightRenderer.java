package lights;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import utils.Camera;
import utils.Loader;
import utils.Maths;

import java.util.List;

/**
 * Created by one on 9/30/16.
 */
public class LightRenderer {

    private int vaoID;

    private PointLightShader pointLightShader;

    private SpotLightShader spotLightShader;

    public void init() {
        float[] positions = { -1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, -1 };
        vaoID = Loader.loadToVAO(positions);
        pointLightShader = new PointLightShader();
        spotLightShader = new SpotLightShader();
    }

    public void render(List<PointLight> pointLights, List<SpotLight> spotLights, Camera camera) {

        renderPointLights(pointLights, camera);

        renderSpotLights(spotLights, camera);

    }

    public void renderSpotLights(List<SpotLight> spotLights, Camera camera) {
        spotLightShader.start();

        GL30.glBindVertexArray(vaoID);
        GL20.glEnableVertexAttribArray(0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        // render
        spotLightShader.loadCameraMatrix(camera.getCameraMatrix());
        for (SpotLight spotLight:spotLights) {
            Matrix4f matrix = Maths.createTransformationMatrix(spotLight.getPosition(), spotLight.getSize());
            spotLightShader.loadTransformationMatrix(matrix);
            spotLightShader.loadColor(spotLight.getColor());
            spotLightShader.loadAttenuationType(spotLight.getAttenuationType());
            spotLightShader.loadConeAngle(spotLight.getConeAngle());
            spotLightShader.loadDirection(spotLight.getDirection());
            spotLightShader.loadSpotLightPosition(spotLight.getPosition());
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);

        }
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        spotLightShader.stop();
    }

    public void renderPointLights(List<PointLight> pointLights, Camera camera) {
        pointLightShader.start();
        GL30.glBindVertexArray(vaoID);
        GL20.glEnableVertexAttribArray(0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        // render
        pointLightShader.loadCameraMatrix(camera.getCameraMatrix());
        for (PointLight pointlight:pointLights) {
            Matrix4f matrix = Maths.createTransformationMatrix(pointlight.getPosition(), pointlight.getSize());
            pointLightShader.loadTransformationMatrix(matrix);
            pointLightShader.loadColor(pointlight.getColor());
            pointLightShader.loadAttenuationType(pointlight.getAttenuationType());
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);

        }
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        pointLightShader.stop();

    }

    public void cleanUp() {
        pointLightShader.cleanUp();
    }


}
