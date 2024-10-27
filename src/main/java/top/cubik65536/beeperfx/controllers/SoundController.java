package top.cubik65536.beeperfx.controllers;

import java.io.ByteArrayInputStream;

import javax.sound.sampled.*;

public class SoundController {
    private static final int FRAMES_PER_WAVELENGTH = 32;

    /**
     * The audio data clip to be played.
     */
    Clip clip;

    public SoundController() throws LineUnavailableException {
        this.clip = AudioSystem.getClip();
    }

    private static byte getByteValue(double angle) {
        int maxVol = 127;
        return Integer.valueOf((int) Math.round(Math.sin(angle) * maxVol)).byteValue();
    }

    public void setUpSound() {
        try {
            generateTone();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void generateTone() {
        clip.stop();
        clip.close();

        int wavelengths = 20;
        byte[] buffer = new byte[2 * FRAMES_PER_WAVELENGTH * wavelengths];
        AudioFormat audioFormat = new AudioFormat(
                44100,  // sample rate
                8,  // sample size in bits
                2,  // channels
                true,  // signed
                false  // bigendian
        );

        for (int i = 0; i < FRAMES_PER_WAVELENGTH * wavelengths; i++) {
            double angle = ((float) (i * 2) / ((float) FRAMES_PER_WAVELENGTH)) * (Math.PI);
            buffer[i * 2] = getByteValue(angle);
            buffer[(i * 2) + 1] = buffer[i * 2];
        }

        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
            AudioInputStream ais = new AudioInputStream(
                    byteArrayInputStream,
                    audioFormat,
                    buffer.length / 2
            );
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
