package com.ece;

import javax.xml.bind.JAXBPermission;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
            ArrayList<Navire> listNavJ = new ArrayList<>();
            ArrayList<Navire> listNavO = new ArrayList<>();

            while (true) {
                // ligne de type : <joueur> <type Navire> <orientation> <x> <y> <nb de case détruites> <les cases>
                String ligne = tampon.readLine();
                // Vérifie la fin de fichier
                if (ligne == null)
                    break;

                // on créer deux listes de navire pour chacun des joueurs
                Navire nav;
                nav = chargerInfo(ligne,"1");
                if (nav !=null)
                listNavJ.add(nav);
                nav = chargerInfo(ligne,"2");
                if (nav !=null)
                listNavO.add(nav);

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

    private Navire chargerInfo(String ligne,String j) {
        int pos = ligne.indexOf(' ');
        if (pos <= 0){
            return null;
        }
        String joueur = ligne.substring(0,pos);
        if (!joueur.equals(j)){
            return null;
        }

        int pos2 = ligne.indexOf(' ',pos);
        String typeNav = ligne.substring(pos,pos2);
        ArrayList<Navire> listTemporaire = new ArrayList<>();
        switch (typeNav){
            case "1":
                Cuirasse cuirasse = new Cuirasse();
                listTemporaire.add(cuirasse);
                break;
            case "2":
                Croiseur croiseur = new Croiseur();
                listTemporaire.add(croiseur);
                break;
            case "3":
                Destroyer destroyer = new Destroyer();
                listTemporaire.add(destroyer);
                break;
            case "4":
                SousMarin sousMarin = new SousMarin();
                listTemporaire.add(sousMarin);
                break;
            default:
                return null;
        }
        pos = ligne.indexOf(' ', pos2);
        String orientation = ligne.substring(pos2,pos);
        int yMax = 14;
        int xMax = 14;
        if (orientation == "1"){
            listTemporaire.get(0).setOrientation("verticale");
            yMax = 15 - listTemporaire.get(0).getTaille();
        }
        else if (orientation == "2"){
            listTemporaire.get(0).setOrientation("horizontal");
            xMax = 15 - listTemporaire.get(0).getTaille();
        }

        pos2 = ligne.indexOf(' ', pos);
        String x = ligne.substring(pos,pos2);
        int coordX = Integer.parseInt(x);
        Point coordNav= new Point();
        if (coordX >= 0 || coordX <= xMax ){
            coordNav.x = coordX;
        }else{
            return null;
        }

        pos = ligne.indexOf(' ', pos2);
        String y = ligne.substring(pos2,pos);
        int coordY = Integer.parseInt(y);
        if (coordY >= 0 || coordY <= yMax ){
            coordNav.y = coordY;
        }else{
            return null;
        }

        pos2 = ligne.indexOf(' ', pos);
        String nbCaseCasse = ligne.substring(pos,pos2);
        int nbCaseCasseInt = Integer.parseInt(nbCaseCasse);
        int[] temp = new int[listTemporaire.get(0).getTaille()];
        for (int i = 0; i < nbCaseCasseInt; i++){
            pos = ligne.indexOf(' ', pos2);
            String caseCasse = ligne.substring(pos2,pos);
            int caseCasseInt = Integer.parseInt(caseCasse);
            temp[caseCasseInt] = 1;
            pos2 = pos;
        }

        return listTemporaire.get(0);
    }

    public static void ouvrirAide() {


        //trouvé ça sur internet a tester
       /*
        File htmlFile = new File(url);
       Desktop.getDesktop().browse(htmlFile.toURI());      */
    }

}
