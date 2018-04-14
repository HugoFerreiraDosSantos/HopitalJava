/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.*;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Aude
 */
public abstract class InterrogationAbs extends JPanel {

    protected HopitalGraphique hopGraph;
    protected JComboBox listes[];
    protected JComboBox listesColonnes[];
    protected JComboBox listesCriteres[];
    protected JComboBox listesTri[];
    protected JTextField textCriteres[];
    protected JTextField alias[];
    protected ArrayList<String> resultats;
    protected ArrayList<ArrayList< String>> resultats2;
    protected JButton valider;
    protected JButton retour;
    protected JRadioButton etOu[];
    protected ButtonGroup groupRadio[];
    private ImagePan back;

    public InterrogationAbs(HopitalGraphique hopGraph) {
        resultats = new ArrayList<>();
        back = new ImagePan("image.jpg");
        resultats2 = new ArrayList<>();
        valider = new JButton("Rechercher");
        retour = new JButton("Retour");
        alias = new JTextField[3];
        textCriteres = new JTextField[3];
        etOu = new JRadioButton[4];
        groupRadio = new ButtonGroup[2];
        this.hopGraph = hopGraph;

        this.build();
    }

    private void build() {
        this.setLayout(null);
        try {
            resultats = MenuConnexion.getConnexion().remplirChampsRequete2("show tables");
            for (int i = 0; i < resultats.size(); i++) {
                resultats2.add(new ArrayList<>());
                resultats2.set(i, MenuConnexion.getConnexion().remplirChampsRequete2("show columns from " + resultats.get(i)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(InterrogationAbs.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < 4; i += 2) {

            etOu[i] = new JRadioButton("Ou", true);
            etOu[i].setSelected(true);
            this.add(etOu[i]);

            etOu[i + 1] = new JRadioButton("Et");
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
                objects2[cpt++] = resultats.get(i) + '.' + resultats2.get(i).get(j).substring(0, resultats2.get(i).get(j).indexOf(";"));
            }
        }
        String[] objects3 = new String[]{"", "Egal à", "Different de", "Inferieur à", "Superieur à", "Inferieur ou egal à", "Superieur ou egal à", "Contient", "Commence par", "Termine par"};
        String[] objects4 = new String[]{"", "Croissant", "Décroissant"};

        listes = new JComboBox[]{new JComboBox(objects1), new JComboBox(objects1), new JComboBox(objects1)};
        listesColonnes = new JComboBox[]{new JComboBox(objects2), new JComboBox(objects2), new JComboBox(objects2)};
        listesCriteres = new JComboBox[]{new JComboBox(objects3), new JComboBox(objects3), new JComboBox(objects3)};
        listesTri = new JComboBox[]{new JComboBox(objects4), new JComboBox(objects4), new JComboBox(objects4)};

        for (int i = 0; i < 3; i++) {
            alias[i] = new JTextField();
            this.add(alias[i]);

            listes[i].setBounds(autoSizeX(0.0925 + i * 0.33125), autoSizeY(0.14), autoSizeX(0.23125), autoSizeY(0.05));
            this.add(listes[i]);

            listesColonnes[i].setBounds(autoSizeX(0.0925 + i * 0.32), autoSizeY(0.06), autoSizeX(0.23125), autoSizeY(0.05));
            this.add(listesColonnes[i]);

            textCriteres[i] = new JTextField();
            this.add(textCriteres[i]);

            listesCriteres[i].setBounds(autoSizeX(0.0969 + i * 0.32), autoSizeY(0.30), autoSizeX(0.115), autoSizeY(0.05));
            this.add(listesCriteres[i]);

            listesTri[i].setBounds(autoSizeX(0.149 + i * 0.32), autoSizeY(0.38), autoSizeX(0.115), autoSizeY(0.05));
            this.add(listesTri[i]);
        }

        this.add(valider);
        this.add(retour);
        this.add(back);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            g.drawImage(ImageIO.read(new File("formulaire.png")), 0, 0, autoSizeX(1.0), autoSizeY(1.0), this);
        } catch (IOException ex) {
            Logger.getLogger(InterrogationAbs.class.getName()).log(Level.SEVERE, null, ex);
        }
        back.setBounds(autoSizeX(0.0), autoSizeY(0.0), autoSizeX(1.0), autoSizeY(1.0));
        for (int i = 0; i < 3; i++) {
            alias[i].setBackground(Color.WHITE);
            listes[i].setBackground(Color.WHITE);
            listesColonnes[i].setBackground(Color.WHITE);
            textCriteres[i].setBackground(Color.WHITE);
            listesCriteres[i].setBackground(Color.WHITE);
            listesTri[i].setBackground(Color.WHITE);
            
            alias[i].setBounds(autoSizeX(0.0925 + i * 0.32), autoSizeY(0.22), autoSizeX(0.23125), autoSizeY(0.05));
            listes[i].setBounds(autoSizeX(0.0925 + i * 0.32), autoSizeY(0.14), autoSizeX(0.23125), autoSizeY(0.05));
            listesColonnes[i].setBounds(autoSizeX(0.0925 + i * 0.32), autoSizeY(0.06), autoSizeX(0.23125), autoSizeY(0.05));
            textCriteres[i].setBounds(autoSizeX(0.213 + i * 0.32), autoSizeY(0.30), autoSizeX(0.110), autoSizeY(0.052));
            listesCriteres[i].setBounds(autoSizeX(0.0969 + i * 0.32), autoSizeY(0.30), autoSizeX(0.115), autoSizeY(0.05));
            listesTri[i].setBounds(autoSizeX(0.149 + i * 0.32), autoSizeY(0.38), autoSizeX(0.115), autoSizeY(0.05));
        }
        for (int i = 0; i < 4; i += 2) {
            etOu[i].setBounds(autoSizeX(0.394 + i * 0.16), autoSizeY(0.3), autoSizeX(0.02), autoSizeY(0.02));
            etOu[i + 1].setBounds(autoSizeX(0.394 + i * 0.16), autoSizeY(0.33), autoSizeX(0.02), autoSizeY(0.02));
        }
        valider.setBounds(autoSizeX(0.545), autoSizeY(0.6), autoSizeX(0.15), autoSizeY(0.05));
        valider.setBackground(Color.PINK);
        retour.setBounds(autoSizeX(0.345), autoSizeY(0.6), autoSizeX(0.1), autoSizeY(0.05));
        retour.setBackground(Color.PINK);
    }

}
