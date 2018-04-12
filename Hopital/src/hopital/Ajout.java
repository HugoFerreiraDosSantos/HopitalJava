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

/**
 *
 * @author Aude
 */
public class Ajout extends JPanel implements ActionListener{
    
    private final HopitalGraphique hopGraph;
    private JTable table;
    private JButton ajout;
    private DefaultTableModel tm;
    private String [] title;
    private Object data [] [];
    private String tableName;
    private int selectData;
    public Ajout(HopitalGraphique hop,String tabName)
    {
        selectData =0;
        this.tableName = tabName;
        this.hopGraph = hop;
        ajout = new JButton("Ajouter");
        this.build();
    }
    private void build(){
        
     title = null;
        try {
                         title = MenuConnexion.getConnexion().remplirChampsColumn("SELECT * FROM " +tableName);

             } catch (SQLException ex) {
                    }
        
   data = new Object[1][title.length];
            for (int i = 0; i <title.length; i++) {
                data[0][i]="";
            }
            tm = new DefaultTableModel(data,title);
            table = new JTable(tm);
            
            
            this.add(new JScrollPane(table));
            this.add(ajout);
            ajout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==ajout)
        {
            table.removeRowSelectionInterval(0, 0);
            boolean valide = true;
            for (int i = 0 ; i < title.length;i++)
            {
                if ("".equals((String)tm.getValueAt(0, i)) )
                {
                    valide = false;
                    break;
                }
                       
            }
            if (valide)
            {
                String req = "INSERT INTO "+tableName+" VALUES (";
                try {
                            Integer.parseInt((String)tm.getValueAt(0, 0));
                            req+=(String)tm.getValueAt(0, 0);
                            
                     } catch (NumberFormatException ex) {
                            req+="'"+(String)tm.getValueAt(0, 0) +"'" ;
                        }
                
                tm.setValueAt("",0, 0);
                for (int i = 1; i<title.length; i++)
                {
                    req +=" , ";
                
                    try {
                            Integer.parseInt((String)tm.getValueAt(0, i));
                            req+=(String)tm.getValueAt(0, i);
                            
                     } catch (NumberFormatException ex) {
                            req+="'"+(String)tm.getValueAt(0, i) +"'" ;
                        }
                    tm.setValueAt("",0, i);
                }
                req+=")";
                try {
                    getConnexion().executeUpdate(req);
                } catch (SQLException ex) {
                    Logger.getLogger(Ajout.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(this, "Ajout réussi");
                
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Saisie incorrect");
            }
        }
    
    }
    
}
