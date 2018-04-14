/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.MenuMisAJourAbs;

/**
 * Class qui gere le menu de mise a jour de l'application ( modele de
 * MenuMiseAJourAbs )
 *
 * @author Aude et Hugo
 */
public class MenuMisAJour extends MenuMisAJourAbs implements ActionListener {

    public MenuMisAJour(HopitalGraphique hop) {
        super(hop);

        this.build();
    }

    private void build() {

        ajout.addActionListener(this);
        modification.addActionListener(this);
        suppression.addActionListener(this);
        retour.addActionListener(this);
    }

    /**
     * Methode qui permet d'acceder à la page adequate
     *
     * @param e Action effectuée
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retour) {
            hopGraph.changeFenetre(1);
        } else if (e.getSource() == ajout) {
            hopGraph.changeFenetre(5);
        } else if (e.getSource() == modification) {
            hopGraph.changeFenetre(6);
        } else if (e.getSource() == suppression) {
            hopGraph.changeFenetre(7);
        }
    }

}
