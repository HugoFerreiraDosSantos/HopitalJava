/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author PPC
 */
public class MenuConnexion extends JPanel implements ActionListener {

    private ButtonGroup bg;
    private JRadioButton locale;
    private JRadioButton distante;
    private JPanel pan_locale;
    private JPanel pan_distante;

    private JButton valider;
    private static Connexion db;
    private JTextField test[];
    private HopitalGraphique hopGraph;

    public MenuConnexion(HopitalGraphique hop) {
        hopGraph = hop;
        db = null;
        pan_locale = new JPanel();
        pan_distante = new JPanel();
        bg = new ButtonGroup();
        locale = new JRadioButton("Locale", true);
        distante = new JRadioButton("Distante");
        valider = new JButton("Valider");
        test = new JTextField[7];
        this.build();

    }

    private void build() {

        bg.add(locale);
        bg.add(distante);

        this.setLayout(null);
        pan_locale.add(locale);
        test[0] = new JTextField();
        test[0].setToolTipText("NameDatabase");
        test[1] = new JTextField();
        test[1].setToolTipText("LoginDatabase");
        test[2] = new JPasswordField();
        test[2].setToolTipText("PasswordDatabase");
        for (int i = 0; i < 3; i++) {
            pan_locale.add(test[i]);
            test[i].setPreferredSize(new Dimension(autoSizeX(0.1), autoSizeY(0.05)));
        }
        pan_locale.setBounds(autoSizeX(0.15), autoSizeY(0.5), autoSizeX(0.5), autoSizeY(0.0667));
        this.add(pan_locale);

        pan_distante.add(distante);
        test[3] = new JTextField();
        test[3].setToolTipText("UsernameECE");
        test[4] = new JPasswordField();
        test[4].setToolTipText("PasswordECE");
        test[5] = new JTextField();
        test[5].setToolTipText("LoginDatabase");
        test[6] = new JPasswordField();
        test[6].setToolTipText("PasswordDatabase");

        for (int i = 3; i < 7; i++) {
            pan_distante.add(test[i]);
            test[i].setPreferredSize(new Dimension(autoSizeX(0.1), autoSizeY(0.05)));
        }
        pan_distante.setBounds(autoSizeX(0.14375), autoSizeY(0.6167), autoSizeX(0.625), autoSizeY(0.0667));
        this.add(pan_distante);

        valider.setBounds(autoSizeX(0.43125), autoSizeY(0.7833), autoSizeX(0.125), autoSizeY(0.0667));
        this.add(valider);
        valider.addActionListener(this);
    }

    public boolean connect(boolean bool_locale) {
        try {
            if (bool_locale) {
                //db = new Connexion(test[0].getText(), test[1].getText(), test[2].getText());
                db = new Connexion("hopital", "root", test[2].getText());
                System.out.println("locale");

            } else {
                db = new Connexion(test[3].getText(), test[4].getText(), test[5].getText(), test[6].getText());
                System.out.println("distante");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == valider) {
            if (connect(locale.isSelected())) {
                hopGraph.changeFenetre(1);
            }
        }
    }

    public static Connexion getConnexion() {
        return db;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            g.drawImage(ImageIO.read(new File("connexion.png")), 0, 0, autoSizeX(1.0), autoSizeY(1.0), this);
        } catch (IOException ex) {
            Logger.getLogger(MenuConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < 3; i++) {
            test[i].setPreferredSize(new Dimension(autoSizeX(0.1), autoSizeY(0.05)));
        }
        pan_locale.setBounds(autoSizeX(0.15), autoSizeY(0.5), autoSizeX(0.5), autoSizeY(0.0667));

        for (int i = 3; i < 7; i++) {
            test[i].setPreferredSize(new Dimension(autoSizeX(0.1), autoSizeY(0.05)));
        }

        pan_distante.setBounds(autoSizeX(0.14375), autoSizeY(0.6167), autoSizeX(0.625), autoSizeY(0.0667));

        valider.setBounds(autoSizeX(0.43125), autoSizeY(0.7833), autoSizeX(0.125), autoSizeY(0.0667));

    }

}
