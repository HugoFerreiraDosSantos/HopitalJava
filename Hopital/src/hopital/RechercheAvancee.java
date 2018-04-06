/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author PPC
 */
public class RechercheAvancee extends JPanel {

    private ArrayList<String> resultats;
    private String requete;

    public RechercheAvancee(String requete) {
        resultats = new ArrayList<>();
        this.requete = requete;
        this.build();
    }

    private void build() {

        if (!"".equals(requete)) {

            if (MenuConnexion.getConnexion() != null) {
                try {
                    resultats = MenuConnexion.getConnexion().remplirChampsRequete(requete);
                } catch (SQLException ex) {
                    Logger.getLogger(Hopital.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Erreur");
            }

            String res = "";
            res = resultats.stream().map((resultat) -> resultat + " ").reduce(res, String::concat);
            JLabel text = new JLabel(res);
            this.add(text);
            JOptionPane.showMessageDialog(this, this, "Résultat de la requête", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
