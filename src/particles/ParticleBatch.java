package particles;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import sprites.Texture;
import utils.Camera;
import utils.Loader;
import utils.Maths;

/**
 * Created by one on 9/25/16.
 */
public class ParticleBatch {

    private float decayRate = 0.1f; // how fast the particle disappear

    private Particle[] myParticles;

    private int maxParticles = 0;

    private int lastFreeParticle = 0;

    private Texture texture;

    private Vector2f textureOffset1 = new Vector2f(0, 0);

    private Vector2f textureOffset2 = new Vector2f(0, 0);

    private float blendFactor;

    public final float SIZE = 50;

    private int vaoID;

    private ParticleShader shader;

    private int vboID;

    private static final int INSTANCE_DATA_LENGTH = 21;

    public void init(int maxParticles, float decayRate, Texture texture) {
        this.maxParticles = maxParticles;
        this.decayRate = decayRate;
        this.texture = texture;
        myParticles = new Particle[maxParticles];
        for (int i = 0; i < maxParticles; i++) {
            Particle p = new Particle();
            myParticles[i] = p;
        }

        float[] vertices = {
                0, SIZE,
                0, 0,
                SIZE, 0,
                SIZE, SIZE

        };
        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        vaoID = Loader.loadToVAO(vertices, textureCoords, indices);
        shader = new ParticleShader();

    }

    public void update() {

        for (int i = 0; i < maxParticles; i++) {
            // check if it is active
            Particle p = myParticles[i];
            if (p.getLife() > 0) {
                p.update();
                p.decay(decayRate);

            }
        }

    }

    public void render(Camera camera) {

        prepare(camera);


        GL13.glActiveTexture(0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getId());

        for (int i = 0; i < maxParticles; i++) {
            // check if it is active
            Particle p = myParticles[i];
            float pLife = p.getLife();
            if (pLife > 0) {

                updateTextureCoordInfo(pLife);

                shader.loadTransformationMatrix(Maths.createTransformationMatrix(p.getPosition(), new Vector3f(0, 0, 0), 1));
                shader.loadTextureCoordInfo(textureOffset1, textureOffset2, texture.getNumRows(), blendFactor);

                GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
            }
        }

        finishRendering();

    }

    private void prepare(Camera camera) {
        shader.start();
        GL30.glBindVertexArray(vaoID);
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        shader.loadCameraMatrix(camera.getCameraMatrix());


    }


    public void addParticle(Vector2f position, Vector2f velocity, Vector3f color) {
        int particleIndex = findFreeParticle();
        Particle p = myParticles[particleIndex];
        p.setLife(1);
        p.setPosition(position);
        p.setVelocity(velocity);
        p.setColor(color);
    }

    private int findFreeParticle() {

        for (int i = lastFreeParticle; i < maxParticles; i++) {
            if (myParticles[i].getLife() <= 0) {
                lastFreeParticle = i;
                return i;
            }
        }

        for (int i = 0; i < lastFreeParticle; i++) {
            if (myParticles[i].getLife() <= 0) {
                lastFreeParticle = i;
                return i;
            }
        }

        // no particles are free, overwrite the first particle
        return 0;


    }

    public void finishRendering() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        shader.stop();
    }


    public float getBlendFactor() {
        return blendFactor;
    }

    public Vector2f getTextureOffset1() {
        return textureOffset1;
    }

    public Vector2f getTextureOffset2() {
        return textureOffset2;
    }

    public void cleanUp() {
        shader.cleanUp();
    }

    private void updateTextureCoordInfo(float lifeFactor) {
        int stageCount = texture.getNumRows() * texture.getNumRows();
        float atlasProgression = lifeFactor * stageCount;
        int index1 = (int) Math.floor(atlasProgression);
        int index2 = index1 < stageCount - 1 ? index1 + 1 : index1;
        this.blendFactor = atlasProgression % 1;
        setTextureOffset(textureOffset1, index1);
        setTextureOffset(textureOffset2, index2);
    }

    private void setTextureOffset(Vector2f offset, int index) {
        int column = index % texture.getNumRows();
        int row = index / texture.getNumRows();
        offset.x = (float) column / texture.getNumRows();
        offset.y = (float) row / texture.getNumRows();

    }

}
