/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author PPC
 */
public class MenuPrincipal extends JPanel implements ActionListener {

    private JButton retour;
    private HopitalGraphique hopGraph;
    private JLabel title;
    private JButton reporting;
    private JButton interrogation;
    private JButton miseAJour;

    public MenuPrincipal(HopitalGraphique hop) {
        hopGraph = hop;
        title = new JLabel("Menu principal");
        retour = new JButton("Retour");
        reporting = new JButton("Reporting");
        miseAJour = new JButton("Mise à jour");
        interrogation = new JButton("Recherche");
        this.build();
    }

    private void build() {
        this.setLayout(null);

        title.setBounds(autoSizeX(0.456), autoSizeY(0.083), autoSizeX(0.25), autoSizeY(0.116));
        this.add(title);

        reporting.setBounds(autoSizeX(0.368), autoSizeY(0.25), autoSizeX(0.25), autoSizeY(0.116));
        reporting.addActionListener(this);
        this.add(reporting);

        miseAJour.setBounds(autoSizeX(0.36875), autoSizeY(0.416), autoSizeX(0.25), autoSizeY(0.116));
        miseAJour.addActionListener(this);
        this.add(miseAJour);

        interrogation.setBounds(autoSizeX(0.36875), autoSizeY(0.5833), autoSizeX(0.25), autoSizeY(0.116));
        interrogation.addActionListener(this);
        this.add(interrogation);

        retour.setBounds(autoSizeX(0.36875), autoSizeY(0.75), autoSizeX(0.25), autoSizeY(0.116));
        retour.addActionListener(this);
        this.add(retour);

        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retour) {
            hopGraph.changeFenetre(0);
        } else if (e.getSource() == reporting) {
            hopGraph.changeFenetre(2);
        } else if (e.getSource() == interrogation) {
            hopGraph.changeFenetre(3);
        }
    }
}
