/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class RechercheAvanceeImpl extends RechercheAvanceeAbs implements ActionListener {

    private InterroRequete interroRequete;
    private String requete;
    protected ArrayList<String> resultats;

    public RechercheAvanceeImpl(HopitalGraphique hop) {
        super(hop);
        resultats = new ArrayList<>();
        this.build();
    }

    private void build() {
        b.addActionListener(this);
        r.addActionListener(this);

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

            interroRequete = new InterroRequete(resultats, title);

        }
        else if (e.getSource() == r)
        {
            hopGraph.changeFenetre(1);
        }

    }

}
