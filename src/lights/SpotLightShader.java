package lights;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import shaders.ShaderProgram;

/**
 * Created by one on 10/1/16.
 */
public class SpotLightShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/lights/pointLightVertexShader.vert";
    private static final String FRAGMENT_FILE = "src/lights/pointLightFragmentShader.frag";

    private int location_transformationMatrix;
    private int location_cameraMatrix;
    private int location_color;
    private int location_attenuationType;


    public SpotLightShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_cameraMatrix = super.getUniformLocation("cameraMatrix");
        location_color = super.getUniformLocation("color");
        location_attenuationType = super.getUniformLocation("attenuationType");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }

    protected void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    protected void loadCameraMatrix(Matrix4f matrix) {
        super.loadMatrix(location_cameraMatrix, matrix);
    }

    protected void loadColor(Vector3f color) {
        super.loadVector(location_color, color);
    }

    protected void loadAttenuationType(float attenuationType) {
        super.loadFloat(location_attenuationType, attenuationType);
    }



}
