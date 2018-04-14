/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.MenuPrincipalAbs;

/**
 * Class qui gere le menu de mise a jour de l'application ( modele de
 * MenuMiseAJourAbs )
 *
 * @author Aude et Hugo
 */
public class MenuPrincipal extends MenuPrincipalAbs implements ActionListener {

    public MenuPrincipal(HopitalGraphique hop) {
        super(hop);
        this.build();
    }

    private void build() {

        reporting.addActionListener(this);

        miseAJour.addActionListener(this);

        interrogation.addActionListener(this);

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
            hopGraph.changeFenetre(0);
        } else if (e.getSource() == reporting) {
            hopGraph.changeFenetre(2);
        } else if (e.getSource() == interrogation) {
            hopGraph.changeFenetre(3);
        } else if (e.getSource() == miseAJour) {
            hopGraph.changeFenetre(4);
        }
    }

}
