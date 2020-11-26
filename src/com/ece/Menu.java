package com.ece;

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
        Grille grille = new Grille();
        grille.dessiner();

    }

    public static void chargerPartie() {

    }

    public static void ouvrirAide() {


        //trouvé ça sur internet a tester
       /*
        File htmlFile = new File(url);
       Desktop.getDesktop().browse(htmlFile.toURI());      */
    }

}
