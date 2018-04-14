/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.MenuEditionAbs;

/**
 * Class qui gere le menu d'edition de l'application ( modele de MenuEditionAbs
 * )
 *
 * @author Aude et Hugo
 */
public class MenuEdition extends MenuEditionAbs implements ActionListener {

    public MenuEdition(HopitalGraphique hop) {
        super(hop);
        this.build();
    }

    private void build() {
        retour.addActionListener(this);
    }

    /**
     * Permet de revenir a la page précedente
     *
     * @param e Action effectue
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retour) {
            hopGraph.changeFenetre(4);
        }

    }

}
