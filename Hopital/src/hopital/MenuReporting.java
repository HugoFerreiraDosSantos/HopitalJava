/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

/**
 *
 * @author PPC
 */
import static hopital.HopitalGraphique.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MenuReporting extends JPanel implements ActionListener {

    private JButton retour;
    private JTabbedPane onglets;
    private final ImagePan back;
    private HopitalGraphique hopGraph;

    public MenuReporting(HopitalGraphique hop) {

        onglets = new JTabbedPane();
        retour = new JButton("Retour");
        hopGraph = hop;
        back = new ImagePan("image.jpg");
        this.build();
    }

    private void build() {
        this.setLayout(null);
        onglets.addTab("Docteurs par spécialité", new DocParSpe());
        onglets.addTab("Infirmiers par service", new InfParSer());
        onglets.addTab("Patients par service", new PatParSer());
        onglets.addTab("Maladies les plus contractées par sexe", new MalParSexe());
        onglets.setBackground(Color.PINK);
        this.add(onglets);
        this.add(retour);
        this.add(back);

        retour.addActionListener(this);

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retour) {
            hopGraph.changeFenetre(1);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        back.setBounds(0, 0, autoSizeX(1.0), autoSizeY(1.0));
        onglets.setBounds(autoSizeX(0.0625), autoSizeY(0.033), autoSizeX(0.875), autoSizeY(0.783));
        retour.setBounds(autoSizeX(0.3125), autoSizeY(0.833), autoSizeX(0.375), autoSizeY(0.0833));
        retour.setBackground(Color.PINK);
    }

}
