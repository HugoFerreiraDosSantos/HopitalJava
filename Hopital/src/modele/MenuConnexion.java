/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import controleur.Connexion;
import static controleur.SaveAndLoad.load;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import vue.MenuConnexionAbs;

/**
 * Class qui gere le menu de connexion de l'application ( modele de
 * MenuConnexionAbs )
 *
 * @author Aude et Hugo
 */
public class MenuConnexion extends MenuConnexionAbs implements ActionListener {

    public MenuConnexion(HopitalGraphique hop) {
        super(hop);
        this.build();

    }

    private void build() {
        valider.addActionListener(this);
        quitter.addActionListener(this);
    }

    public boolean connect(boolean bool_locale) {
        try {
            if (bool_locale) {
                db = new Connexion(test[0].getText(), test[1].getText(), test[2].getText());

            } else {
                db = new Connexion(test[3].getText(), test[4].getText(), test[5].getText(), test[6].getText());
            }
        } catch (SQLException | ClassNotFoundException ex) {
            if (!"Erreur Connection A Distance".equals(ex.getMessage())) {
                JOptionPane.showMessageDialog(this, "Erreur Connexion Locale", "Erreur Connexion", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur Connexion", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }

        return true;
    }

    public boolean getLocal() {
        return locale.isSelected();
    }

    /**
     * Permet de quitter le logiciel ou de choisir la connexion adapte
     *
     * @param e Action effectue
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == valider) {
            if (connect(locale.isSelected())) {
                load();
                hopGraph.changeFenetre(1);
            } else {
                hopGraph.changeFenetre(0);
            }
        } else if (e.getSource() == quitter) {
            System.exit(0);
        }
    }

    /**
     * Methode static qui retourne la connexion
     *
     * @return Instance de connexion
     */
    public static Connexion getConnexion() {
        return db;
    }

}
