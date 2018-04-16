/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.Connexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import modele.HopitalGraphique;
import static modele.HopitalGraphique.autoSizeX;
import static modele.HopitalGraphique.autoSizeY;
import modele.ImagePan;

/**
 * Class abstraite qui gere la vue de MenuConnexion
 *
 * @author Aude et Hugo
 */
public abstract class MenuConnexionAbs extends JPanel {

    /**
     * Attributs privés ou protected : Components de la classe
     */
    protected ButtonGroup bg;
    protected JRadioButton locale;
    protected JRadioButton distante;
    protected JPanel pan_locale;
    protected JPanel pan_distante;
    protected ImagePan back;
    protected JButton valider;
    protected JButton quitter;
    protected static Connexion db;
    protected JTextField test[];
    protected HopitalGraphique hopGraph;

    /**
     * Instantiation des differents attributs
     *
     * @param hop Instance d'HopitalGraphique
     */
    public MenuConnexionAbs(HopitalGraphique hop) {
        hopGraph = hop;
        db = null;
        pan_locale = new JPanel();
        pan_distante = new JPanel();
        bg = new ButtonGroup();
        locale = new JRadioButton("Locale", true);
        distante = new JRadioButton("Distante");
        valider = new JButton("Valider");
        quitter = new JButton("Quitter");
        back = new ImagePan("images/image.jpg");
        test = new JTextField[7];
        this.build();

    }

    /**
     *
     * Mise en place et parametrage des ddifferents components de la classe
     */
    private void build() {

        bg.add(locale);
        bg.add(distante);

        this.setLayout(null);
        pan_locale.add(locale);
        test[0] = new JTextField();
        test[0].setToolTipText("NameDatabase");
        test[1] = new JTextField();
        test[1].setToolTipText("LoginDatabase");
        test[2] = new JPasswordField();
        test[2].setToolTipText("PasswordDatabase");
        for (int i = 0; i < 3; i++) {
            pan_locale.add(test[i]);
            test[i].setPreferredSize(new Dimension(autoSizeX(0.1), autoSizeY(0.05)));
        }
        pan_locale.setBounds(autoSizeX(0.15), autoSizeY(0.5), autoSizeX(0.5), autoSizeY(0.0667));

        this.add(pan_locale);

        pan_distante.add(distante);
        test[3] = new JTextField();
        test[3].setToolTipText("ServerName");
        test[4] = new JTextField();
        test[4].setToolTipText("DatabaseName");
        test[5] = new JTextField();
        test[5].setToolTipText("LoginDatabase");
        test[6] = new JPasswordField();
        test[6].setToolTipText("PasswordDatabase");

        for (int i = 3; i < 7; i++) {
            pan_distante.add(test[i]);
            test[i].setPreferredSize(new Dimension(autoSizeX(0.1), autoSizeY(0.05)));
        }
        pan_distante.setBounds(autoSizeX(0.14375), autoSizeY(0.6167), autoSizeX(0.625), autoSizeY(0.0667));
        this.add(pan_distante);

        valider.setBounds(autoSizeX(0.33125), autoSizeY(0.7833), autoSizeX(0.125), autoSizeY(0.0667));
        this.add(valider);

        quitter.setBounds(autoSizeX(0.53125), autoSizeY(0.7833), autoSizeX(0.125), autoSizeY(0.0667));
        this.add(quitter);

        this.add(back);
        repaint();
    }

    public static Connexion getConnexion() {
        return db;
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
            g.drawImage(ImageIO.read(new File("images/connexion.png")), 0, 0, autoSizeX(1.0), autoSizeY(1.0), this);
        } catch (IOException ex) {

        }
        back.setBounds(0, 0, autoSizeX(1.0), autoSizeY(1.0));
        for (int i = 0; i < 3; i++) {
            test[i].setPreferredSize(new Dimension(autoSizeX(0.1), autoSizeY(0.05)));
        }
        pan_locale.setBounds(autoSizeX(0.15), autoSizeY(0.5), autoSizeX(0.5), autoSizeY(0.0667));
        pan_locale.setBackground(Color.PINK);
        for (int i = 3; i < 7; i++) {
            test[i].setPreferredSize(new Dimension(autoSizeX(0.1), autoSizeY(0.05)));
        }

        pan_distante.setBounds(autoSizeX(0.14375), autoSizeY(0.6167), autoSizeX(0.625), autoSizeY(0.0667));
        pan_distante.setBackground(Color.PINK);
        valider.setBounds(autoSizeX(0.33125), autoSizeY(0.7833), autoSizeX(0.125), autoSizeY(0.0667));
        valider.setBackground(Color.PINK);
        quitter.setBounds(autoSizeX(0.53125), autoSizeY(0.7833), autoSizeX(0.125), autoSizeY(0.0667));
        quitter.setBackground(Color.PINK);

    }

}
