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
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MenuReporting extends JPanel implements ActionListener {

    private JButton retour;
    private JTabbedPane onglets;

    private HopitalGraphique hopGraph;

    public MenuReporting(HopitalGraphique hop) {

        onglets = new JTabbedPane();
        retour = new JButton("Retour");
        hopGraph = hop;
        this.build();
    }

    private void build() {
        this.setLayout(null);
        onglets.addTab("Docteurs par spécialité", new DocParSpe());
        onglets.addTab("Infirmiers par service", new InfParSer());
        onglets.addTab("Patients par service", new PatParSer());
        onglets.addTab("Maladies les plus contractées", null);
        onglets.setBounds(autoSizeX(0.0625), autoSizeY(0.033), autoSizeX(0.875), autoSizeY(0.783));
        this.add(onglets);

        retour.setBounds(autoSizeX(0.3125), autoSizeY(0.833), autoSizeX(0.375), autoSizeY(0.0833));
        retour.addActionListener(this);
        add(retour);
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
        onglets.setBounds(autoSizeX(0.0625), autoSizeY(0.033), autoSizeX(0.875), autoSizeY(0.783));
        retour.setBounds(autoSizeX(0.3125), autoSizeY(0.833), autoSizeX(0.375), autoSizeY(0.0833));
    }

}
