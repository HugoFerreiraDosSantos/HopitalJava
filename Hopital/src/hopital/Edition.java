/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import static hopital.MenuConnexion.getConnexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aude
 */
public class Edition extends JPanel implements ActionListener{
    
    private final HopitalGraphique hopGraph;
    private JTable table;
    private JButton edition;
    private DefaultTableModel tm;
    private ArrayList <String> resultats;
    private String [] title;
    private Object data [] [];
    private String tableName;
    private int selectData;
    public Edition(HopitalGraphique hop,String tabName)
    {
        selectData =0;
        this.tableName = tabName;
        this.hopGraph = hop;
        edition = new JButton("Modifier");
        this.build();
    }
    private void build(){
        
     resultats = new ArrayList<>();
     title = null;
        try {
                        resultats = MenuConnexion.getConnexion().remplirChampsRequete2("SELECT * FROM " +tableName);
                        title = MenuConnexion.getConnexion().remplirChampsColumn("SELECT * FROM " +tableName);

             } catch (SQLException ex) {
                    }
        
     int cpt = 0;
            for (int i = 0; i < resultats.get(0).length(); i++) {
                if (resultats.get(0).charAt(i) == ';') {
                    cpt++;
                }
            }

           data = new Object[resultats.size()][cpt + 1];
            for (int i = 0; i < resultats.size(); i++) {
                int j = 0;
                while (resultats.get(i).contains(";")) {
                    int pos = resultats.get(i).indexOf(";");
                    data[i][j++] = resultats.get(i).substring(0, pos);
                    resultats.set(i, resultats.get(i).substring(pos + 1));
                }
                data[i][j] = resultats.get(i);
            }
            tm = new DefaultTableModel(data,title);
            
            table = new JTable(tm);
            
            
            this.add(new JScrollPane(table));
            this.add(edition);
            edition.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==edition)
        {
          
            
        }
            
            
        }
    
    
    
}
