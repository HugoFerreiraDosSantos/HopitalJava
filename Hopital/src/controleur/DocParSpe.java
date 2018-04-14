/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.Graphics;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import static modele.HopitalGraphique.*;
import modele.MenuConnexion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * Classe qui permet la recherche du nombre de docteurs par specialite dans la
 * base ( herite de JPanel )
 *
 * @author Aude et Hugo
 */
public class DocParSpe extends JPanel {

    private ArrayList<String> resultats;
    private JPanel pan;

    public DocParSpe() {
        resultats = new ArrayList<>();
        this.setLayout(null);
        pan = createDemoPanel();
        pan.setBounds(autoSizeX(0.0), autoSizeY(0.0), autoSizeX(0.86875), autoSizeY(0.725));
        this.add(pan);
    }

    /**
     *
     * @return Le graph cree
     */
    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        if (MenuConnexion.getConnexion() != null) {
            try {
                resultats = MenuConnexion.getConnexion().remplirChampsRequete2("SELECT count(numero), specialite FROM docteur GROUP BY (specialite)");
            } catch (SQLException ex) {
                Logger.getLogger(Hopital.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Erreur");
        }

        int value;
        String label;
        for (int i = 0; i < resultats.size(); i++) {
            value = Integer.parseInt(resultats.get(i).substring(0, resultats.get(i).indexOf(";")));
            label = resultats.get(i).substring(resultats.get(i).indexOf(";") + 1);

            dataset.setValue(label, new Double(value));
        }

        return dataset;
    }

    /**
     *
     * @param dataset parametre du graphe
     * @return Graph cree et parametre
     */
    private JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Nombre de docteurs par spécialité", // chart title
                dataset, // data
                false, // include legend
                true,
                false);

        return chart;
    }

    /**
     *
     * @return Instance du graph
     */
    private JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    /**
     * Surchage de paint pour adapter le Graph à l'écran
     *
     * @param g Instance de Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pan.setBounds(autoSizeX(0.0), autoSizeY(0.0), autoSizeX(0.86875), autoSizeY(0.725));
    }

}
