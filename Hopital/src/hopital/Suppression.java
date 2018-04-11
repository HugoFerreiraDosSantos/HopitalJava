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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Aude
 */
public class Suppression extends JPanel implements ActionListener{
    
    private final HopitalGraphique hopGraph;
    private JTable table;
    private JButton suppression;
    private DefaultTableModel tm;
    private ArrayList <String> resultats;
    private String [] title;
    private Object data [] [];
    private String tableName;
    public Suppression(HopitalGraphique hop,String tabName)
    {
        this.tableName = tabName;
        this.hopGraph = hop;
        suppression = new JButton("Suppression");
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
            this.add(suppression);
            suppression.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        table.setModel(tm);
        if (e.getSource()==suppression)
        {
            int [] selectedRows = table.getSelectedRows();
            if (selectedRows.length !=0)
            {
                int cptSup = 0;
            for (int select : selectedRows)
            {
                String req ="DELETE FROM "+tableName+" WHERE "+title[0] + " = ";
                 try {
                            Integer.parseInt((String) data[select][0]);
                            req+=data[select][0];
                            
                     } catch (NumberFormatException ex) {
                            req+="'"+data[select][0] +"'" ;
                        }
             
                for (int i = 1; i<data[select].length; i++)
                {
                    req += " AND "+title[i] + " = ";
                    try {
                            Integer.parseInt((String) data[select][i]);
                            req+=data[select][i];
                            
                     } catch (NumberFormatException ex) {
                            req+="'"+data[select][i] +"'" ;
                        }
                }
                System.out.println(req);
                    try {
                        getConnexion().executeUpdate(req);
                    } catch (SQLException ex) {
                        Logger.getLogger(Suppression.class.getName()).log(Level.SEVERE, null, ex);
                    }
                tm.removeRow(select-cptSup++);}
            }
        }
            
            
        }
    
    
    
}
