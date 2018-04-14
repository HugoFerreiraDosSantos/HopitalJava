/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import modele.HopitalGraphique;
import static modele.HopitalGraphique.autoSizeX;
import static modele.HopitalGraphique.autoSizeY;
import modele.ImagePan;
import modele.MenuConnexion;

/**
 * Class abstraite qui gere la vue de MenuMisAJour
 *
 * @author Aude et Hugo
 */
public abstract class MenuMisAJourAbs extends JPanel {

    /**
     * Attributs privés ou protected : Components de la classe
     */
    protected final JButton retour;
    protected final HopitalGraphique hopGraph;
    protected final JButton ajout;
    protected final JButton suppression;
    protected final JButton modification;
    protected final ImagePan back;

    /**
     * Instantiation des differents attributs
     *
     * @param hop Instance d'HopitalGraphique
     */
    public MenuMisAJourAbs(HopitalGraphique hop) {
        hopGraph = hop;
        retour = new JButton("Retour");
        ajout = new JButton("Ajout");
        modification = new JButton("Modification");
        suppression = new JButton("Suppression");
        back = new ImagePan("images/image.jpg");
        this.build();
    }

    /**
     *
     * Mise en place et parametrage des ddifferents components de la classe
     */
    private void build() {
        this.setLayout(null);

        this.add(ajout);

        this.add(modification);

        this.add(suppression);

        this.add(retour);
        this.add(back);
        revalidate();
        repaint();
    }

    /**
     * Methode qui addapte la taille et la forme des components en fonction de
     * la taille et la forme de l'écran
     *
     * @param g Instance de Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        try {
            g.drawImage(ImageIO.read(new File("images/maj.png")), 0, 0, autoSizeX(1.0), autoSizeY(1.0), this);
        } catch (IOException ex) {
            Logger.getLogger(MenuConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        back.setBounds(0, 0, autoSizeX(1.0), autoSizeY(1.0));
        ajout.setBounds(autoSizeX(0.368), autoSizeY(0.25), autoSizeX(0.25), autoSizeY(0.116));
        modification.setBounds(autoSizeX(0.36875), autoSizeY(0.416), autoSizeX(0.25), autoSizeY(0.116));
        suppression.setBounds(autoSizeX(0.36875), autoSizeY(0.5833), autoSizeX(0.25), autoSizeY(0.116));
        retour.setBounds(autoSizeX(0.36875), autoSizeY(0.75), autoSizeX(0.25), autoSizeY(0.116));

        ajout.setBackground(Color.PINK);
        modification.setBackground(Color.PINK);
        suppression.setBackground(Color.PINK);
        retour.setBackground(Color.PINK);
    }

}
