/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import static controleur.SaveAndLoad.save;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modele.HopitalGraphique;
import modele.MenuConnexion;
import static modele.MenuConnexion.getConnexion;

/**
 * Classe qui permet la suppression d'une ligne dans la base ( herite de JPanel
 * et implémente ActionListener )
 *
 * @author Aude et Hugo
 */
public class Suppression extends JPanel implements ActionListener {

    /**
     * Attributs privés
     */
    private final HopitalGraphique hopGraph;
    private JTable table;
    private JButton suppression;
    private DefaultTableModel tm;
    private ArrayList<String> resultats;
    private String[] title;
    private Object data[][];
    private String tableName;
    private int selectData;
    private JPanel princ;

    /**
     *
     * @param hop Instance d'HopitalGraphique
     * @param tabName Nom de la table
     */
    public Suppression(HopitalGraphique hop, String tabName) {
        selectData = 0;
        this.tableName = tabName;
        this.hopGraph = hop;
        suppression = new JButton("Supprimer");
        princ = new JPanel();
        this.build();
    }

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

        table = new JTable(tm) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        this.add(new JScrollPane(table));
        this.add(princ);
        princ.add(suppression);
        suppression.addActionListener(this);
    }

    /**
     * Surchage de ActionPerformed : Supprime une ligne ou plusieurs dans la
     * base en fonction de la ou les lignes selectionnées
     *
     * @param e action effectue
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == suppression) {
            int[] selectedRows = table.getSelectedRows();
            if (selectedRows.length != 0) {
                int cptSup = 0;
                for (int select : selectedRows) {
                    String req = "DELETE FROM " + tableName + " WHERE " + title[0] + " = ";
                    try {
                        Integer.parseInt((String) data[select + selectData][0]);
                        req += data[select + selectData][0];

                    } catch (NumberFormatException ex) {
                        req += "'" + data[select + selectData][0] + "'";
                    }

                    for (int i = 1; i < data[select + selectData].length; i++) {
                        req += " AND " + title[i] + " = ";
                        try {
                            Integer.parseInt((String) data[select + selectData][i]);
                            req += data[select + selectData][i];

                        } catch (NumberFormatException ex) {
                            req += "'" + data[select + selectData][i] + "'";
                        }
                    }
                    try {
                        getConnexion().executeUpdate(req);
                        save(req);
                    } catch (SQLException ex) {
                        Logger.getLogger(Suppression.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    tm.removeRow(select - cptSup++);
                }
                selectData += cptSup;
            }
        }

    }

}
