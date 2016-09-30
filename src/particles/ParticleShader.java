package particles;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import shaders.ShaderProgram;

/**
 * Created by one on 9/25/16.
 */
public class ParticleShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/particles/particleVShader.txt";
    private static final String FRAGMENT_FILE = "src/particles/particleFShader.txt";

    private int location_cameraMatrix;
    private int location_transformationMatrix;
    private int location_texOffset1;
    private int location_texOffset2;
    private int location_texCoordInfo;

    public ParticleShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_cameraMatrix = super.getUniformLocation("cameraMatrix");
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_texOffset1 = super.getUniformLocation("texOffset1");
        location_texOffset2 = super.getUniformLocation("texOffset2");
        location_texCoordInfo = super.getUniformLocation("texCoordInfo");

    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    protected void loadTextureCoordInfo(Vector2f offset1, Vector2f offset2, float numRows, float blend) {
        super.load2DVector(location_texOffset1, offset1);
        super.load2DVector(location_texOffset2, offset2);
        super.load2DVector(location_texCoordInfo, new Vector2f(numRows, blend));
    }

    protected void loadCameraMatrix(Matrix4f matrix) {
        super.loadMatrix(location_cameraMatrix, matrix);
    }

    protected void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

}