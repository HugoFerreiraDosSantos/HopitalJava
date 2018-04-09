/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.*;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author PPC
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
        onglets.addTab("Recherche par formulaire", new Interrogation(hopGraph));
        onglets.addTab("Recherche avancée", new RechercheAvancee(hopGraph));

        onglets.setBounds(autoSizeX(0.0), autoSizeY(0.0), autoSizeX(0.990), autoSizeY(1.0));
        this.add(onglets);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        onglets.setBounds(autoSizeX(0.0), autoSizeY(0.0), autoSizeX(0.990), autoSizeY(1.0));
    }
}
