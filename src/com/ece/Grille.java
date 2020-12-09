package com.ece;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.ece.Navire.SaisieErroneeException;

public class Grille implements Serializable {

    public class ConsoleColors {
        // Reset
        public static final String RESET = "\033[0m";  // Text Reset

        // Regular Colors
        public static final String BLACK = "\033[0;30m";   // BLACK
        public static final String RED = "\033[0;31m";     // RED
        public static final String GREEN = "\033[0;32m";   // GREEN
        public static final String YELLOW = "\033[0;33m";  // YELLOW
        public static final String BLUE = "\033[0;34m";    // BLUE
        public static final String PURPLE = "\033[0;35m";  // PURPLE
        public static final String CYAN = "\033[0;36m";    // CYAN
        public static final String WHITE = "\033[0;37m";   // WHITE

        // Bold
        public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
        public static final String RED_BOLD = "\033[1;31m";    // RED
        public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
        public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
        public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
        public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
        public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
        public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

        // Underline
        public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
        public static final String RED_UNDERLINED = "\033[4;31m";    // RED
        public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
        public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
        public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
        public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
        public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
        public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

        // Background
        public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
        public static final String RED_BACKGROUND = "\033[41m";    // RED
        public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
        public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
        public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
        public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
        public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
        public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

        // High Intensity
        public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
        public static final String RED_BRIGHT = "\033[0;91m";    // RED
        public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
        public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
        public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
        public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
        public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
        public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

        // Bold High Intensity
        public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
        public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
        public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
        public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
        public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

        // High Intensity backgrounds
        public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
        public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
        public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
        public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
        public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
        public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
        public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
        public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    }

    Navire[][] tableau = new Navire[15][15];
    Cuirasse cuirasse;
    ArrayList<Croiseur> listCroiseur = new ArrayList<Croiseur>();
    ArrayList<Destroyer> listDestroyer = new ArrayList<Destroyer>();
    ArrayList<SousMarin> listSousmarin = new ArrayList<SousMarin>();
    ArrayList<Navire> Navires = new ArrayList<Navire>();
    String[] cons = {" 0 ", " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", "10 ", "11 ", "12 ", "13 ", "14 "};

    Grille() {
        cuirasse = new Cuirasse();
        setPositionRandom(cuirasse);
        Navires.add(cuirasse);

        for (int i = 0; i < 2; i++) {
            Croiseur croiseur = new Croiseur();
            setPositionRandom(croiseur);
            listCroiseur.add(croiseur);
            Navires.add(croiseur);
        }
        for (int i = 0; i < 3; i++) {
            Destroyer destroyer = new Destroyer();
            setPositionRandom(destroyer);
            listDestroyer.add(destroyer);
            Navires.add(destroyer);
        }
        for (int i = 0; i < 4; i++) {
            SousMarin sousMarin = new SousMarin();
            setPositionRandom(sousMarin);
            listSousmarin.add(sousMarin);
            Navires.add(sousMarin);
        }
    }

    Grille(ArrayList<Cuirasse> listCui, ArrayList<Croiseur> listCroi, ArrayList<Destroyer> listDestr, ArrayList<SousMarin> listSous) {
        for (Cuirasse cui : listCui) {
            Point coord = cui.getCoord();
            setPosition(cui, coord.x, coord.y);
        }
        for (Croiseur croi : listCroi) {
            Point coord = croi.getCoord();
            setPosition(croi, coord.x, coord.y);
        }
        for (Destroyer destr : listDestr) {
            Point coord = destr.getCoord();
            setPosition(destr, coord.x, coord.y);
        }
        for (SousMarin sous : listSous) {
            Point coord = sous.getCoord();
            setPosition(sous, coord.x, coord.y);
        }
    }

    public ArrayList<Navire> gettouslesNavires(){
        return Navires;
    }

    public void setListCroiseur(ArrayList<Croiseur> c) {
        listCroiseur = c;
    }

    public void setListDestroyer(ArrayList<Destroyer> d) {
        listDestroyer = d;
    }

    public void setListSousmarin(ArrayList<SousMarin> sm) {
        listSousmarin = sm;
    }

    public Navire[][] getTableau() {
        return tableau;
    }

    public void dessiner() {
        System.out.println(ConsoleColors.BLACK_BOLD + "   | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O |");
        System.out.println("=================================================================" + ConsoleColors.RESET);

        for (int i = 0; i < 15; i++) {
            System.out.print(ConsoleColors.BLACK_BOLD + cons[i] + "|" + ConsoleColors.RESET);
            for (int j = 0; j < 15; j++) {
                if (tableau[j][i] != null) {
                    int temp = casetouche(tableau[j][i], i, j);
                    if (temp == 1) {
                        System.out.print(ConsoleColors.RED_BOLD + " X  " + ConsoleColors.RESET);
                    } else {
                        System.out.print(" " + tableau[j][i].getId() + "  ");
                    }
                } else {
                    System.out.print(ConsoleColors.BLUE_BOLD + "--- " + ConsoleColors.RESET);
                }
            }
            System.out.println(ConsoleColors.BLACK_BOLD + "|");
        }
        System.out.println("=================================================================" + ConsoleColors.RESET);

    }

    public void dessinerenemi() {
        System.out.println(ConsoleColors.BLACK_BOLD + "   | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O |");
        System.out.println("=================================================================");
        for (int i = 0; i < 15; i++) {
            System.out.print(cons[i] + "|" + ConsoleColors.RESET);
            for (int j = 0; j < 15; j++) {
                if (tableau[j][i] != null) {
                    int temp = casetouche(tableau[j][i], i, j);
                    if (temp == 1) {
                        System.out.print(ConsoleColors.RED_BOLD + "000 " + ConsoleColors.RESET);
                    } else if (temp == 2) {
                        System.out.print(" " + tableau[j][i].getId() + "  ");
                    } else {
                        System.out.print(ConsoleColors.BLUE_BOLD + "--- " + ConsoleColors.RESET);
                    }
                } else {
                    System.out.print(ConsoleColors.BLUE_BOLD + "--- " + ConsoleColors.RESET);
                }
            }
            System.out.println(ConsoleColors.BLACK_BOLD + "|");
        }
        System.out.println("=================================================================" + ConsoleColors.RESET);
    }

    public void setPositionRandom(Navire n) {
        Random rand = new Random();
        Boolean isOk = false;
        while (!isOk) {
            if (n.getOrientation() == "verticale") {
                Point point = new Point();
                point.x = rand.nextInt(15);
                point.y = rand.nextInt(15 - n.getTaille());
                int nbCheck = 0;
                for (int i = point.y; i < point.y + (n.getTaille()); i++) {
                    if (tableau[point.x][i] == null) {
                        nbCheck++;
                    }
                }
                if (nbCheck == n.getTaille()) {
                    n.setCoord(point);
                    for (int i = point.y; i < point.y + (n.getTaille()); i++) {
                        tableau[point.x][i] = n;
                        isOk = true;
                    }
                }
            } else {
                Point point = new Point();
                point.x = rand.nextInt(15 - n.getTaille());
                point.y = rand.nextInt(15);
                int nbCheck = 0;
                for (int i = point.x; i < point.x + (n.getTaille()); i++) {
                    if (tableau[i][point.y] == null) {
                        nbCheck++;
                    }
                }
                if (nbCheck == n.getTaille()) {
                    n.setCoord(point);
                    for (int i = point.x; i < point.x + (n.getTaille()); i++) {
                        tableau[i][point.y] = n;
                        isOk = true;
                    }
                }
            }
        }
    }

    public boolean setPosition(Navire n, int x, int y) {
        if (n.getOrientation() == "verticale") {
            Point point = new Point();
            point.x = x;
            point.y = y;
            int nbCheck = 0;
            for (int i = point.y; i < point.y + (n.getTaille()); i++) {
                if (tableau[point.x][i] == null) {
                    nbCheck++;
                }
            }
            if (nbCheck == n.getTaille()) {
                n.setCoord(point);
                for (int i = point.y; i < point.y + (n.getTaille()); i++) {
                    tableau[point.x][i] = n;
                }
                return true;
            }
            return false;
        } else {
            Point point = new Point();
            point.x = x;
            point.y = y;
            int nbCheck = 0;
            for (int i = point.x; i < point.x + (n.getTaille()); i++) {
                if (tableau[i][point.y] == null) {
                    nbCheck++;
                }
            }
            if (nbCheck == n.getTaille()) {
                n.setCoord(point);
                for (int i = point.x; i < point.x + (n.getTaille()); i++) {
                    tableau[i][point.y] = n;
                }
                return true;
            }
            return false;
        }
    }

    public static void controleNavire(int choix) throws SaisieErroneeException {
        if ((choix != 1) && (choix != 2) && (choix != 3) && (choix != 4)
                && (choix != 5) && (choix != 6) && (choix != 7) && (choix != 8)
                && (choix != 9) && (choix != 10)) {
            throw new SaisieErroneeException(
                    "Vous n'avez pas choisie un Navire valide, ressaisir: ");
        }

    }

    public static void controleNavire2(Navire n) throws SaisieErroneeException {
        if(!n.getAlive()){
            throw new SaisieErroneeException(
                    "Le navire choisit est coulé, vous ne pouvez pas tirer avec, ressaisir");
        }
    }


    public Navire getNavire(){

        System.out.println("choississez un navire: ");
        int choix;
        boolean correct = false;

        for (int i = 0; i < Navires.size(); i++) {
            System.out.println(i + 1 + ". " + Navires.get(i));
        }

        Scanner scan = new Scanner(System.in);
        do {
            choix = scan.nextInt();
            try {
                controleNavire(choix);
                controleNavire2(Navires.get(choix-1));
                correct=true;
                correct = true;
            } catch (SaisieErroneeException e) {
                System.out.println(e);
            }
        } while (!correct);
        return Navires.get(choix - 1);
    }

    public Navire getNavireAlea() {

        int choix;
        boolean correct = false;

        Random rand = new Random();
        do {
            choix = rand.nextInt(10) + 1;
            try {
                controleNavire(choix);
                controleNavire2(Navires.get(choix-1));
                correct=true;
            } catch (SaisieErroneeException e) {
                System.out.println(e);
            }
        } while (!correct);

        return Navires.get(choix - 1);
    }

    public static void controleDestroyer(String choix) throws SaisieErroneeException {
        if ((!choix.equals("y")) && (!choix.equals("n"))) {
            throw new SaisieErroneeException(
                    "Vous n'avez pas entrer y ou n, ressaisir: ");
        }
    }

    public void checkDestroyer(Destroyer tireur) {
        String choix;
        Scanner sc = new Scanner(System.in);

        if (tireur.getFusee()) {
            boolean ok = false;
            do {
                System.out.println("Il vous reste 1 fusee eclairante avec ce Destroyer, voulez-vous l'utiliser ? (y/n)");
                choix = sc.nextLine();
                try {
                    controleDestroyer(choix);
                    ok = true;
                    if (choix.equals("y")) {
                        tireur.setFusee(false);
                        tireur.setPuissanceTire(16);
                        System.out.println("tirreur :" + tireur.getFusee());
                    }
                } catch (SaisieErroneeException e) {
                    System.out.println(e);
                }
            } while (!ok);
        } else {
            tireur.setPuissanceTire(1);
        }
    }

    public void checkDestroyerAlea(Destroyer tireur) {

        if (tireur.getFusee()) {

            tireur.setFusee(false);
            tireur.setPuissanceTire(16);
            System.out.println("L'IA utilise une fusée éclairante");

        } else {
            tireur.setPuissanceTire(1);
        }
    }

    public void checkNavire(int x, int y, Navire tireur) {
        Point p = new Point();
        p.setLocation(x, y);
        if (getTableau()[x][y] != null) {
            getTableau()[x][y].impactTire(p, tireur);
        }
    }

    public boolean checkTirePossible(Point p, Navire tireur) {

        boolean possible = false;

        if (tireur.getPuissanceTire() == 4) {
            if ((p.x == 14) && (p.y < 14)) {
                checkNavire(p.x, p.y, tireur);
                checkNavire(p.x, p.y + 1, tireur);
                possible = true;
            }
            if ((p.x < 14) && (p.y == 14)) {
                checkNavire(p.x, p.y, tireur);
                checkNavire(p.x + 1, p.y, tireur);
                possible = true;
            }
            if ((p.x == 14) && (p.y == 14)) {
                checkNavire(p.x, p.y, tireur);
            }
        }
        if (tireur.getPuissanceTire() == 9) {
            if ((p.x == 0) && ((p.y != 14) && (p.y != 0))) {
                for (int i = p.x; i < p.x + 2; i++) {
                    for (int j = p.y - 1; j < p.y + 2; j++) {
                        checkNavire(i, j, tireur);
                    }
                }
                possible = true;
            }

            if ((p.x == 14) && ((p.y != 0) && (p.y != 14))) {

                for (int i = p.x - 1; i < p.x + 1; i++) {
                    for (int j = p.y - 1; j < p.y + 2; j++) {
                        checkNavire(i, j, tireur);
                    }
                }
                possible = true;
            }

            if ((p.y == 0) && ((p.x != 0) && (p.x != 14))) {

                for (int i = p.x - 1; i < p.x + 2; i++) {
                    for (int j = p.y; j < p.y + 2; j++) {
                        checkNavire(i, j, tireur);
                    }
                }
                possible = true;
            }
            if ((p.y == 14) && ((p.x != 0) && (p.x != 14))) {

                for (int i = p.x - 1; i < p.x + 2; i++) {
                    for (int j = p.y - 1; j < p.y + 1; j++) {
                        checkNavire(i, j, tireur);
                    }
                }
                possible = true;
            }

            if ((p.x == 0) && (p.y == 0)) {
                checkNavire(p.x, p.y, tireur);
                checkNavire(p.x + 1, p.y, tireur);
                checkNavire(p.x, p.y + 1, tireur);
                checkNavire(p.x + 1, p.y + 1, tireur);
                possible = true;
            }
            if ((p.x == 0) && (p.y == 14)) {
                checkNavire(p.x, p.y - 1, tireur);
                checkNavire(p.x, p.y, tireur);
                checkNavire(p.x + 1, p.y - 1, tireur);
                checkNavire(p.x + 1, p.y, tireur);
                possible = true;
            }
            if ((p.x == 14) && (p.y == 0)) {
                checkNavire(p.x - 1, p.y, tireur);
                checkNavire(p.x - 1, p.y + 1, tireur);
                checkNavire(p.x, p.y, tireur);
                checkNavire(p.x, p.y + 1, tireur);
                possible = true;
            }
            if ((p.x == 14) && (p.y == 14)) {
                checkNavire(p.x, p.y, tireur);
                checkNavire(p.x - 1, p.y, tireur);
                checkNavire(p.x, p.y - 1, tireur);
                checkNavire(p.x - 1, p.y - 1, tireur);
                possible = true;
            }
        }
        if ((tireur.getPuissanceTire() == 16) && ((p.y > 11) || (p.x > 11))) {
            for (int i = p.y; i <= 14; i++) {
                for (int j = p.x; i <= 14; i++) {
                    checkNavire(j, i, tireur);
                }
            }
            possible = true;
        }
        return possible;
    }

    public void rechercheNavire(Navire tireur) {

        if (tireur.getId() == 3) {
            checkDestroyer((Destroyer) tireur);
        }
        System.out.println("vous avez une puissance de tir de " + tireur.getPuissanceTire() + " visez bien");

        Point p = tireur.saisirTir();

        boolean possible = checkTirePossible(p, tireur);

        if (!possible) {
            if (tireur.getPuissanceTire() == 9) {
                for (int i = p.x - 1; i < p.x + 2; i++) {
                    for (int j = p.y - 1; j < p.y + 2; j++) {
                        checkNavire(i, j, tireur);
                    }
                }
            } else if (tireur.getPuissanceTire() == 4) {
                checkNavire(p.x, p.y, tireur);
                checkNavire(p.x + 1, p.y, tireur);
                checkNavire(p.x, p.y + 1, tireur);
                checkNavire(p.x + 1, p.y + 1, tireur);
            } else if (tireur.getPuissanceTire() == 16) {
                for (int i = p.x; i < p.x + 4; i++) {
                    for (int j = p.y; j < p.y + 4; j++) {
                        checkNavire(i, j, tireur);
                    }
                }
            } else {
                checkNavire(p.x, p.y, tireur);
            }
        }
    }

    public void rechercheNavireAlea(Navire tireur,Joueur joueur1) {
        Point p = new Point();
        boolean possible;
        Random rand = new Random();
        if (tireur.getId() == 3) {
            checkDestroyerAlea((Destroyer) tireur);
        }
        System.out.println("L'IA a chosi le navire : "+tireur);
        int x;
        int py;

        Navire[][] tabEnnemi = joueur1.getJGrille().getTableau();

        x = rand.nextInt(15);
        py = rand.nextInt(15);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (tabEnnemi[j][i] != null) {  // tableau ennemi
                    int temp = casetouche(tabEnnemi[j][i], i, j);
                    if (temp == 1) {
                    } else if (temp == 2) {
                        x = j;
                        py = i;
                    } else {
                    }
                } else {
                }
            }
        }

        p.setLocation(x, py);
        possible = checkTirePossible(p, tireur);

        if (!possible) {
            if (tireur.getPuissanceTire() == 9) {
                for (int i = p.x - 1; i < p.x + 2; i++) {
                    for (int j = p.y - 1; j < p.y + 2; j++) {
                        checkNavire(i, j, tireur);
                    }
                }
            } else if (tireur.getPuissanceTire() == 4) {
                checkNavire(p.x, p.y, tireur);
                checkNavire(p.x + 1, p.y, tireur);
                checkNavire(p.x, p.y + 1, tireur);
                checkNavire(p.x + 1, p.y + 1, tireur);
            } else if (tireur.getPuissanceTire() == 16) {
                for (int i = p.x; i < p.x + 4; i++) {
                    for (int j = p.y; j < p.y + 4; j++) {
                        checkNavire(i, j, tireur);
                    }
                }
            } else {
                checkNavire(p.x, p.y, tireur);
            }
        }

        System.out.println("L'IA a tiré ici : "+p);
    }

    public int casetouche(Navire n, int i, int j) {
        int[] tempo = n.getToucherTab();
        if (n.getOrientation() == "verticale") {
            if ((tempo[i - n.getCoord().y]) == 1) {
                return 1;
            } else if ((tempo[i - n.getCoord().y]) == 2) {
                return 2;
            } else return 0;
        } else {
            if ((tempo[j - n.getCoord().x]) == 1) {
                return 1;
            } else if ((tempo[j - n.getCoord().x]) == 2) {
                return 2;
            } else {
                return 0;
            }
        }
    }


}
