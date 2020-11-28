package com.ece;

import javax.xml.bind.JAXBPermission;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        int nombre;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("--------LA BATAILLE NAVAL--------\n");

            System.out.println("1. Jouer une partie");
            System.out.println("2. Charger une partie");
            System.out.println("3. Aide");
            System.out.println("4. Quitter");

            System.out.println("\nSaissir une action:");

            do {
                nombre = scan.nextInt();
                if ((nombre < 1) || (nombre > 4)) {
                    System.out.println("le nombre n'est pas valide, ressaissir: ");
                }
            } while ((nombre < 1) || (nombre > 4));

            switch (nombre) {
                case 1:
                    jouerPartie();
                    break;

                case 2:
                    chargerPartie();
                    break;
                case 3:
                    ouvrirAide();
                    break;

            }
        } while (nombre != 4);
        scan.close();
        
    }

    public static void jouerPartie() {
        Grille Grille1= new Grille();
        Grille Grille2= new Grille();
        Joueur joueur1 = new Joueur(Grille1,Grille2);
        Joueur joueur2 = new Joueur(Grille2,Grille1);

        joueur1.getJGrille().dessiner();
        joueur1.getOGrille().dessiner();
        joueur2.getJGrille().dessiner();
        joueur2.getOGrille().dessiner();
    }

    public static void chargerPartie() {



    }

    public void lireFichier() {
        FileReader monFichier = null;
        BufferedReader tampon = null;

        try {
            monFichier = new FileReader("Save/partie1.txt");
            tampon = new BufferedReader(monFichier);

            while (true) {
                // ligne de type : <joueur> <type Navire> <orientation> <x> <y> <nb de case détruites> <les cases>
                String ligne = tampon.readLine();
                chargerInfo(ligne);
                // Vérifie la fin de fichier
                if (ligne == null)
                    break;
                System.out.println(ligne);
            } // Fin du while
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                tampon.close();
                monFichier.close();
            } catch(IOException exception1) {
                exception1.printStackTrace();
            }
        }
    }

    private void chargerInfo(String ligne) {

    }

    public static void ouvrirAide() {


        //trouvé ça sur internet a tester
       /*
        File htmlFile = new File(url);
       Desktop.getDesktop().browse(htmlFile.toURI());      */
    }

}
