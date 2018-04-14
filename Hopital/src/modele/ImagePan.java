/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static modele.HopitalGraphique.autoSizeX;
import static modele.HopitalGraphique.autoSizeY;

/**
 *
 * @author Hugo
 *
 * Classe qui gère les images
 */
public class ImagePan extends JPanel {

    private String fileName;

    /**
     *
     * @param file Path de l'image
     */
    public ImagePan(String file) {
        fileName = file;
        this.build();
    }

    /**
     * Methode qui instancie l'image
     */
    private void build() {
        this.setBounds(0, 0, autoSizeX(1.0), autoSizeY(1.0));
        this.revalidate();
    }

    /**
     * Methode qui permet l'affichage de l'image de façon responsive
     *
     * @param g Instance de Graphics pour gerer une image
     */
    @Override
    public void paint(Graphics g) {
        try {
            g.drawImage(ImageIO.read(new File(fileName)), 0, 0, autoSizeX(1.0), autoSizeY(1.0), this);
        } catch (IOException ex) {
            Logger.getLogger(ImagePan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
