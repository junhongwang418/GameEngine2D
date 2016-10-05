package audios;

import org.lwjgl.openal.AL10;

/**
 * Created by one on 10/3/16.
 */
public class Source {

    private int sourceID;

    public Source() {

        sourceID = AL10.alGenSources();

        // set property of the source
        AL10.alSourcef(sourceID, AL10.AL_GAIN, 1); // volume
        AL10.alSourcef(sourceID, AL10.AL_PITCH, 1); // pitch
        AL10.alSource3f(sourceID, AL10.AL_POSITION, 0, 0, 0); // position

//        AL10.alSourcef(sourceID, AL10.AL_ROLLOFF_FACTOR, 1);
//        AL10.alSourcef(sourceID, AL10.AL_REFERENCE_DISTANCE, 6);
//        AL10.alSourcef(sourceID, AL10.AL_MAX_DISTANCE, 15);

    }

    public void play(int buffer) {
        // put the cd into the cd player
        stop();
        AL10.alSourcei(sourceID, AL10.AL_BUFFER, buffer);
        continuePlaying();


    }

    public void delete() {
        stop();
        AL10.alDeleteSources(sourceID);
    }

    public int getSourceID() {
        return sourceID;
    }

    public void pause() {
        AL10.alSourcePause(sourceID);
    }

    public void continuePlaying() {
        AL10.alSourcePlay(sourceID);
    }

    public void stop() {
        AL10.alSourceStop(sourceID);
    }

    public void setLooping(boolean loop) {
        AL10.alSourcei(sourceID, AL10.AL_LOOPING, loop ? AL10.AL_TRUE : AL10.AL_FALSE);
    }

    public boolean isPlaying() {
        return AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING ;
    }

    public void setVelocity(float x, float y, float z) {
        AL10.alSource3f(sourceID, AL10.AL_VELOCITY, x, y, z);
    }


    public void setVolume(float volume) {
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }

    public void setPitch(float pitch) {
        AL10.alSourcef(sourceID, AL10.AL_PITCH, pitch);
    }

    public void setPosition(float x, float y, float z) {
        AL10.alSource3f(sourceID, AL10.AL_POSITION, x, y, z);
    }
}
