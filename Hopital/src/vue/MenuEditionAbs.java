/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.Edition;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import modele.HopitalGraphique;
import static modele.HopitalGraphique.autoSizeX;
import static modele.HopitalGraphique.autoSizeY;
import modele.ImagePan;
import modele.MenuConnexion;

/**
 * Class abstraite qui gere la vue de MenuEdition
 *
 * @author Aude et Hugo
 */
public abstract class MenuEditionAbs extends JPanel {

    /**
     * Attributs privés ou protected : Components de la classe
     */
    protected JButton retour;
    protected JTabbedPane onglets;
    protected final ImagePan back;
    protected HopitalGraphique hopGraph;
    protected ArrayList<String> resultats = new ArrayList<>();

    /**
     * Instantiation des differents attributs
     *
     * @param hop Instance d'HopitalGraphique
     */
    public MenuEditionAbs(HopitalGraphique hop) {

        onglets = new JTabbedPane();
        retour = new JButton("Retour");
        back = new ImagePan("images/image.jpg");
        hopGraph = hop;
        this.build();
    }

    /**
     *
     * Mise en place et parametrage des ddifferents components de la classe
     */
    private void build() {
        this.setLayout(null);
        try {
            resultats = MenuConnexion.getConnexion().remplirChampsRequete2("show tables");
        } catch (SQLException ex) {
            Logger.getLogger(InterrogationAbs.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultats.forEach((res) -> {
            onglets.addTab(res, new Edition(hopGraph, res));
        });

        onglets.setBackground(Color.PINK);
        this.add(onglets);

        add(retour);
        add(back);
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
        back.setBounds(0, 0, autoSizeX(1.0), autoSizeY(1.0));
        onglets.setBounds(autoSizeX(0.0625), autoSizeY(0.033), autoSizeX(0.875), autoSizeY(0.783));
        retour.setBounds(autoSizeX(0.3125), autoSizeY(0.839), autoSizeX(0.375), autoSizeY(0.0833));

        retour.setBackground(Color.PINK);
    }

}
