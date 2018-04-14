/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.*;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author PPC
 */
public abstract class RechercheAvanceeAbs extends JPanel {

    protected JButton b;
    protected JButton r;
    protected JTextArea t;
    protected HopitalGraphique hopGraph;
    protected JScrollPane pan;
    private ImagePan back;

    public RechercheAvanceeAbs(HopitalGraphique hop) {

        r = new JButton("Retour");
        b = new JButton("Rechercher");
        t = new JTextArea(8, 50);
        
        back = new ImagePan("image.jpg");

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

        Object obj[][];
        String str[];
        obj = new Object[1][1];
        obj[0][0] = " ";
        str = new String[1];
        str[0] = "Aucun résultat";

        pan = new JScrollPane(new JTable(obj, str));
        this.add(pan);
        this.add(back);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        back.setBounds(autoSizeX(0.0), autoSizeY(0.0), autoSizeX(1.0), autoSizeY(1.0));
        pan.setBounds(autoSizeX(0.42), autoSizeY(0.05), autoSizeX(0.5), autoSizeY(0.78));
        b.setBounds(autoSizeX(0.2), autoSizeY(0.6), autoSizeX(0.15), autoSizeY(0.05));
        r.setBounds(autoSizeX(0.05), autoSizeY(0.6), autoSizeX(0.1), autoSizeY(0.05));
        r.setBackground(Color.PINK);
        b.setBackground(Color.PINK);
        t.setBounds(autoSizeX(0.05), autoSizeY(0.25), autoSizeX(0.3), autoSizeY(0.2));
    }
}
