/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterrogationImpl extends InterrogationAbs implements ActionListener {

    private InterroRequete interroRequete;

    public InterrogationImpl(HopitalGraphique hopGraph) {
        super(hopGraph);
        this.build();
    }

    private void build() {
        retour.addActionListener(this);
        valider.addActionListener(this);
    }

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
                Logger.getLogger(Interrogation.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        interroRequete = new InterroRequete(resultats, title);
    }

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
