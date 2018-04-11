/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author PPC
 */
public class InterroRequete extends JPanel {

    private ArrayList<String> resultats;
    private String title[];

    public InterroRequete(ArrayList<String> resultats, String[] title) {
        super();
        this.title = title;
        this.resultats = resultats;
        build();

    }

    private void build() {

        if (!resultats.isEmpty()) {
            int cpt = 0;
            for (int i = 0; i < resultats.get(0).length(); i++) {
                if (resultats.get(0).charAt(i) == ';') {
                    cpt++;
                }
            }

            Object data[][] = new Object[resultats.size()][cpt + 1];
            for (int i = 0; i < resultats.size(); i++) {
                int j = 0;
                while (resultats.get(i).contains(";")) {
                    int pos = resultats.get(i).indexOf(";");
                    data[i][j++] = resultats.get(i).substring(0, pos);
                    resultats.set(i, resultats.get(i).substring(pos + 1));
                }
                data[i][j] = resultats.get(i);
            }
            
            this.add(new JScrollPane(new JTable(data, title)));
            
            JOptionPane.showMessageDialog(this, this, "Résultat de la requête", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, new JLabel("Aucun résultat"), "Résultat de la requête", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
