/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.*;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PPC
 */
public abstract class RechercheAvancee extends JPanel {

    protected JButton b;
    protected JTextField t;
    protected HopitalGraphique hopGraph;

    public RechercheAvancee(HopitalGraphique hop) {

        b = new JButton("test");
        t = new JTextField();
        this.hopGraph = hop;
        this.build();
    }

    private void build() {

        this.setLayout(null);
        b.setBounds(autoSizeX(0.2), autoSizeY(0.2), autoSizeX(0.3), autoSizeY(0.3));
        t.setBounds(autoSizeX(0.2), autoSizeY(0.5), autoSizeX(0.3), autoSizeY(0.3));
        this.add(b);
        this.add(t);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        b.setBounds(autoSizeX(0.2), autoSizeY(0.2), autoSizeX(0.3), autoSizeY(0.3));
        t.setBounds(autoSizeX(0.2), autoSizeY(0.5), autoSizeX(0.3), autoSizeY(0.3));
    }
}
