/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Aude
 */
public class MenuMisAJour extends JPanel implements ActionListener {

    private final JButton retour;
    private final HopitalGraphique hopGraph;
    private final JLabel title;
    private final JButton ajout;
    private final JButton suppression;
    private final JButton modification;

    public MenuMisAJour(HopitalGraphique hop) {
        hopGraph = hop;
        title = new JLabel("Menu de mise à jour");
        retour = new JButton("Retour");
        ajout = new JButton("Ajout");
        modification = new JButton("Modification");
        suppression = new JButton("Suppression");
        this.build();
    }

    private void build() {
        this.setLayout(null);

        this.add(title);

        ajout.addActionListener(this);
        this.add(ajout);

        modification.addActionListener(this);
        this.add(modification);

        suppression.addActionListener(this);
        this.add(suppression);

        retour.addActionListener(this);
        this.add(retour);

        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retour) {
            hopGraph.changeFenetre(1);
        } else if (e.getSource() == ajout) {
            hopGraph.changeFenetre(5);
        } else if (e.getSource() == modification) {
            hopGraph.changeFenetre(6);
        }
        else if (e.getSource() == suppression)
        {
            hopGraph.changeFenetre(7);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        title.setBounds(autoSizeX(0.435), autoSizeY(0.083), autoSizeX(0.25), autoSizeY(0.116));
        ajout.setBounds(autoSizeX(0.368), autoSizeY(0.25), autoSizeX(0.25), autoSizeY(0.116));
        modification.setBounds(autoSizeX(0.36875), autoSizeY(0.416), autoSizeX(0.25), autoSizeY(0.116));
        suppression.setBounds(autoSizeX(0.36875), autoSizeY(0.5833), autoSizeX(0.25), autoSizeY(0.116));
        retour.setBounds(autoSizeX(0.36875), autoSizeY(0.75), autoSizeX(0.25), autoSizeY(0.116));
    }

}

