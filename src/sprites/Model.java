package sprites;

import utils.Loader;

/**
 * Created by one on 9/17/16.
 */
public class Model {

    private float width;
    private float height;

    private Texture texture;

    private int vaoID;

    public Model(float width, float height, String textureFilePath) throws Exception {

        this.width = width;
        this.height = height;
        this.texture = TextureCache.getTexture(textureFilePath);

        float[] vertices = {
                0, height,
                0, 0,
                width, 0,
                width, height

        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        vaoID = Loader.loadToVAO(vertices, textureCoords, indices);
    }

    public Model(float width, float height, String textureFilePath, int numRows) throws Exception {

        this.width = width;
        this.height = height;
        this.texture = TextureCache.getTexture(textureFilePath, numRows);

        float[] vertices = {
                0, height,
                0, 0,
                width, 0,
                width, height

        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        vaoID = Loader.loadToVAO(vertices, textureCoords, indices);
    }

    public Texture getTexture() {
        return texture;
    }

    public int getVaoID() {
        return vaoID;
    }

}
