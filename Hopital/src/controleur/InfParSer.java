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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Classe qui permet la recherche du nombre d'infirmiers par service dans la
 * base ( herite de JPanel )
 *
 * @author Aude et Hugo
 */
public class InfParSer extends JPanel {

    private ArrayList<String> resultats;
    private JPanel pan;

    public InfParSer() {
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
    private CategoryDataset createDataset() {
        if (MenuConnexion.getConnexion() != null) {
            try {
                resultats = MenuConnexion.getConnexion().remplirChampsRequete("SELECT count(numero), code_service FROM infirmier WHERE rotation = 'JOUR' GROUP BY (code_service) UNION ALL SELECT count(numero), code_service FROM infirmier WHERE rotation = 'NUIT' GROUP BY (code_service)");
            } catch (SQLException ex) {
                Logger.getLogger(Hopital.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Erreur");
        }

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String str;
        int demiSize = resultats.size() / 2;
        for (int i = 0; i < demiSize; i++) {
            str = resultats.get(i);
            dataset.addValue(Integer.parseInt(str.substring(0, str.indexOf(";"))), "JOUR", str.substring(str.indexOf(";") + 1));
            str = resultats.get(i + demiSize);
            dataset.addValue(Integer.parseInt(str.substring(0, str.indexOf(";"))), "NUIT", str.substring(str.indexOf(";") + 1));
        }

        return dataset;
    }

    /**
     *
     *
     * @return Graph cree et parametre
     */
    private JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Nombre d'infirmiers par service (jour et nuit)",
                "Service",
                "Nombre d'infirmiers",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);
        return barChart;
    }

    /**
     *
     * @return Instance du graph
     */
    private JPanel createDemoPanel() {
        ChartPanel chart = new ChartPanel(createChart());
        return chart;
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
