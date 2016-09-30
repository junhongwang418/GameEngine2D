package particles;

import utils.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by one on 9/25/16.
 */
public class ParticleEngine {

    private static List<ParticleBatch> batches = new ArrayList<ParticleBatch>();

    public static void addParticleBatch(ParticleBatch particleBatch) {
        batches.add(particleBatch);
    }

    public void update() {

        for (ParticleBatch batch:batches) {
            batch.update();
        }

    }

    public void render(Camera camera) {

        for (ParticleBatch batch:batches) {
            batch.render(camera);
        }

    }


}
