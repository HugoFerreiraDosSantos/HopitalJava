/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.*;
import javax.sound.sampled.*;

/**
 *
 * @author Hugo Gere le son
 */
public class Sound {

    private Clip clipS;
    /**
     * Etat de la musique et des bruitages
     */
    private boolean state;

    /**
     *
     * @param fileName nom du fichier son
     *
     * Activation de la musique ou du bruitage
     */
    public Sound(String fileName) {
        state = true;

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(fileName));
            clipS = AudioSystem.getClip();
            clipS.open(audioIn);
            clipS.loop(Clip.LOOP_CONTINUOUSLY);
            clipS.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }

    /**
     * Stop ou relance la musique
     */
    public void startStopSound() {
        state = !state;
        if (state) {
            clipS.loop(Clip.LOOP_CONTINUOUSLY);
            clipS.start();
        } else {
            clipS.stop();
        }
    }

    /**
     * Libere le son
     */
    public void close() {
        clipS.close();
    }

}
