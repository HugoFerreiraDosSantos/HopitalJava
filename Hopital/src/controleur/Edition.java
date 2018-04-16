/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modele.HopitalGraphique;
import modele.MenuConnexion;
import static modele.MenuConnexion.getConnexion;

/**
 * Classe qui permet la modification de la base ( hérite de JPanel et implémente
 * ActionListener )
 *
 * @author Aude et Hugo
 */
public class Edition extends JPanel implements ActionListener {

    /**
     * Attributs privés
     */
    private final HopitalGraphique hopGraph;
    private JTable table;
    private JButton edition;
    private DefaultTableModel tm;
    private ArrayList<String> resultats;
    private String[] title;
    private Object data[][];
    private String tableName;
    private JPanel princ;

    /**
     *
     * @param hop Instance de HopitalGraphique
     * @param tabName Nom de la table
     */
    public Edition(HopitalGraphique hop, String tabName) {
        this.tableName = tabName;
        this.hopGraph = hop;
        edition = new JButton("Modifier");
        princ = new JPanel();
        this.build();
    }

    /**
     * Methode qui instancie la classe
     */
    private void build() {
        this.setLayout(new GridLayout(1, 2));
        resultats = new ArrayList<>();
        title = null;
        try {
            resultats = MenuConnexion.getConnexion().remplirChampsRequete("SELECT * FROM " + tableName);
            title = MenuConnexion.getConnexion().remplirChampsColumn("SELECT * FROM " + tableName);

        } catch (SQLException ex) {
        }

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
        tm = new DefaultTableModel(data, title);

        table = new JTable(tm);

        this.add(new JScrollPane(table));
        this.add(princ);
        princ.add(edition);
        edition.addActionListener(this);
    }

    /**
     * Surcharge de actionPerformed pour modifier la base en fonction des
     * requetes formulees
     *
     * @param e Action effectuée
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == edition) {
            boolean modif = false;
            for (int i = 0; i < resultats.size(); i++) {
                for (int j = 0; j < title.length; j++) {
                    if (!((String) tm.getValueAt(i, j)).equals((String) data[i][j])) {
                        modif = true;
                        String req = "UPDATE " + tableName + " SET " + title[0] + " = ";
                        String where = " WHERE " + title[0] + " = ";
                        try {
                            Integer.parseInt((String) tm.getValueAt(i, 0));
                            req += tm.getValueAt(i, 0);

                        } catch (NumberFormatException ex) {
                            req += "'" + tm.getValueAt(i, 0) + "'";
                        }
                        try {
                            Integer.parseInt((String) data[i][0]);
                            where += data[i][0];

                        } catch (NumberFormatException ex) {
                            where += "'" + data[i][0] + "'";
                        }
                        data[i][0] = (String) tm.getValueAt(i, 0);
                        for (int k = 1; k < title.length; k++) {
                            req += " , " + title[k] + " = ";
                            where += " AND  " + title[k] + " = ";
                            try {
                                Integer.parseInt((String) tm.getValueAt(i, k));
                                req += tm.getValueAt(i, k);

                            } catch (NumberFormatException ex) {
                                req += "'" + tm.getValueAt(i, k) + "'";
                            }
                            try {
                                Integer.parseInt((String) data[i][k]);
                                where += data[i][k];

                            } catch (NumberFormatException ex) {
                                where += "'" + data[i][k] + "'";
                            }
                            data[i][k] = (String) tm.getValueAt(i, k);
                        }
                        req += " " + where;
                        System.out.println(req);
                        try {
                            getConnexion().executeUpdate(req);
                        } catch (SQLException ex) {
                            Logger.getLogger(Edition.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
            if (modif) {
                JOptionPane.showMessageDialog(this, "Modification(s) réussie(s)");
            } else {
                JOptionPane.showMessageDialog(this, "Aucune modification");
            }
        }

    }

}
