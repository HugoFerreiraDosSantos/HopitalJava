/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Aude
 */
public class Interrogation extends JPanel implements ActionListener {

    private HopitalGraphique hopGraph;
    private JComboBox listes[];
    private JComboBox listesColonnes[];
    private JComboBox listesCriteres[];
    private JComboBox listesTri[];
    private JTextField textCriteres[];
    private JLabel titles[];
    private JTextField alias[];
    private ArrayList<String> resultats;
    private ArrayList<ArrayList< String>> resultats2;
    private JButton valider;
    private JButton retour;
    private JRadioButton etOu[];
    private JLabel textEtOu[];
    private ButtonGroup groupRadio[];

    public Interrogation(HopitalGraphique hopGraph) {
        resultats = new ArrayList<>();
        resultats2 = new ArrayList<>();
        valider = new JButton("Rechercher");
        retour = new JButton("Retour");
        titles = new JLabel[6];
        alias = new JTextField[3];
        textCriteres = new JTextField[3];
        etOu = new JRadioButton[4];
        textEtOu = new JLabel[4];
        groupRadio = new ButtonGroup[2];
        this.hopGraph = hopGraph;

        this.build();
    }

    private void build() {
        this.setLayout(null);
        try {
            resultats = MenuConnexion.getConnexion().remplirChampsRequete("show tables");
            for (int i = 0; i < resultats.size(); i++) {
                resultats2.add(new ArrayList<>());
                resultats2.set(i, MenuConnexion.getConnexion().remplirChampsRequete("show columns from " + resultats.get(i)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Interrogation.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < 4; i += 2) {

            textEtOu[i] = new JLabel("Ou");
            etOu[i] = new JRadioButton("Ou", true);
            etOu[i].setSelected(true);
            textEtOu[i].setBounds(autoSizeX(0.374 + i * 0.16525), autoSizeY(0.3), autoSizeX(0.02), autoSizeY(0.02));
            etOu[i].setBounds(autoSizeX(0.394 + i * 0.16525), autoSizeY(0.3), autoSizeX(0.02), autoSizeY(0.02));
            this.add(textEtOu[i]);
            this.add(etOu[i]);

            textEtOu[i + 1] = new JLabel("Et");
            etOu[i + 1] = new JRadioButton("Et");
            textEtOu[i + 1].setBounds(autoSizeX(0.374 + i * 0.16525), autoSizeY(0.33), autoSizeX(0.02), autoSizeY(0.02));
            etOu[i + 1].setBounds(autoSizeX(0.394 + i * 0.16525), autoSizeY(0.33), autoSizeX(0.02), autoSizeY(0.02));
            this.add(textEtOu[i + 1]);
            this.add(etOu[i + 1]);

            groupRadio[i / 2] = new ButtonGroup();
            groupRadio[i / 2].add(etOu[i]);
            groupRadio[i / 2].add(etOu[i + 1]);
        }

        int size = 0;

        for (int i = 0; i < resultats2.size(); i++) {
            size += resultats2.get(i).size();
        }

        String[] objects1 = new String[]{"", "Moyenne", "Minimum", "Maximum", "Nombre de"};
        String[] objects2 = new String[size + 1];
        int cpt = 1;
        objects2[0] = "";
        for (int i = 0; i < resultats2.size(); i++) {
            for (int j = 0; j < resultats2.get(i).size(); j++) {
                objects2[cpt++] = resultats.get(i) + '.' + resultats2.get(i).get(j).substring(0, resultats2.get(i).get(j).indexOf(","));
            }
        }
        String[] objects3 = new String[]{"", "Egal à", "Different de", "Inferieur à", "Superieur à", "Inferieur ou egal à", "Superieur ou egal à", "Contient", "Commence par", "Termine par"};
        String[] objects4 = new String[]{"", "Croissant", "Décroissant"};

        listes = new JComboBox[]{new JComboBox(objects1), new JComboBox(objects1), new JComboBox(objects1)};
        listesColonnes = new JComboBox[]{new JComboBox(objects2), new JComboBox(objects2), new JComboBox(objects2)};
        listesCriteres = new JComboBox[]{new JComboBox(objects3), new JComboBox(objects3), new JComboBox(objects3)};
        listesTri = new JComboBox[]{new JComboBox(objects4), new JComboBox(objects4), new JComboBox(objects4)};

        for (int i = 0; i < 3; i++) {
            titles[i] = new JLabel();
            titles[i + 3] = new JLabel();
            this.add(titles[i]);
            this.add(titles[i + 3]);

            alias[i] = new JTextField();
            alias[i].setBounds(autoSizeX(0.0924 + i * 0.33125), autoSizeY(0.22), autoSizeX(0.23125), autoSizeY(0.05));
            this.add(alias[i]);

            listes[i].setBounds(autoSizeX(0.0924 + i * 0.33125), autoSizeY(0.14), autoSizeX(0.23125), autoSizeY(0.05));
            this.add(listes[i]);
            listesColonnes[i].setBounds(autoSizeX(0.0925 + i * 0.33125), autoSizeY(0.06), autoSizeX(0.23125), autoSizeY(0.05));
            this.add(listesColonnes[i]);

            textCriteres[i] = new JTextField();
            textCriteres[i].setBounds(autoSizeX(0.213 + i * 0.33125), autoSizeY(0.30), autoSizeX(0.110), autoSizeY(0.052));
            this.add(textCriteres[i]);

            listesCriteres[i].setBounds(autoSizeX(0.0969 + i * 0.33125), autoSizeY(0.30), autoSizeX(0.115), autoSizeY(0.05));
            this.add(listesCriteres[i]);

            listesTri[i].setBounds(autoSizeX(0.149 + i * 0.33125), autoSizeY(0.38), autoSizeX(0.115), autoSizeY(0.05));
            this.add(listesTri[i]);
        }

        titles[0].setText("Saisissez votre recherche :");
        titles[0].setBounds(autoSizeX(0.419), autoSizeY(0.0083), autoSizeX(0.15), autoSizeY(0.033));

        titles[1].setText("Colonne");
        titles[1].setBounds(autoSizeX(0.005), autoSizeY(0.07), autoSizeX(0.15), autoSizeY(0.033));

        titles[2].setText("Opération");
        titles[2].setBounds(autoSizeX(0.005), autoSizeY(0.15), autoSizeX(0.15), autoSizeY(0.033));

        titles[3].setText("Alias");
        titles[3].setBounds(autoSizeX(0.005), autoSizeY(0.22), autoSizeX(0.15), autoSizeY(0.033));

        titles[4].setText("Critère");
        titles[4].setBounds(autoSizeX(0.005), autoSizeY(0.31), autoSizeX(0.15), autoSizeY(0.033));

        titles[5].setText("Tri");
        titles[5].setBounds(autoSizeX(0.005), autoSizeY(0.39), autoSizeX(0.15), autoSizeY(0.033));

        valider.setBounds(autoSizeX(0.6), autoSizeY(0.6), autoSizeX(0.1), autoSizeY(0.05));
        valider.addActionListener(this);
        retour.setBounds(autoSizeX(0.4), autoSizeY(0.6), autoSizeX(0.1), autoSizeY(0.05));
        retour.addActionListener(this);
        this.add(valider);
        this.add(retour);
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
            ArrayList<String> colStr = new ArrayList<>();

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
                        colStr.add(aliasValue);
                    } else {
                        colStr.add(str);
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

            if (!" FROM ".equals(from)) {
                requete += from;
            }
            if (!" WHERE ".equals(critere)) {
                requete += critere;
            }
            if (!" ORDER BY ".equals(ordre)) {
                requete += ordre;
            }

            String[] cast = new String[colStr.size()];
            if (!"".equals(requete)) {
                try {

                    resultats = MenuConnexion.getConnexion().remplirChampsRequete(requete);

                } catch (SQLException ex) {
                    Logger.getLogger(Interrogation.class.getName()).log(Level.SEVERE, null, ex);
                }

                for (int i = 0; i < colStr.size(); i++) {
                    cast[i] = colStr.get(i);
                }
            }
            InterroRequete interroRequete;
            interroRequete = new InterroRequete(resultats, cast);

        } else if (source == retour) {
            hopGraph.changeFenetre(1);
        }
    }
}
