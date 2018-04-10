/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.*;
import java.awt.Color;
import java.awt.Graphics;
import static javafx.scene.layout.BorderStrokeStyle.SOLID;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author PPC
 */
public abstract class RechercheAvanceeAbs extends JPanel {

    protected JButton b;
    protected JButton r;
    protected JTextArea t;
    protected HopitalGraphique hopGraph;

    public RechercheAvanceeAbs(HopitalGraphique hop) {

        r = new JButton("Retour");
        b = new JButton("Rechercher");
        t = new JTextArea(8,50);
        this.hopGraph = hop;
        this.build();
    }

    private void build() {

        this.setLayout(null);
        t.setLineWrap(true);
        t.setAlignmentX(CENTER_ALIGNMENT);
        t.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(b);
        this.add(t);
        this.add(r);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        b.setBounds(autoSizeX(0.5), autoSizeY(0.6), autoSizeX(0.15), autoSizeY(0.05));
        r.setBounds(autoSizeX(0.345), autoSizeY(0.6), autoSizeX(0.1), autoSizeY(0.05));
        t.setBounds(autoSizeX(0.35), autoSizeY(0.25), autoSizeX(0.3), autoSizeY(0.2));
    }
}
