package utils;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * Created by one on 9/17/16.
 */
public class Maths {

    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.identity();
        matrix.translate(translation.x, translation.y, 0);
        matrix.scale(new Vector3f(scale.x, scale.y, 1));
        return matrix;

    }

    public static Matrix4f createTransformationMatrix(Vector2f position, Vector3f rotation, float scale) {
        return new Matrix4f()
                .identity()
                .translate(position.x, position.y, 0);

    }

    public static Matrix4f createTransformationMatrix(Vector2f position, float degree) {
        return new Matrix4f()
                .identity()
                .translate(position.x, position.y, 0)
                .rotate(degree, new Vector3f(0, 0, 1));
    }



}
