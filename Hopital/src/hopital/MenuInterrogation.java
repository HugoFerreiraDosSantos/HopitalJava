/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.autoSizeX;
import static hopital.HopitalGraphique.autoSizeY;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author PPC
 */
public class MenuInterrogation extends JPanel implements ChangeListener {

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
        onglets.addTab("Recherche avancée", null);
        onglets.addChangeListener(this);
        onglets.setBounds(autoSizeX(0.0), autoSizeY(0.0), autoSizeX(0.999), autoSizeY(1.0));
        this.add(onglets);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
