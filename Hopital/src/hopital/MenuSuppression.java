/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.autoSizeX;
import static hopital.HopitalGraphique.autoSizeY;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Aude
 */
public class MenuSuppression extends JPanel implements ActionListener {

    private JButton retour;
    private JTabbedPane onglets;
    private final ImagePan back;

    private HopitalGraphique hopGraph;
    private ArrayList<String> resultats = new ArrayList<>();

    public MenuSuppression(HopitalGraphique hop) {

        onglets = new JTabbedPane();
        retour = new JButton("Retour");
        back = new ImagePan("image.jpg");
        hopGraph = hop;
        this.build();
    }

    private void build() {
        this.setLayout(null);
        try {
            resultats = MenuConnexion.getConnexion().remplirChampsRequete2("show tables");
        } catch (SQLException ex) {
            Logger.getLogger(InterrogationAbs.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String res : resultats) {
            onglets.addTab(res, new Suppression(hopGraph, res));
        }

        onglets.setBackground(Color.PINK);
        this.add(onglets);

        retour.addActionListener(this);
        add(retour);
        add(back);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retour) {
            hopGraph.changeFenetre(4);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        back.setBounds(0, 0, autoSizeX(1.0), autoSizeY(1.0));
        onglets.setBounds(autoSizeX(0.0625), autoSizeY(0.033), autoSizeX(0.875), autoSizeY(0.783));
        retour.setBounds(autoSizeX(0.3125), autoSizeY(0.839), autoSizeX(0.375), autoSizeY(0.0833));
        retour.setBackground(Color.PINK);
    }

}
