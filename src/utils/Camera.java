package utils;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by one on 9/17/16.
 */
public class Camera {

    private Vector2f position;

    private Matrix4f viewMatrix;

    private Matrix4f orthoMatrix;

    private Matrix4f cameraMatrix;

    private boolean needsMatrixUpdate;

    private float scale;

    private int screenWidth;

    private int screenHeight;

    public Camera() {
        position = new Vector2f(0, 0);
        viewMatrix = new Matrix4f().identity();
        orthoMatrix = new Matrix4f().identity();
        cameraMatrix = new Matrix4f().identity();
        scale = 1;
        needsMatrixUpdate = true;
        screenWidth = 0;
        screenHeight = 0;
    }

    public void init(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        position = new Vector2f(screenWidth/2, screenHeight/2);
        orthoMatrix = new Matrix4f().ortho2D(-screenWidth/2, (float) screenWidth/2, -screenHeight/2, (float) screenHeight/2);
    }

    public void increasePosition(float dx, float dy) {
        this.position.x += dx;
        this.position.y += dy;
        needsMatrixUpdate = true;
    }

    public void increaseScale(float ds) {
        this.scale += ds;
        needsMatrixUpdate = true;
    }

    public void update() {
        if (needsMatrixUpdate) {
            viewMatrix = new Matrix4f()
                    .identity()
                    .translate(new Vector3f(-position.x, -position.y, 0));

            cameraMatrix = new Matrix4f().identity().scale(scale, scale, 0).mul(orthoMatrix).mul(viewMatrix);
        }
    }


    public void setPosition(Vector2f position) {
        this.position = position;
        needsMatrixUpdate = true;
    }

    public Vector2f getPosition() {
        return position;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
        needsMatrixUpdate = true;
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }

    public void setViewMatrix(Matrix4f viewMatrix) {
        this.viewMatrix = viewMatrix;
    }

    public Matrix4f getOrthoMatrix() {
        return orthoMatrix;
    }

    public void setOrthoMatrix(Matrix4f orthoMatrix) {
        this.orthoMatrix = orthoMatrix;
    }

    public Matrix4f getCameraMatrix() {
        return cameraMatrix;
    }

    public void setCameraMatrix(Matrix4f cameraMatrix) {
        this.cameraMatrix = cameraMatrix;
    }

}
