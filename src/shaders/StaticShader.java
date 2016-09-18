package shaders;

import org.joml.Matrix4f;
import org.joml.Vector2f;

/**
 * Created by one on 9/17/16.
 */
public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/shaders/vertexShader.vert";
    private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.frag";

    private int location_transformationMatrix;
    private int location_cameraMatrix;
    private int location_numRows;
    private int location_offset;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {

        location_cameraMatrix = super.getUniformLocation("cameraMatrix");
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_numRows = super.getUniformLocation("numRows");
        location_offset = super.getUniformLocation("offset");

    }

    public void loadCameraMatrix(Matrix4f cameraMatrix) {
        loadMatrix(location_cameraMatrix, cameraMatrix);
    }

    public void loadTransformationMatrix(Matrix4f transformationMatrix) {
        loadMatrix(location_transformationMatrix, transformationMatrix);
    }

    public void loadNumRows(int numRows) {
        super.loadFloat(location_numRows, numRows);
    }

    public void loadOffset(float x, float y) {
        super.load2DVector(location_offset, new Vector2f(x, y));
    }



}
