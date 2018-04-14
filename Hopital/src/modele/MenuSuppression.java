/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.MenuSuppressionAbs;

/**
 * Class qui gere le menu de suppression de l'application ( modele de
 * MenuSuppressionAbs )
 *
 * @author Aude et Hugo
 */
public class MenuSuppression extends MenuSuppressionAbs implements ActionListener {

    public MenuSuppression(HopitalGraphique hop) {
        super(hop);
        this.build();
    }

    private void build() {

        retour.addActionListener(this);

    }

    /**
     * Methode qui permet de revenir à la page précédente
     *
     * @param e Action effectuée
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retour) {
            hopGraph.changeFenetre(4);
        }

    }

}
