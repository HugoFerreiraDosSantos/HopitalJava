/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 * Classe qui represente la fenetre et qui gere la transition entre les
 * differents affichages
 *
 * @author Aude et Aude
 */
public class HopitalGraphique extends JFrame implements KeyListener {

    /**
     * Attributs privés : Differents affichages
     */
    private MenuConnexion menuCo;
    private MenuPrincipal menuPr;
    private MenuReporting menuRe;
    private MenuInterrogation menuInterrogation;
    private MenuMisAJour menuMisAJour;
    private MenuSuppression menuSuppression;
    private static Dimension dimFen;
    private MenuAjout menuAjout;
    private MenuEdition menuEdition;
    private Sound snd;

    public HopitalGraphique() {

        this.build();
    }

    /**
     * Methode qui instancie la fenetre
     */
    private void build() {
        Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        dimFen = new Dimension((int) dim.getWidth() * 2 / 3, (int) dim.getHeight() * 2 / 3);

        this.setSize((int) dimFen.getWidth(), (int) dimFen.getHeight());
        menuCo = new MenuConnexion(this);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                dimFen = e.getComponent().getSize();
            }
        });
        this.snd = new Sound("Musique.wav");
        this.setContentPane(menuCo);
        this.setResizable(true);
        this.setTitle("Hopital");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.revalidate();
        setFocusable(true);
        this.addKeyListener(this);
    }

    /**
     * Methode qui affiche la fenetre choisie ( permet la transition entre les
     * differents JPanel )
     *
     * @param fen affichage à mettre en place
     */
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
            case 4:
                menuMisAJour = new MenuMisAJour(this);
                this.setContentPane(menuMisAJour);
                break;
            case 5:
                menuAjout = new MenuAjout(this);
                this.setContentPane(menuAjout);
                break;
            case 6:
                menuEdition = new MenuEdition(this);
                this.setContentPane(menuEdition);
                break;
            case 7:
                menuSuppression = new MenuSuppression(this);
                this.setContentPane(menuSuppression);
                break;
            default:;

        }
        this.revalidate();
        this.setFocusable(true);
    }

    /**
     * Methode qui permet d'ajuster la position X q'un object en fonction de la
     * fenetre
     *
     * @param x position X en pourcentage
     * @return la position X du component en fonction de la fentre
     */
    public static int autoSizeX(double x) {

        return (int) (x * (dimFen.getWidth()));

    }

    /**
     * Methode qui permet d'ajuster la position Y q'un object en fonction de la
     * fenetre
     *
     * @param y position Y en pourcentage
     * @return la position Y du component en fonction de la fentre
     */
    public static int autoSizeY(double y) {

        return (int) (y * (dimFen.getHeight()));

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
            snd.startStopSound();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
