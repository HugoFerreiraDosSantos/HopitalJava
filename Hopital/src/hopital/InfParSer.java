/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.HopitalGraphique.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author PPC
 */
public class InfParSer extends JPanel {

    private ArrayList<String> resultats;

    public InfParSer() {
        resultats = new ArrayList<>();
        this.setLayout(null);
        JPanel pan = createDemoPanel();
        pan.setBounds(autoSizeX(0.0), autoSizeY(0.0), autoSizeX(0.86875), autoSizeY(0.725));
        this.add(pan);
    }

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
            dataset.addValue(Integer.parseInt(str.substring(0, str.indexOf(","))), "JOUR", str.substring(str.indexOf(",") + 1));
            str = resultats.get(i + demiSize);
            dataset.addValue(Integer.parseInt(str.substring(0, str.indexOf(","))), "NUIT", str.substring(str.indexOf(",") + 1));
        }

        return dataset;
    }

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

    private JPanel createDemoPanel() {
        ChartPanel chart = new ChartPanel(createChart());
        return chart;
    }

}
