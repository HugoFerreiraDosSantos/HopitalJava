/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Aude
 */
public class HopitalGraphique extends JFrame {

    private MenuConnexion menuCo;
    private MenuPrincipal menuPr;
    private MenuReporting menuRe;
    private MenuInterrogation menuInterrogation;
    private static Dimension dimFen;

    public HopitalGraphique() {

        this.build();
    }

    private void build() {
        Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        //dimFen = new Dimension((int) dim.getWidth() * 2 / 3, (int) dim.getHeight() * 2 / 3);
        dimFen = new Dimension(800, 600);
        this.setSize((int) dimFen.getWidth(), (int) dimFen.getHeight());
        menuCo = new MenuConnexion(this);

        this.setContentPane(menuCo);
        this.setResizable(false);
        this.setTitle("Hopital");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.revalidate();
    }

    public void changeFenetre(int fen) {
        switch (fen) {
            case 0:
                menuCo = new MenuConnexion(this);
                this.setContentPane(menuCo);
                break;
            case 1:
                menuPr = new MenuPrincipal(this);
                this.setContentPane(menuPr);
                break;
            case 2:
                menuRe = new MenuReporting(this);
                this.setContentPane(menuRe);
                break;
            case 3:
                menuInterrogation = new MenuInterrogation(this);
                this.setContentPane(menuInterrogation);
                break;

            default:;
        }
        this.revalidate();
    }

    public static int autoSizeX(double x) {

        return (int) (x * (dimFen.getWidth()));

    }

    public static int autoSizeY(double y) {

        return (int) (y * (dimFen.getHeight()));

    }

}
