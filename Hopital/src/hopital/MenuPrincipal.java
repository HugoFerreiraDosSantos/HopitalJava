/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author PPC
 */
public class MenuPrincipal extends JPanel implements ActionListener {

    private final JButton retour;
    private final HopitalGraphique hopGraph;
    private final JButton reporting;
    private final JButton interrogation;
    private final JButton miseAJour;
    private final ImagePan back;

    public MenuPrincipal(HopitalGraphique hop) {
        hopGraph = hop;
        retour = new JButton("Retour");
        reporting = new JButton("Reporting");
        miseAJour = new JButton("Mise à jour");
        interrogation = new JButton("Recherche");
        back = new ImagePan("image.jpg");
        this.build();
    }

    private void build() {
        this.setLayout(null);
        reporting.addActionListener(this);
        this.add(reporting);

        miseAJour.addActionListener(this);
        this.add(miseAJour);

        interrogation.addActionListener(this);
        this.add(interrogation);

        retour.addActionListener(this);
        this.add(retour);
        this.add(back);
        revalidate();
        repaint();
    }

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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        try {
            g.drawImage(ImageIO.read(new File("principale.png")), 0, 0, autoSizeX(1.0), autoSizeY(1.0), this);
        } catch (IOException ex) {
            Logger.getLogger(MenuConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        reporting.setBounds(autoSizeX(0.368), autoSizeY(0.25), autoSizeX(0.25), autoSizeY(0.116));
        miseAJour.setBounds(autoSizeX(0.36875), autoSizeY(0.416), autoSizeX(0.25), autoSizeY(0.116));
        interrogation.setBounds(autoSizeX(0.36875), autoSizeY(0.5833), autoSizeX(0.25), autoSizeY(0.116));
        retour.setBounds(autoSizeX(0.36875), autoSizeY(0.75), autoSizeX(0.25), autoSizeY(0.116));

        back.setBounds(0, 0, autoSizeX(1.0), autoSizeY(1.0));
        reporting.setBackground(Color.PINK);
        miseAJour.setBackground(Color.PINK);
        interrogation.setBackground(Color.PINK);
        retour.setBackground(Color.PINK);
    }

}
