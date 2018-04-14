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
 * @author Aude
 */
public class MenuMisAJour extends JPanel implements ActionListener {

    private final JButton retour;
    private final HopitalGraphique hopGraph;
    private final JButton ajout;
    private final JButton suppression;
    private final JButton modification;
    private final ImagePan back;

    public MenuMisAJour(HopitalGraphique hop) {
        hopGraph = hop;
        retour = new JButton("Retour");
        ajout = new JButton("Ajout");
        modification = new JButton("Modification");
        suppression = new JButton("Suppression");
        back = new ImagePan("image.jpg");
        this.build();
    }

    private void build() {
        this.setLayout(null);


        ajout.addActionListener(this);
        this.add(ajout);

        modification.addActionListener(this);
        this.add(modification);

        suppression.addActionListener(this);
        this.add(suppression);

        retour.addActionListener(this);
        this.add(retour);
        this.add(back);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retour) {
            hopGraph.changeFenetre(1);
        } else if (e.getSource() == ajout) {
            hopGraph.changeFenetre(5);
        } else if (e.getSource() == modification) {
            hopGraph.changeFenetre(6);
        } else if (e.getSource() == suppression) {
            hopGraph.changeFenetre(7);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        try {
            g.drawImage(ImageIO.read(new File("maj.png")), 0, 0, autoSizeX(1.0), autoSizeY(1.0), this);
        } catch (IOException ex) {
            Logger.getLogger(MenuConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        back.setBounds(0, 0, autoSizeX(1.0), autoSizeY(1.0));
        ajout.setBounds(autoSizeX(0.368), autoSizeY(0.25), autoSizeX(0.25), autoSizeY(0.116));
        modification.setBounds(autoSizeX(0.36875), autoSizeY(0.416), autoSizeX(0.25), autoSizeY(0.116));
        suppression.setBounds(autoSizeX(0.36875), autoSizeY(0.5833), autoSizeX(0.25), autoSizeY(0.116));
        retour.setBounds(autoSizeX(0.36875), autoSizeY(0.75), autoSizeX(0.25), autoSizeY(0.116));

        ajout.setBackground(Color.PINK);
        modification.setBackground(Color.PINK);
        suppression.setBackground(Color.PINK);
        retour.setBackground(Color.PINK);
    }

}
