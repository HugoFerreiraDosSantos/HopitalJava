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
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PPC
 */
public class RechercheAvancee extends JPanel implements ActionListener {

    private ArrayList<String> resultats;
    private ArrayList<String> columns;
    private String requete;
    private JButton b = new JButton("test");
    private JTextField t = new JTextField();
    private HopitalGraphique hopGraph;

    public RechercheAvancee(HopitalGraphique hop) {
        this.setLayout(null);
        b.addActionListener(this);
        b.setBounds(autoSizeX(0.2), autoSizeY(0.2), autoSizeX(0.3), autoSizeY(0.3));
        t.setBounds(autoSizeX(0.2), autoSizeY(0.5), autoSizeX(0.3), autoSizeY(0.3));
        resultats = new ArrayList<>();
        columns = new ArrayList<>();
        this.hopGraph = hop;
        this.add(b);
        this.add(t);
        this.build();
    }

    private void build() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            requete = t.getText();
            String[] title = null;
            if (!"".equals(requete) && !requete.isEmpty()) {

                if (MenuConnexion.getConnexion() != null) {
                    try {
                        resultats = MenuConnexion.getConnexion().remplirChampsRequete2(requete);
                        title = MenuConnexion.getConnexion().remplirChampsColumn(requete);

                    } catch (SQLException ex) {
                    }
                } else {
                    System.out.println("Erreur");
                }

            }
            int cpt = 0;
            if (!resultats.isEmpty()) {

                for (int i = 0; i < resultats.get(0).length(); i++) {
                    if (resultats.get(0).charAt(i) == ';') {
                        cpt++;
                    }
                }
            }

            new InterroRequete(resultats, title);

        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        b.setBounds(autoSizeX(0.2), autoSizeY(0.2), autoSizeX(0.3), autoSizeY(0.3));
        t.setBounds(autoSizeX(0.2), autoSizeY(0.5), autoSizeX(0.3), autoSizeY(0.3));
    }
}
