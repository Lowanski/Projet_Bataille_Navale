package com.ece;

import java.awt.*;
import java.io.Serializable;
import java.util.*;

import com.ece.Navire.SaisieErroneeException;
import com.sun.tools.javac.util.Pair;

/*** La Grille est une class qui s'occupe d'accueillir tous les navires d'un joueur. Des méthodes permettent d'afficher dans le
 * terminal la grille, soit en voyant les bateaux, soit sans les voir. Elle stock la list des cases sur lesquels l'IA doit tirer.
 * Elle permets aussi la recherche dans ses tableaux des navires. Et enfin checker les Navires.***/
public class Grille implements Serializable {

    Navire[][] tableau = new Navire[15][15];
    Cuirasse cuirasse;
    ArrayList<Croiseur> listCroiseur = new ArrayList<Croiseur>();
    ArrayList<Destroyer> listDestroyer = new ArrayList<Destroyer>();
    ArrayList<SousMarin> listSousmarin = new ArrayList<SousMarin>();
    ArrayList<Navire> Navires = new ArrayList<Navire>();
    ArrayList<Pair<Navire,Point>> listCaseATirer = new ArrayList<>();
    String[] cons = {" 0 ", " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", "10 ", "11 ", "12 ", "13 ", "14 "};

    //Constructeurs
    public Grille() {
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

    public Grille(ArrayList<Cuirasse> listCui, ArrayList<Croiseur> listCroi, ArrayList<Destroyer> listDestr, ArrayList<SousMarin> listSous) {
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

    //Methode d'affichage
    public void dessiner() {
        System.out.println(ConsoleColors.BLACK_BOLD + "   | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O |");
        System.out.println("=================================================================" + ConsoleColors.RESET);

        for (int i = 0; i < 15; i++) {
            System.out.print(ConsoleColors.BLACK_BOLD + cons[i] + "|" + ConsoleColors.RESET);
            for (int j = 0; j < 15; j++) {
                if (tableau[j][i] != null) {
                    int temp = casetouche(tableau[j][i], i, j);
                    if (temp == 1) {

                        System.out.print(ConsoleColors.RED_BOLD + " X  " +ConsoleColors.RESET);
                    }
                    else if(temp==2)
                    {
                        System.out.print(ConsoleColors.YELLOW_BOLD+" " + tableau[j][i].getId() + "  "+ ConsoleColors.RESET);
                    }
                    else {
                        System.out.print(" " + tableau[j][i].getId() + "  " );
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

    //Controle des entrées clavier
    public static void controleNavire(int choix) throws SaisieErroneeException {

        if ((choix < 1) || (choix > 10)){
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

    public static void controleDestroyer(String choix) throws SaisieErroneeException {
        if ((!choix.equals("y")) && (!choix.equals("n"))) {
            throw new SaisieErroneeException(
                    "Vous n'avez pas entrer y ou n, ressaisir: ");
        }
    }

    // Methodes
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

        // L'IA tire aleatoirement sur le tableau ennemi
        x = rand.nextInt(15);
        py = rand.nextInt(15);

        // Si un navire ennemi est découvert par une fusée eclairante , l'IA va lui tirer dessus
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (tabEnnemi[j][i] != null) {  // tableau ennemi
                    int temp = casetouche(tabEnnemi[j][i], i, j);
                    if (temp == 2) {
                        x = j;
                        py = i;
                    }
                }
            }
        }

        if(listCaseATirer.size() != 0){
            Pair maPair = listCaseATirer.get(0);
            Point pList = (Point) maPair.snd;
            x = pList.x;
            py = pList.y;
            listCaseATirer.remove(0);
        }
        // Si l'IA touche un ennemi avec un tire
        // Création d'une liste de map (Navire,case à tirer) de case à tirer (Gauche, Haut, Droit, Bas)
        // Si un bateau est a nouveau toucher on rajoute les instructions à la liste precédente
        // L'instruction ne sera pas prise en compte si la case ou tirer pocède un navire detruit
        // Si la liste est vide, on laisse l'aleatoire se charger du tire


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

    public void addListCaseATirer(Navire n,Point p,Navire tireur){
        Pair<Navire,Point> posATirerG = null;
        Pair<Navire,Point> posATirerH = null;
        Pair<Navire,Point> posATirerD = null;
        Pair<Navire,Point> posATirerB = null;
        int puissanceTire = tireur.getPuissanceTire();
        if (puissanceTire == 9){
            int posxTireG = p.x - 2;
            int posyTireG = p.y;
            if (posxTireG<0){
                posxTireG = 0;
            }

            int posxTireH = p.x;
            int posyTireH = p.y - 2;
            if (posyTireH<0){
                posyTireH = 0;
            }

            int posxTireD = p.x + 2;
            int posyTireD = p.y;
            if (posxTireD>14){
                posxTireD = 14;
            }

            int posxTireB = p.x;
            int posyTireB = p.y+2;
            if (posyTireB>14){
                posyTireB = 14;
            }
            checkCaseATirer(posxTireG,posyTireG,posATirerG,n);
            checkCaseATirer(posxTireH,posyTireH,posATirerH,n);
            checkCaseATirer(posxTireD,posyTireD,posATirerD,n);
            checkCaseATirer(posxTireB,posyTireB,posATirerB,n);

        }else if (puissanceTire == 4){
            int posxTireG = p.x - 1;
            int posyTireG = p.y;
            if (posxTireG<0){
                posxTireG = 0;
            }

            int posxTireH = p.x;
            int posyTireH = p.y - 1;
            if (posyTireH<0){
                posyTireH = 0;
            }

            int posxTireD = p.x + 2;
            int posyTireD = p.y;
            if (posxTireD>14){
                posxTireD = 14;
            }

            int posxTireB = p.x;
            int posyTireB = p.y+2;
            if (posyTireB>14){
                posyTireB = 14;
            }
            checkCaseATirer(posxTireG,posyTireG,posATirerG,n);
            checkCaseATirer(posxTireH,posyTireH,posATirerH,n);
            checkCaseATirer(posxTireD,posyTireD,posATirerD,n);
            checkCaseATirer(posxTireB,posyTireB,posATirerB,n);

        }else if (puissanceTire == 1){
            int posxTireG = p.x - 1;
            int posyTireG = p.y;
            if (posxTireG<0){
                posxTireG = 0;
            }

            int posxTireH = p.x;
            int posyTireH = p.y - 1;
            if (posyTireH<0){
                posyTireH = 0;
            }

            int posxTireD = p.x + 1;
            int posyTireD = p.y;
            if (posxTireD>14){
                posxTireD = 14;
            }

            int posxTireB = p.x;
            int posyTireB = p.y+1;
            if (posyTireB>14){
                posyTireB = 14;
            }
            checkCaseATirer(posxTireG,posyTireG,posATirerG,n);
            checkCaseATirer(posxTireH,posyTireH,posATirerH,n);
            checkCaseATirer(posxTireD,posyTireD,posATirerD,n);
            checkCaseATirer(posxTireB,posyTireB,posATirerB,n);
        }else return;
    }

    public void removeNavColeListCaseATire(Navire n){
        int compteur=0;
        while (compteur < listCaseATirer.size()){
            if (listCaseATirer.get(compteur).fst == n){
                listCaseATirer.remove(compteur);
            }else{
                compteur++;
            }
        }
    }

    //Les Checks
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
            getTableau()[x][y].impactTire(p, tireur,this);
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

    private void checkCaseATirer(int posxTire, int posyTire, Pair<Navire, Point> posATirer, Navire n) {
        if (getTableau()[posxTire][posyTire] != null) {  // tableau ennemi
            int temp = casetouche(getTableau()[posxTire][posyTire], posyTire, posxTire);
            if (temp == 1) {
            }
            else{
                Point pTire = new Point(posxTire,posyTire);
                posATirer = new Pair<>(n,pTire);
                listCaseATirer.add(posATirer);
            }
        }

    }

    //Setters
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

    //Getters
    public Navire getNavire(){

        System.out.println("choississez un navire: ");
        int choix=-10;
        boolean correct = false;

        for (int i = 0; i < Navires.size(); i++) {
            System.out.println(i + 1 + ". " + Navires.get(i));
        }

        do {
            try {
                Scanner scan = new Scanner(System.in);
                choix = scan.nextInt();
            }catch (Exception e){
                e.printStackTrace();
            }
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

    public Navire[][] getTableau() {
        return tableau;
    }

    public ArrayList<Navire> gettouslesNavires(){
        return Navires;
    }
}
