package com.ece;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.ece.Navire.SaisieErroneeException;

/***
 * Le menu est le main du code. il centralise toute les class et permet de jouer au jeu.
 */
public class Menu {

    private static ArrayList<Cuirasse> listCuirasseJ = new ArrayList<Cuirasse>();
    private static ArrayList<Croiseur> listCroiseurJ = new ArrayList<Croiseur>();
    private static ArrayList<Destroyer> listDestroyerJ = new ArrayList<Destroyer>();
    private static ArrayList<SousMarin> listSousmarinJ = new ArrayList<SousMarin>();
    private static ArrayList<Cuirasse> listCuirasseO = new ArrayList<Cuirasse>();
    private static ArrayList<Croiseur> listCroiseurO = new ArrayList<Croiseur>();
    private static ArrayList<Destroyer> listDestroyerO = new ArrayList<Destroyer>();
    private static ArrayList<SousMarin> listSousmarinO = new ArrayList<SousMarin>();
    private static Chrono chrono = new Chrono();

    // ---------------------BOUCLE DE JEU-----------------------

    /***
     * Menu principale, accueil du jeu, possibilités de commencer une nouvelle partie,
     * charger une ancienne ou ouvrir l'aide.
     * @param args
     */
    public static void main(String[] args) {

        int nombre;
        String choix;

        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("--------LA BATAILLE NAVAL--------\n");

            System.out.println("1. Jouer une partie");
            System.out.println("2. Charger une partie");
            System.out.println("3. Aide");
            System.out.println("4. Quitter");

            System.out.println("\nSaissir une action:");
            boolean correct=false;
            do {
                choix= scan.nextLine();
                try {
                    controleMenu(choix);
                    correct=true;
                } catch (SaisieErroneeException e) {
                    System.out.println(e);
                }
            } while (!correct);

            nombre= Integer.parseInt(choix);

            switch (nombre) {
                case 1:
                    jouerPartie();
                    break;
                case 2:
                    chargerPartieSerial();
                    break;
                case 3:
                    ouvrirAide();
                    break;
            }
        } while (nombre != 4);
        scan.close();
    }

    public static void jouerPartie() {
        Grille Grille1 = new Grille();
        Grille Grille2 = new Grille();
        Joueur joueur1 = new Joueur(Grille1, Grille2);
        Joueur joueur2 = new Joueur(Grille2, Grille1);
        chrono.start();

        play(joueur1, joueur2);
    }

    /***
     * Menu d'action avec possibilitée de se deplacer tirer et sauvegarder. C'est aussi la boucle de jeu,
     * avec un comptage des tours et le tour de jeu de l'IA
     * @param joueur1
     * @param joueur2
     */
    public static void play(Joueur joueur1, Joueur joueur2) {
        int etatPartie; // Etat partie 1 = le joueur a joué | 0 = le jouer quitte la partie | -1 = un joueur a gagné la partie
        int nombre;
        do {
            String choix;
            boolean correct=false;
            joueur1.setActionDebutTour(); // initialise le nb d'action
            joueur2.setActionDebutTour();


            if(joueur1.checkEtatJoueur()){  //si le joueur 1 n'est pas mort

                do {
                    joueur1.getJGrille().dessiner();
                    joueur1.getOGrille().dessiner();
                    joueur1.getOGrille().dessinerenemi();
                    Score(joueur1,joueur2);

                    System.out.println("1. Tirer");
                    System.out.println("2. Déplacer");
                    System.out.println("3. Sauvegarder et Quitter");

                    System.out.println("\nSaissir une action:");
                    Scanner scan = new Scanner(System.in);
                    do {
                        choix = scan.nextLine();
                        try {
                            controleAction(choix);
                            correct=true;
                        } catch (SaisieErroneeException e){
                            System.out.println(e);
                        }
                    } while (!correct);

                    nombre= Integer.parseInt(choix);

                    etatPartie = doAction(joueur1, joueur2, nombre);

                } while (nombre != 3 && joueur1.getAction() != 0);


                if(joueur2.checkEtatJoueur()){  // si l'IA peut jouer

                    System.out.println("##### L'IA JOUE #####");
                    if(etatPartie == 1)
                        doActionIA(joueur2,joueur1);
                    /*
                    Faire la partie où l'ordi fait aléatoirement quelque chose.
                    Il faut adapter les fonction de tire et de mouvement pour l'ordi (ne par demandé de rentrer quelque chose)
                    */

                }
                else{
                    chrono.stop();
                    System.out.println(ConsoleColors.BLUE+"\nVICTOIRE DU JOUEUR 1 EN : "+chrono.getDureeSec()+"s"+ConsoleColors.RESET);
                    etatPartie=-1;
                }
            }
            else{
                chrono.stop();
                System.out.println(ConsoleColors.BLUE+"\nVICTOIRE DE l'IA EN : "+chrono.getDureeSec()+"s"+ConsoleColors.RESET);
                etatPartie=-1;
            }

        } while (etatPartie != 0 && etatPartie != -1);

        if (etatPartie == 0) {
            System.out.println("Vous avez quitté la partie, la partie a été sauvegardée");
        }
    }

    /***
     * L'IA choisie aléatoiremnt de tirer ou de se déplacer.
     * @param joueur2
     * @param joueur1
     */
    private static void doActionIA(Joueur joueur2, Joueur joueur1) {
        Random rand = new Random();
        int actionAlea = rand.nextInt(2);
        if (actionAlea == 1){
            Navire tireur = joueur2.getJGrille().getNavireAlea();
            if (joueur2.useAction()) {
                joueur2.getOGrille().rechercheNavireAlea(tireur,joueur1);
            }
            joueur2.useAction();
        }else{
            boolean ok;
            if (joueur2.useAction()) {
                do {
                    ok = joueur2.getJGrille().getNavireAlea().canMove(joueur2.getJGrille(),"IA");
                } while (!ok);
            }
            joueur2.useAction();
        }
    }

    /***
     * Réalise l'action selectionner dans la fonction play
     * @param joueur1
     * @param joueur2
     * @param nombre
     * @return
     */
    public static int doAction(Joueur joueur1, Joueur joueur2, int nombre) {
        int etatPartie;
        switch (nombre) {
            case 1:
                ///choisir le bateau et tirer
                Navire tireur = joueur1.getJGrille().getNavire();
                if (joueur1.useAction()) {
                    joueur1.getOGrille().rechercheNavire(tireur);
                }
                joueur1.useAction();
                etatPartie = 1;
                break;

            case 2:
                /// choisir le bateau et bouger si il peut
                boolean ok;
                if (joueur1.useAction()) {
                    do {
                        ok = joueur1.getJGrille().getNavire().canMove(joueur1.getJGrille());
                    } while (!ok);
                }
                joueur1.useAction();
                etatPartie = 1;
                break;

            case 3:
                sauvegarderPartie(joueur1, joueur2);
                etatPartie = -1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + nombre);
        }
        return etatPartie;
    }

    public static void Score(Joueur j1, Joueur j2){

        float Scoreplayer1= j1.ScoreJoueur();
        float Scoreplayer2= j2.ScoreJoueur();

        System.out.println("Score de partie: \njoueur 1:"+ (int)Scoreplayer2 +" IA: "+ (int)Scoreplayer1);
    }

    // BLINDAGE DE SAISIE
    public static void controleMenu(String choix) throws SaisieErroneeException {
        if ((!choix.equals("1"))&&(!choix.equals("2"))&&(!choix.equals("3")&&(!choix.equals("4")))) {
            throw new SaisieErroneeException(
                    "Vous n'avez pas entrer une action valide, ressaisir: ");
        }
    }

    public static void controleAction(String choix) throws SaisieErroneeException {
        if ((!choix.equals("1"))&&(!choix.equals("2"))&&(!choix.equals("3"))) {
            throw new SaisieErroneeException(
                    "Vous n'avez pas entrer une action valide, ressaisir: ");
        }
    }

    // SAUVEGARDE ET CHARGEMENT

    /***
     * Sauvegarde de la partie de manière serialiser en sérialisant le joueur 1, le joueur 2 et le chrono
     */
    private static void chargerPartieSerial() {
        Joueur joueur1 = null;
        Joueur joueur2 = null;
        ObjectInputStream ois;
        FileInputStream fis;
        chrono.resume();

        try {
            File fileTst = new File("Save/partieSave.serial");
            if (fileTst.exists()){
                // ouverture d'un flux d'entrée depuis le fichier "Save/partieSave.serial"
                fis = new FileInputStream("Save/partieSave.serial"); // création d'un "flux objet" avec le flux fichier
                ois = new ObjectInputStream(fis);
            }else return;


            try {
                // désérialisation : lecture de l'objet depuis le flux d'entrée
                joueur1 = (Joueur) ois.readObject();
                joueur2 = (Joueur) ois.readObject();
                chrono = (Chrono) ois.readObject();
            } finally {
                // on ferme les flux
                try {
                    ois.close();
                } finally {
                    fis.close();
                }
            }
        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }

        if (joueur1 != null) {
            System.out.println(joueur1 + " a ete deserialise");
        }
        if (joueur2 != null) {
            System.out.println(joueur2 + " a ete deserialise");
        }

        play(joueur1, joueur2);

    }

    /***
     * Chargement (deserialisation) du fichier "partieSave.serial" pour instancier joueur 1, joueur 2 et le chrono
     * @param j1
     * @param j2
     */
    public static void sauvegarderPartie(Joueur j1, Joueur j2) {
        try {
            chrono.pause();
            FileOutputStream fos = new FileOutputStream("Save/partieSave.serial");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                // sérialisation : écriture de l'objet dans le flux de sortie

                oos.writeObject(j1);
                oos.writeObject(j2);
                oos.writeObject(chrono);
                // on vide le tampon
                oos.flush();
                System.out.println(j1 + " a ete serialise");
                System.out.println(j2 + " a ete serialise");
            } finally {
                //fermeture des flux
                try {
                    oos.close();
                } finally {
                    fos.close();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //Chargement d'une partie à partir d'un fichier txt
    public static void chargerPartie() {
        lireFichier();
        Grille Grille1 = new Grille(listCuirasseJ, listCroiseurJ, listDestroyerJ, listSousmarinJ);
        Grille Grille2 = new Grille(listCuirasseO, listCroiseurO, listDestroyerO, listSousmarinO);

        Joueur joueur1 = new Joueur(Grille1, Grille2);
        Joueur joueur2 = new Joueur(Grille2, Grille1);

        joueur1.getJGrille().dessiner();
        joueur1.getOGrille().dessiner();
        joueur2.getJGrille().dessiner();
        joueur2.getOGrille().dessiner();

    }
    //Lecture du fichier
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
                nav = chargerInfo(ligne, "1");
                if (nav != null) {
                    if (nav.getId() == 1) {
                        Cuirasse cuirasseChargeJ = new Cuirasse();
                        cuirasseChargeJ.setCoord(nav.getCoord());
                        cuirasseChargeJ.setOrientation(nav.getOrientation());
                        cuirasseChargeJ.setToucherTab(nav.getToucherTab());
                        listCuirasseJ.add(cuirasseChargeJ);
                    }
                    if (nav.getId() == 2) {
                        Croiseur croiseurChargeJ = new Croiseur();
                        croiseurChargeJ.setCoord(nav.getCoord());
                        croiseurChargeJ.setOrientation(nav.getOrientation());
                        croiseurChargeJ.setToucherTab(nav.getToucherTab());
                        listCroiseurJ.add(croiseurChargeJ);
                    }
                    if (nav.getId() == 3) {
                        Destroyer destroyerChargeJ = new Destroyer();
                        destroyerChargeJ.setCoord(nav.getCoord());
                        destroyerChargeJ.setOrientation(nav.getOrientation());
                        destroyerChargeJ.setToucherTab(nav.getToucherTab());
                        listDestroyerJ.add(destroyerChargeJ);
                    }
                    if (nav.getId() == 4) {
                        SousMarin sousMarinChargeJ = new SousMarin();
                        sousMarinChargeJ.setCoord(nav.getCoord());
                        sousMarinChargeJ.setOrientation(nav.getOrientation());
                        sousMarinChargeJ.setToucherTab(nav.getToucherTab());
                        listSousmarinJ.add(sousMarinChargeJ);
                    }
                }

                nav = chargerInfo(ligne, "2");

                if (nav != null) {
                    if (nav.getId() == 1) {
                        Cuirasse cuirasseChargeO = new Cuirasse();
                        cuirasseChargeO.setCoord(nav.getCoord());
                        cuirasseChargeO.setOrientation(nav.getOrientation());
                        cuirasseChargeO.setToucherTab(nav.getToucherTab());
                        listCuirasseO.add(cuirasseChargeO);
                    }
                    if (nav.getId() == 2) {
                        Croiseur croiseurChargeO = new Croiseur();
                        croiseurChargeO.setCoord(nav.getCoord());
                        croiseurChargeO.setOrientation(nav.getOrientation());
                        croiseurChargeO.setToucherTab(nav.getToucherTab());
                        listCroiseurO.add(croiseurChargeO);
                    }
                    if (nav.getId() == 3) {
                        Destroyer destroyerChargeO = new Destroyer();
                        destroyerChargeO.setCoord(nav.getCoord());
                        destroyerChargeO.setOrientation(nav.getOrientation());
                        destroyerChargeO.setToucherTab(nav.getToucherTab());
                        listDestroyerO.add(destroyerChargeO);
                    }
                    if (nav.getId() == 4) {
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
            } catch (IOException exception1) {
                exception1.printStackTrace();
            }
        }
    }
    //Parsing des lignes pour instancier les attributs des joueurs
    private static Navire chargerInfo(String ligne, String j){
        int pos = ligne.indexOf(' ');
        if (pos <= 0) {
            return null;
        }
        String joueur = ligne.substring(0, pos);
        if (!joueur.equals(j)) {
            return null;
        }

        int pos2 = ligne.indexOf(' ', pos + 1);
        String typeNav = ligne.substring(pos + 1, pos2);
        ArrayList<Navire> listTemporaire = new ArrayList<>();

        switch (typeNav) {
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
        pos = ligne.indexOf(' ', pos2 + 1);
        String orientation = ligne.substring(pos2 + 1, pos);
        int yMax = 14;
        int xMax = 14;
        if (orientation.equals("1")) {
            listTemporaire.get(0).setOrientation("verticale");
            yMax = 15 - listTemporaire.get(0).getTaille();
        } else if (orientation.equals("2")) {
            listTemporaire.get(0).setOrientation("horizontale");
            xMax = 15 - listTemporaire.get(0).getTaille();
        }

        pos2 = ligne.indexOf(' ', pos + 1);
        String x = ligne.substring(pos + 1, pos2);
        int coordX = Integer.parseInt(x);
        Point coordNav = new Point();
        if (coordX >= 0 || coordX <= xMax) {
            coordNav.x = coordX;
        } else {
            return null;
        }

        pos = ligne.indexOf(' ', pos2 + 1);
        String y = ligne.substring(pos2 + 1, pos);
        int coordY = Integer.parseInt(y);
        if (coordY >= 0 || coordY <= yMax) {
            coordNav.y = coordY;
        } else {
            return null;
        }

        listTemporaire.get(0).setCoord(coordNav);

        String nbCaseCasse;
        boolean fin = false;
        if (ligne.length() - pos <= 2) {
            nbCaseCasse = ligne.substring(pos + 1, ligne.length());
            fin = true;
        } else {
            pos2 = ligne.indexOf(' ', pos + 1);
            nbCaseCasse = ligne.substring(pos + 1, pos2);
        }

        int nbCaseCasseInt = Integer.parseInt(nbCaseCasse);
        int[] temp = new int[listTemporaire.get(0).getTaille()];

        if (!fin) {
            for (int i = 0; i < nbCaseCasseInt - 1; i++) {
                pos = ligne.indexOf(' ', pos2 + 1);
                String caseCasse = ligne.substring(pos2 + 1, pos);
                int caseCasseInt = Integer.parseInt(caseCasse);
                temp[caseCasseInt] = 1;
                pos2 = pos;
            }
            String caseCasse = ligne.substring(pos2 + 1, ligne.length());
            int caseCasseInt = Integer.parseInt(caseCasse);
            temp[caseCasseInt] = 1 ;
        }

        listTemporaire.get(0).setToucherTab(temp);

        return listTemporaire.get(0);
    }

    // AIDE
    public static void ouvrirAide(){

    }

}