/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.InterrogationImpl;
import controleur.RechercheAvanceeImpl;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import static modele.HopitalGraphique.*;

/**
 * Class qui gere le menu d'interrogation de l'application
 *
 * @author Aude et Hugo
 */
public class MenuInterrogation extends JPanel {

    private JTabbedPane onglets;
    private HopitalGraphique hopGraph;

    public MenuInterrogation(HopitalGraphique hop) {

        onglets = new JTabbedPane();
        hopGraph = hop;
        this.build();
    }

    private void build() {
        this.setLayout(null);
        onglets.addTab("Recherche par formulaire", new InterrogationImpl(hopGraph));
        onglets.addTab("Recherche avancée", new RechercheAvanceeImpl(hopGraph));
        this.add(onglets);
        repaint();
    }

    /**
     * Methode qui permet d'apapter la position et la forme des objects en
     * fonction de la taille de la fenetre
     *
     * @param g Instance de Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        onglets.setBounds(autoSizeX(0.0), autoSizeY(0.0), autoSizeX(0.990), autoSizeY(1.0));
    }
}
