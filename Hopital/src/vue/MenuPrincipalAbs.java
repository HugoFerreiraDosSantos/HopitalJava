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
 * Class abstraite qui gere la vue de MenuPrincipal
 *
 * @author Aude et Hugo
 */
public abstract class MenuPrincipalAbs extends JPanel {

    /**
     * Attributs privés ou protected : Components de la classe
     */
    protected final JButton retour;
    protected final HopitalGraphique hopGraph;
    protected final JButton reporting;
    protected final JButton interrogation;
    protected final JButton miseAJour;
    private final ImagePan back;

    /**
     * Instantiation des differents attributs
     *
     * @param hop Instance d'HopitalGraphique
     */
    public MenuPrincipalAbs(HopitalGraphique hop) {
        hopGraph = hop;
        retour = new JButton("Retour");
        reporting = new JButton("Reporting");
        miseAJour = new JButton("Mise à jour");
        interrogation = new JButton("Recherche");
        back = new ImagePan("images/image.jpg");
        this.build();
    }

    /**
     *
     * Mise en place et parametrage des ddifferents components de la classe
     */
    private void build() {
        this.setLayout(null);
        this.add(reporting);

        this.add(miseAJour);

        this.add(interrogation);

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
            g.drawImage(ImageIO.read(new File("images/principale.png")), 0, 0, autoSizeX(1.0), autoSizeY(1.0), this);
        } catch (IOException ex) {
            Logger.getLogger(MenuConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        reporting.setBounds(autoSizeX(0.368), autoSizeY(0.25), autoSizeX(0.25), autoSizeY(0.116));
        miseAJour.setBounds(autoSizeX(0.36875), autoSizeY(0.416), autoSizeX(0.25), autoSizeY(0.116));
        interrogation.setBounds(autoSizeX(0.36875), autoSizeY(0.5833), autoSizeX(0.25), autoSizeY(0.116));
        retour.setBounds(autoSizeX(0.36875), autoSizeY(0.75), autoSizeX(0.25), autoSizeY(0.116));

        back.setBounds(0, 0, autoSizeX(1.0), autoSizeY(1.0));
        reporting.setBackground(Color.PINK);
        miseAJour.setBackground(Color.PINK);
        interrogation.setBackground(Color.PINK);
        retour.setBackground(Color.PINK);
    }

}
