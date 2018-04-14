/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.DocParSpe;
import controleur.InfParSer;
import controleur.MalParSexe;
import controleur.PatParSer;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import modele.HopitalGraphique;
import static modele.HopitalGraphique.autoSizeX;
import static modele.HopitalGraphique.autoSizeY;
import modele.ImagePan;

/**
 * Class abstraite qui gere la vue de MenuReporting
 *
 * @author Aude et Hugo
 */
public abstract class MenuReportingAbs extends JPanel {

    /**
     * Attributs privés ou protected : Components de la classe
     */
    protected JButton retour;
    protected JTabbedPane onglets;
    protected final ImagePan back;
    protected HopitalGraphique hopGraph;

    /**
     * Instantiation des differents attributs
     *
     * @param hop Instance d'HopitalGraphique
     */
    public MenuReportingAbs(HopitalGraphique hop) {

        onglets = new JTabbedPane();
        retour = new JButton("Retour");
        hopGraph = hop;
        back = new ImagePan("images/image.jpg");
        this.build();
    }

    /**
     *
     * Mise en place et parametrage des ddifferents components de la classe
     */
    private void build() {
        this.setLayout(null);
        onglets.addTab("Docteurs par spécialité", new DocParSpe());
        onglets.addTab("Infirmiers par service", new InfParSer());
        onglets.addTab("Patients par service", new PatParSer());
        onglets.addTab("Maladies les plus contractées par sexe", new MalParSexe());
        onglets.setBackground(Color.PINK);
        this.add(onglets);
        this.add(retour);
        this.add(back);

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
        retour.setBounds(autoSizeX(0.3125), autoSizeY(0.833), autoSizeX(0.375), autoSizeY(0.0833));
        retour.setBackground(Color.PINK);
    }

}
