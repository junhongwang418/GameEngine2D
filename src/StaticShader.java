import org.joml.Matrix4f;

/**
 * Created by one on 9/12/16.
 */
public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/shaders/vertexShader.vert";
    private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.frag";

    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_cameraMatrix;

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

        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_cameraMatrix = super.getUniformLocation("cameraMatrix");
    }

    public void loadCameraMatrix(Matrix4f cameraMatrix) {
        loadMatrix(location_cameraMatrix, cameraMatrix);
    }



}
