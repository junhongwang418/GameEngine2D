package lights;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import shaders.ShaderProgram;

/**
 * Created by one on 10/1/16.
 */
public class SpotLightShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/lights/lightVertexShader.vert";
    private static final String FRAGMENT_FILE = "src/lights/spotLightFragmentShader.frag";

    private int location_transformationMatrix;
    private int location_cameraMatrix;
    private int location_color;
    private int location_attenuationType;
    private int location_coneAngle;
    private int location_direction;
    private int location_spotLightPosition;


    public SpotLightShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_cameraMatrix = super.getUniformLocation("cameraMatrix");
        location_color = super.getUniformLocation("color");
        location_attenuationType = super.getUniformLocation("attenuationType");
        location_coneAngle = super.getUniformLocation("coneAngle");
        location_direction = super.getUniformLocation("direction");
        location_spotLightPosition = super.getUniformLocation("spotLightPosition");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }

    protected void loadColor(Vector3f color) {
        super.loadVector(location_color, color);
    }

    protected void loadAttenuationType(float attenuationType) {
        super.loadFloat(location_attenuationType, attenuationType);
    }

    protected void loadConeAngle(float coneAngle) {
        super.loadFloat(location_coneAngle, coneAngle);
    }

    protected void loadDirection(Vector2f direction) {
        super.load2DVector(location_direction, direction);
    }

    protected void loadSpotLightPosition(Vector2f position) {
        super.load2DVector(location_spotLightPosition, position);
    }

    protected void loadCameraMatrix(Matrix4f matrix) {
        super.loadMatrix(location_cameraMatrix, matrix);
    }

    protected void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }
}
