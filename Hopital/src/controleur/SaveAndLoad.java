/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import static controleur.Connexion.local;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static modele.MenuConnexion.getConnexion;

/**
 *
 * @author PPC
 */
public class SaveAndLoad {

    public static ArrayList<String> requetes = new ArrayList<>();

    public static void load() {

        Scanner sc;

        try {
            if (local) {
                sc = new Scanner(new File("local.txt"));
            } else {
                sc = new Scanner(new File("distant.txt"));
            }
            String line;
            boolean exit = false;
            while (!exit) {
                try {
                    line = sc.nextLine();
                    requetes.add(line);
                } catch (NoSuchElementException e) {
                    exit = true;
                }
            }

            sc.close();
            send();

            FileWriter writer;
            if (local) {
                writer = new FileWriter("local.txt");
            } else {
                writer = new FileWriter("distant.txt");
            }
            writer.close();

        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            Logger.getLogger(SaveAndLoad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Sauvegarde du fichier
     *
     * @param str requete à sauvegarder
     */
    public static void save(String str) {

        try {
            FileWriter writer;
            if (local) {
                writer = new FileWriter("distant.txt", true);
            } else {
                writer = new FileWriter("local.txt", true);
            }

            writer.write(str + '\n');

            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveAndLoad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void send() {
        requetes.forEach((String requete) -> {
            try {
                getConnexion().executeUpdate(requete);
            } catch (SQLException ex) {
                Logger.getLogger(SaveAndLoad.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
