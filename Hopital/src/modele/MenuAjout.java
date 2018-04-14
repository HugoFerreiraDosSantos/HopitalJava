/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class qui gere le menu d'ajout de l'application ( modele de MenuAjoutAbs )
 *
 * @author Aude et Hugo
 */
public class MenuAjout extends vue.MenuAjoutAbs implements ActionListener {

    public MenuAjout(HopitalGraphique hop) {
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
