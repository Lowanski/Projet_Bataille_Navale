package com.ece;

import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Grille {

    Navire[][] tableau = new Navire[15][15];
    Cuirasse cuirasse;
    ArrayList<Croiseur> listCroiseur = new ArrayList();
    ArrayList<Destroyer> listDestroyer = new ArrayList();
    ArrayList<SousMarin> listSousmarin = new ArrayList();
    String[] cons = {" 0 "," 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "," 9 ","10 ","11 ","12 ","13 ","14 "};

    Grille(){
        cuirasse= new Cuirasse();
        setPositionRandom(cuirasse);

        for(int i =0; i<2;i++)
        {
            Croiseur croiseur = new Croiseur();
            setPositionRandom(croiseur);
            listCroiseur.add(croiseur);
        }
        for(int i =0; i<3;i++)
        {
            Destroyer destroyer = new Destroyer();
            setPositionRandom(destroyer);
            listDestroyer.add(destroyer);
        }
        for(int i =0; i<4;i++)
        {
            SousMarin sousMarin = new SousMarin();
            setPositionRandom(sousMarin);
            listSousmarin.add(sousMarin);
        }
    }

    public Navire[][] getTableau() {
        return tableau;
    }

    public void dessiner() {
        System.out.println("   | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O |");
        System.out.println("=================================================================");

        for(int i=0; i<15;i++)
        {
            System.out.print(cons[i]+ "|");
            for(int j=0; j<15; j++)
            {
                if(tableau[i][j] != null) {
                    System.out.print(" " + tableau[i][j].getId() + "  ");
                }
                else{
                    System.out.print("--- ");
                }
            }
            System.out.println("|");
        }
        System.out.println("=================================================================");

        cuirasse.canGoRight(this);
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

    public static class SaisieErroneeException extends Exception {
        public SaisieErroneeException(String s) {
            super(s);
        }
    }

    public static void controle(String direction) throws SaisieErroneeException {
        if (!direction.equals("D") || !direction.equals("d") || !direction.equals("G") || !direction.equals("g")
                || !direction.equals("H") || !direction.equals("h") || !direction.equals("B") || !direction.equals("b")
                || !direction.equals("X") || !direction.equals("x")) {
            throw new SaisieErroneeException(
                    "Vous n'avez pas rentrer une direction ( g = Gauche , d = Droit , h = Haut , b = Bas");
        }
    }

    public boolean bouger(Navire n){
        String direction;
        direction = saisirDirection(n);
        direction.toUpperCase();
        switch (direction){
            case "D":{
                boolean yes = n.canGoRight(this);
                if (yes){
                    n.goRight();
                }
            }

            case "G":{
                boolean yes = n.canGoLeft(this);
                if (yes){
                    n.goLeft();
                }
            }

            case "H":{
                boolean yes = n.canGoUp(this);
                if (yes){
                    n.goUp();
                }
            }

            case "B":{
                boolean yes = n.canGoDown(this);
                if (yes){
                    n.goDown();
                }
            }

            case "X":{
                return false;
            }
        }
        return true;
    }

    private static String saisirDirection(Navire narive) {
        Scanner sc = new Scanner(System.in);
        boolean correct = false;
        String directionEntre;
        do {
            System.out.println("Saisissez une direction pour le navire : " + narive);
            System.out.println("Ou entrer \"X\" pour sortir du mode de déplacement");
            directionEntre = sc.nextLine();
            try {
                controle(directionEntre);
                correct = true;
            } catch (SaisieErroneeException e) {
                System.out.println(e);
            }
        } while (!correct);
        sc.close();
        return directionEntre;
    }
}

