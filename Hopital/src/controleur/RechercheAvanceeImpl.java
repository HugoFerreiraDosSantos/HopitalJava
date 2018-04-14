/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modele.HopitalGraphique;
import modele.MenuConnexion;
import vue.RechercheAvanceeAbs;

/**
 * implementation de la classe qui cree une requete avancée a partir d'une
 * saisie ( hérite de RechercheAvanceeAbs et implémente ActionListener )
 *
 * @author Aude et Hugo
 */
public class RechercheAvanceeImpl extends RechercheAvanceeAbs implements ActionListener {

    /**
     * Attributs privés
     */
    private String requete;
    private ArrayList<String> resultats;

    public RechercheAvanceeImpl(HopitalGraphique hop) {
        super(hop);
        resultats = new ArrayList<>();
        this.build();
    }

    /**
     * Methode qui instancie la classe avec les actionListener
     */
    private void build() {
        b.addActionListener(this);
        r.addActionListener(this);

    }

    /**
     * Surcharge de actionPerformed pour récuperer les données saisies dans le
     * JTextArea et créer et envoyer la requete à la base
     *
     * @param e Action effectuée
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            boolean error = false;
            this.remove(pan);
            requete = t.getText();
            Object data[][] = null;
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
                if (!resultats.isEmpty()) {
                    int cpt = 0;
                    for (int i = 0; i < resultats.get(0).length(); i++) {
                        if (resultats.get(0).charAt(i) == ';') {
                            cpt++;
                        }
                    }

                    data = new Object[resultats.size()][cpt + 1];
                    for (int i = 0; i < resultats.size(); i++) {
                        int j = 0;
                        while (resultats.get(i).contains(";")) {
                            int pos = resultats.get(i).indexOf(";");
                            data[i][j++] = resultats.get(i).substring(0, pos);
                            resultats.set(i, resultats.get(i).substring(pos + 1));
                        }
                        data[i][j] = resultats.get(i);
                    }
                    if (data == null || title == null) {
                        error = true;

                    }
                } else {
                    error = true;
                }
            } else {
                error = true;
            }
            if (error) {
                data = new Object[1][1];
                title = new String[1];
                data[0][0] = " ";
                title[0] = "Aucun résultat";
                JOptionPane.showMessageDialog(this, new JLabel("Aucun résultat"), "Résultat de la requête", JOptionPane.INFORMATION_MESSAGE);
            }
            pan = new JScrollPane(new JTable(data, title));
            this.add(pan);
            repaint();
        } else if (e.getSource() == r) {
            hopGraph.changeFenetre(1);
        }
    }

}
