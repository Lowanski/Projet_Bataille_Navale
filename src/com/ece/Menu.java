package com.ece;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu {

    private static ArrayList<Cuirasse> listCuirasseJ = new ArrayList<Cuirasse>();
    private static ArrayList<Croiseur> listCroiseurJ = new ArrayList<Croiseur>();
    private static ArrayList<Destroyer> listDestroyerJ = new ArrayList<Destroyer>();
    private static ArrayList<SousMarin> listSousmarinJ = new ArrayList<SousMarin>();
    private static ArrayList<Cuirasse> listCuirasseO = new ArrayList<Cuirasse>();
    private static ArrayList<Croiseur> listCroiseurO = new ArrayList<Croiseur>();
    private static ArrayList<Destroyer> listDestroyerO = new ArrayList<Destroyer>();
    private static ArrayList<SousMarin> listSousmarinO = new ArrayList<SousMarin>();

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
        int nombre;
        do{ 
            joueur1.getJGrille().dessiner();
            joueur1.getOGrille().dessiner();

            System.out.println("1. Tirer");
            System.out.println("2. Déplacer");
            System.out.println("3. Sauvegarder et Quitter");

            System.out.println("\nSaissir une action:");
            Scanner scan = new Scanner(System.in);
            do {
                nombre = scan.nextInt();
                if ((nombre < 1) || (nombre > 3)) {
                    System.out.println("le nombre n'est pas valide, ressaissir: ");
                }
            } while ((nombre < 1) || (nombre > 3));

            switch(nombre)
            {
                case 1:
                    ///choisir le bateau et tirer
                    Navire tireur=joueur1.getJGrille().getNavire();
                    Point impact =tireur.saisirTir();

                    joueur1.getOGrille().rechercheNavire(impact,tireur.getPuissanceTire());

                    break;

                case 2: 
                    /// choisir le bateau et bouger si il peut
                    boolean ok;
                    do{
                        ok=joueur2.getJGrille().getNavire().canMove(joueur2.getJGrille());
                    }while(!ok);
                    
                    break;
                
                case 3:
                    //enregistrer
                    break;
            }

        }while(nombre!=3);
    }

    public static void chargerPartie() {
        lireFichier();
        Grille Grille1= new Grille(listCuirasseJ,listCroiseurJ,listDestroyerJ,listSousmarinJ);
        Grille Grille2= new Grille(listCuirasseO,listCroiseurO,listDestroyerO,listSousmarinO);

        Joueur joueur1 = new Joueur(Grille1,Grille2);
        Joueur joueur2 = new Joueur(Grille2,Grille1);

        joueur1.getJGrille().dessiner();
        joueur1.getOGrille().dessiner();
        joueur2.getJGrille().dessiner();
        joueur2.getOGrille().dessiner();

    }

    public static void lireFichier() {
        FileReader monFichier = null;
        BufferedReader tampon = null;

        try {
            monFichier = new FileReader("Save/partie1.txt");
            tampon = new BufferedReader(monFichier);
            while (true) {
                // ligne de type : <joueur> <type Navire> <orientation> <x> <y> <nb de case détruites> <les cases>
                String ligne = tampon.readLine();
                // Vérifie la fin de fichier
                if (ligne == null)
                    break;

                // on créer deux listes de navire pour chacun des joueurs
                Navire nav;
                nav = chargerInfo(ligne,"1");
                if(nav != null){
                    if (nav.getId() == 1){
                        Cuirasse cuirasseChargeJ = new Cuirasse();
                        cuirasseChargeJ.setCoord(nav.getCoord());
                        cuirasseChargeJ.setOrientation(nav.getOrientation());
                        cuirasseChargeJ.setToucherTab(nav.getToucherTab());
                        listCuirasseJ.add(cuirasseChargeJ);
                    }
                    if (nav.getId() == 2){
                        Croiseur croiseurChargeJ = new Croiseur();
                        croiseurChargeJ.setCoord(nav.getCoord());
                        croiseurChargeJ.setOrientation(nav.getOrientation());
                        croiseurChargeJ.setToucherTab(nav.getToucherTab());
                        listCroiseurJ.add(croiseurChargeJ);
                    }
                    if (nav.getId() == 3){
                        Destroyer destroyerChargeJ = new Destroyer();
                        destroyerChargeJ.setCoord(nav.getCoord());
                        destroyerChargeJ.setOrientation(nav.getOrientation());
                        destroyerChargeJ.setToucherTab(nav.getToucherTab());
                        listDestroyerJ.add(destroyerChargeJ);
                    }
                    if (nav.getId() == 4){
                        SousMarin sousMarinChargeJ = new SousMarin();
                        sousMarinChargeJ.setCoord(nav.getCoord());
                        sousMarinChargeJ.setOrientation(nav.getOrientation());
                        sousMarinChargeJ.setToucherTab(nav.getToucherTab());
                        listSousmarinJ.add(sousMarinChargeJ);
                    }
                }

                nav = chargerInfo(ligne,"2");

                if (nav != null){
                    if (nav.getId() == 1){
                        Cuirasse cuirasseChargeO = new Cuirasse();
                        cuirasseChargeO.setCoord(nav.getCoord());
                        cuirasseChargeO.setOrientation(nav.getOrientation());
                        cuirasseChargeO.setToucherTab(nav.getToucherTab());
                        listCuirasseO.add(cuirasseChargeO);
                    }
                    if (nav.getId() == 2){
                        Croiseur croiseurChargeO = new Croiseur();
                        croiseurChargeO.setCoord(nav.getCoord());
                        croiseurChargeO.setOrientation(nav.getOrientation());
                        croiseurChargeO.setToucherTab(nav.getToucherTab());
                        listCroiseurO.add(croiseurChargeO);
                    }
                    if (nav.getId() == 3){
                        Destroyer destroyerChargeO = new Destroyer();
                        destroyerChargeO.setCoord(nav.getCoord());
                        destroyerChargeO.setOrientation(nav.getOrientation());
                        destroyerChargeO.setToucherTab(nav.getToucherTab());
                        listDestroyerO.add(destroyerChargeO);
                    }
                    if (nav.getId() == 4){
                        SousMarin sousMarinChargeO = new SousMarin();
                        sousMarinChargeO.setCoord(nav.getCoord());
                        sousMarinChargeO.setOrientation(nav.getOrientation());
                        sousMarinChargeO.setToucherTab(nav.getToucherTab());
                        listSousmarinO.add(sousMarinChargeO);
                    }
                }

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

    private static Navire chargerInfo(String ligne, String j) {
        int pos = ligne.indexOf(' ');
        if (pos <= 0){
            return null;
        }
        String joueur = ligne.substring(0,pos);
        if (!joueur.equals(j)){
            return null;
        }

        int pos2 = ligne.indexOf(' ',pos+1);
        String typeNav = ligne.substring(pos+1,pos2);
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
        pos = ligne.indexOf(' ', pos2+1);
        String orientation = ligne.substring(pos2+1,pos);
        int yMax = 14;
        int xMax = 14;
        if (orientation.equals("1")){
            listTemporaire.get(0).setOrientation("verticale");
            yMax = 15 - listTemporaire.get(0).getTaille();
        }
        else if (orientation.equals("2")){
            listTemporaire.get(0).setOrientation("horizontale");
            xMax = 15 - listTemporaire.get(0).getTaille();
        }

        pos2 = ligne.indexOf(' ', pos+1);
        String x = ligne.substring(pos+1,pos2);
        int coordX = Integer.parseInt(x);
        Point coordNav= new Point();
        if (coordX >= 0 || coordX <= xMax ){
            coordNav.x = coordX;
        }else{
            return null;
        }

        pos = ligne.indexOf(' ', pos2+1);
        String y = ligne.substring(pos2+1,pos);
        int coordY = Integer.parseInt(y);
        if (coordY >= 0 || coordY <= yMax ){
            coordNav.y = coordY;
        }else{
            return null;
        }

        listTemporaire.get(0).setCoord(coordNav);

        String nbCaseCasse;
        Boolean fin = false;
        if(ligne.length()-pos <= 2){
            nbCaseCasse = ligne.substring(pos+1,ligne.length());
            fin = true;
        }
        else{
            pos2 = ligne.indexOf(' ', pos+1);
            nbCaseCasse = ligne.substring(pos+1,pos2);
        }

        int nbCaseCasseInt = Integer.parseInt(nbCaseCasse);
        int[] temp = new int[listTemporaire.get(0).getTaille()];

        if (fin == false){
            for (int i = 0; i < nbCaseCasseInt - 1; i++){
                pos = ligne.indexOf(' ', pos2+1);
                String caseCasse = ligne.substring(pos2+1,pos);
                int caseCasseInt = Integer.parseInt(caseCasse);
                temp[caseCasseInt] = 1;
                pos2 = pos;
            }
            String caseCasse = ligne.substring(pos2+1,ligne.length());
            int caseCasseInt = Integer.parseInt(caseCasse);
            temp[caseCasseInt] = 1;
        }

        listTemporaire.get(0).setToucherTab(temp);

        return listTemporaire.get(0);
    }

    public static void ouvrirAide() {


        //trouvé ça sur internet a tester
       /*
        File htmlFile = new File(url);
       Desktop.getDesktop().browse(htmlFile.toURI());      */
    }

}
