/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.HopitalGraphique;
import modele.MenuConnexion;
import vue.InterrogationAbs;

/**
 * implementation de la classe qui cree une requete a partir d'un formulaire (
 * hérite de InterrogationAbs et implémente ActionListener )
 *
 * @author Aude et Hugo
 */
public class InterrogationImpl extends InterrogationAbs implements ActionListener {

    /**
     * Attributs privés
     */
    private InterroRequete interroRequete;

    public InterrogationImpl(HopitalGraphique hopGraph) {
        super(hopGraph);
        this.build();
    }

    /**
     * Methode qui instancie la classe avec les actionListener
     */
    private void build() {
        retour.addActionListener(this);
        valider.addActionListener(this);
    }

    /**
     * Methode qui cree la requete en fonction des differentes clauses envoyées
     * en paramètre
     *
     * @param requete SELECT clause et Requete final
     * @param from FROM clause
     * @param critere WHERE by clause
     * @param ordre ORDER BY clause
     */
    private void sendRequete(String requete, String from, String critere, String ordre) {
        if (!" FROM ".equals(from)) {
            requete += from;
        }
        if (!" WHERE ".equals(critere)) {
            requete += critere;
        }
        if (!" ORDER BY ".equals(ordre)) {
            requete += ordre;
        }

        String[] title = null;
        if (!"".equals(requete)) {
            try {

                resultats = MenuConnexion.getConnexion().remplirChampsRequete2(requete);
                title = MenuConnexion.getConnexion().remplirChampsColumn(requete);
            } catch (SQLException ex) {
                Logger.getLogger(InterrogationImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        interroRequete = new InterroRequete(resultats, title);
    }

    /**
     * Surcharge de actionPerformed pour récuperer les données saisies dans le
     * formulaire et créer la requete grâce à sendRequete
     *
     * @param e Action effectuée
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        resultats.removeAll(resultats);
        Object source = e.getSource();
        if (source == valider) {
            String requete = "";
            String from = " FROM ";
            String critere = " WHERE ";
            String ordre = " ORDER BY ";
            String str;
            String agregat;
            String aliasValue;

            for (int i = 0; i < 3; i++) {
                if (!"".equals((String) listesColonnes[i].getSelectedItem())) {
                    str = (String) listesColonnes[i].getSelectedItem();

                    if (!"".equals(requete)) {
                        requete += " , ";
                    } else {
                        requete = "SELECT ";
                    }
                    if (!" FROM ".equals(from) && !from.contains(str.substring(0, str.indexOf(".")))) {
                        from += " , ";
                    }
                    switch ((String) listes[i].getSelectedItem()) {
                        case "Minimum":
                            agregat = "MIN(";
                            break;
                        case "Maximum":
                            agregat = "MAX(";
                            break;
                        case "Nombre de":
                            agregat = "COUNT(";
                            break;
                        case "Moyenne":
                            agregat = "AVG(";
                            break;
                        default:
                            agregat = "";
                    }

                    requete += agregat + str;
                    if (!"".equals(agregat)) {
                        requete += ")";
                    }
                    aliasValue = alias[i].getText();
                    if (!"".equals(aliasValue)) {
                        requete += " AS " + aliasValue + " ";
                    }
                    if (!from.contains(str.substring(0, str.indexOf(".")))) {
                        from += str.substring(0, str.indexOf("."));

                    }
                    if (!"".equals((String) listesCriteres[i].getSelectedItem()) && !"".equals(textCriteres[i].getText())) {

                        if (!" WHERE ".equals(critere)) {
                            if (etOu[i % 2].isSelected()) {
                                critere += " OR ";
                            } else {
                                critere += " AND ";
                            }
                        }
                        critere += " (" + str;
                        String where = textCriteres[i].getText();
                        try {
                            Integer.parseInt(where);
                        } catch (NumberFormatException ex) {
                            where = "'" + where + "'";
                        }
                        switch ((String) listesCriteres[i].getSelectedItem()) {

                            case "Egal à":
                                critere += " = " + where + " ) ";
                                break;
                            case "Different de":
                                critere += " <> " + where + " ) ";
                                break;
                            case "Inferieur à":
                                critere += " < " + where + " ) ";
                                break;
                            case "Superieur à":
                                critere += " > " + where + " ) ";
                                break;
                            case "Superieur ou egal à":
                                critere += " >= " + where + " ) ";
                                break;
                            case "Inferieur ou egal à":
                                critere += " <= " + where + " ) ";
                                break;
                            case "Contient":
                                critere += " LIKE '*" + textCriteres[i].getText() + "*' ) ";
                                break;
                            case "Termine par":
                                critere += " LIKE '*" + textCriteres[i].getText() + "' ) ";
                                break;
                            case "Commence par":
                                critere += " LIKE '" + textCriteres[i].getText() + "*' ) ";
                                break;
                            default:
                                critere += "";
                        }
                    }
                    if (!"".equals((String) listesTri[i].getSelectedItem())) {

                        if (!" ORDER BY ".equals(ordre)) {
                            ordre += " , ";
                        }
                        switch ((String) listesTri[i].getSelectedItem()) {
                            case "Croissant":
                                ordre += str + " ASC ";
                                break;
                            case "Décroissant":
                                ordre += str + " DESC ";
                                break;
                            default:
                                ordre += "";

                        }
                    }
                }
            }

            sendRequete(requete, from, critere, ordre);

        } else if (source == retour) {
            hopGraph.changeFenetre(1);
        }
    }

}
