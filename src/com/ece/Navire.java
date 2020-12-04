package com.ece;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;


public abstract class Navire implements Serializable {

    private Point coord;
    private String orientation; // verticale ; horizontale
    protected int taille;
    protected int puissanceTire;
    protected int id;
    protected int[] toucherTab;

    Navire() {
        Random rand = new Random();
        int val;
        val = rand.nextInt(2);
        if (val == 0)
            orientation = "verticale";

        else
            orientation = "horizontale";
    }

    public void setToucherTab(int[] toucherTab) {
        this.toucherTab = toucherTab;
    }

    public int[] getToucherTab() {
        return toucherTab;
    }

    public void setPuissanceTire(int puissanceTire) {
        this.puissanceTire = puissanceTire;
    }

    public int getPuissanceTire() {
        return puissanceTire;
    }

    public void setCoord(Point coord) {
        this.coord = coord;
    }

    public Point getCoord() {
        return coord;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    protected boolean canGoRight(Grille g) {
        if ((orientation == "horizontale") && (getCoord().getX() + taille < 15) && (g.getTableau()[(int) getCoord().getX() + taille][(int) getCoord().getY()] == null)) {
            return true;
        } else {
            return false;
        }

    }

    protected boolean canGoLeft(Grille g) {
        if ((orientation == "horizontale") && (getCoord().getX() > 0) && (g.getTableau()[(int) getCoord().getX() - 1][(int) getCoord().getY()] == null)) {
            return true;
        } else {
            return false;
        }

    }

    protected boolean canGoUp(Grille g) {
        if ((orientation == "verticale") && (getCoord().getY() > 0) && (g.getTableau()[(int) getCoord().getX()][(int) getCoord().getY() - 1] == null)) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean canGoDown(Grille g) {
        if ((orientation == "verticale") && (getCoord().getY() + taille < 15) && (g.getTableau()[(int) getCoord().getX()][(int) getCoord().getY() + taille] == null)) {
            return true;
        } else {
            return false;
        }


    }

    protected void goDown(Grille g) {
        System.out.println("deplacement en bas");
        g.getTableau()[coord.x][coord.y] = null;
        g.getTableau()[coord.x][coord.y + taille] = this;
        coord.setLocation(coord.getX(), coord.getY() + 1);
    }

    protected void goUp(Grille g) {
        System.out.println("deplacement en haut");
        g.getTableau()[coord.x][coord.y + taille - 1] = null;
        g.getTableau()[coord.x][coord.y - 1] = this;
        coord.setLocation(coord.getX(), coord.getY() - 1);
    }

    protected void goLeft(Grille g) {
        System.out.println("deplacement a gauche");
        g.getTableau()[coord.x + taille - 1][coord.y] = null;
        g.getTableau()[coord.x - 1][coord.y] = this;
        coord.setLocation(coord.getX() - 1, coord.getY());
    }

    protected void goRight(Grille g) {
        System.out.println("deplacement a droite");
        g.getTableau()[coord.x][coord.y] = null;
        g.getTableau()[coord.x + taille][coord.y] = this;
        coord.setLocation(coord.getX() + 1, coord.getY());
    }

    public static class SaisieErroneeException extends Exception {
        public SaisieErroneeException(String s) {
            super(s);
        }
    }

    public static void controle(String direction) throws SaisieErroneeException {
        if (!direction.equals("D") && !direction.equals("d") && !direction.equals("G") && !direction.equals("g")
                && !direction.equals("H") && !direction.equals("h") && !direction.equals("B") && !direction.equals("b")
                && !direction.equals("X") && !direction.equals("x")) {
            throw new SaisieErroneeException(
                    "Vous n'avez pas rentrer une direction ( g = Gauche , d = Droit , h = Haut , b = Bas");
        }
    }

    private static String saisirDirection() {
        Scanner sc = new Scanner(System.in);
        boolean correct = false;
        String directionEntre;
        do {
            System.out.println("Saisissez une direction pour le navire : ");
            System.out.println("Ou entrer \"X\" pour sortir du mode de déplacement");
            directionEntre = sc.nextLine();
            try {
                controle(directionEntre);
                correct = true;
            } catch (SaisieErroneeException e) {
                System.out.println(e);
            }
        } while (!correct);
        return directionEntre;
    }

    public boolean bouger(Grille g) {
        String direction;
        boolean pass = false;
        do {
            direction = saisirDirection();
            direction = direction.toUpperCase();
            switch (direction) {
                case "D": {
                    boolean yes = canGoRight(g);
                    if (yes) {
                        goRight(g);
                        pass = true;
                    } else {
                        System.out.println("vous ne pouvez pas aller a droite");
                    }
                    break;
                }

                case "G": {
                    boolean yes = canGoLeft(g);
                    if (yes) {
                        goLeft(g);
                        pass = true;
                    } else {
                        System.out.println("vous ne pouvez pas aller a gauche");
                    }
                    break;
                }

                case "H": {
                    boolean yes = canGoUp(g);
                    if (yes) {
                        goUp(g);
                        pass = true;
                    } else {
                        System.out.println("vous ne pouvez pas monter");
                    }
                    break;
                }

                case "B": {
                    boolean yes = canGoDown(g);
                    if (yes) {
                        goDown(g);
                        pass = true;
                    } else {
                        System.out.println("vous ne pouvez pas descendre");
                    }
                    break;
                }
                case "X": {
                    return false;
                }
            }
        } while (!pass);
        g.getTableau()[coord.x][coord.y] = this;
        System.out.println("Deplacement de " + this + " sur " + coord);
        return true;
    }

    public boolean canMove(Grille g) {
        int touch = 0;
        for (Object i : toucherTab) {
            if ((int) i != 0) {
                touch++;
            }
        }
        if (touch != 0) {
            System.out.println(this + " ne peut pas bouger car il est touche");
            return false;
        } else {
            bouger(g);
            return true;
        }

    }

    public Point tirer(Point p) {
        return p;
    }

    public static void controleTire(String caseSelected) throws SaisieErroneeException {
        int convert;
        char x;
        String y;

        x = caseSelected.charAt(0);
        y = caseSelected.substring(1);
        convert = Integer.parseInt(y);

        if (((x < 'a') && (x > 'o')) && ((convert <= 15) && (convert >= 0)))
            throw new SaisieErroneeException("Vous n'avez pas rentrer une case de type : B4 ");

    }

    public Point saisirTir() {

        String chaine;
        char x = 0;
        String y;
        int convert = 0;
        boolean correct=false;

        Scanner scan = new Scanner(System.in);
        Point tir = new Point();

        do {
            System.out.println("choisissez des coordonnées de tire: ex(B12)");
            chaine = scan.nextLine();

            /// FAIRE UN TRY CATCH POUR GERER les mauvaises entrées @LOWAN -----------------------------------------
            try {
                controleTire(chaine);
                x = chaine.charAt(0);
                y = chaine.substring(1);
                convert = Integer.parseInt(y);
                correct = true;
            } catch (SaisieErroneeException e) {
                e.printStackTrace();
            }


        } while (!correct);

        tir.x = Character.getNumericValue(x) - 10;
        tir.y = convert;

        System.out.println(tir);
        return tirer(tir);
    }

    public void impactTire(Point impact,Navire tireur){

        if(tireur.getPuissanceTire()!=16){  

            if(this.getId()!=4){ //si ce n'est pas un sous-marin
                
                if(getOrientation()=="verticale"){
                    toucherTab[impact.y-coord.y]=1;
                }
                else{
                    toucherTab[impact.x-coord.x]=1;
                }
                System.out.println("le bateau "+this+" qui a pour coordonnée "+ coord +"est touche au point : "+impact);
            }
            else{ //si c'est un sous-marin
                
                if(tireur.getId()==4){ //si le tireur est un sous-marin
                    toucherTab[0]=1;
                    System.out.println("le bateau "+this+" qui a pour coordonnée "+ coord +"est touche au point : "+impact);
                }
            }

            // on verifie si le bateau est coulee
            int nb=0;
            for(Object i: toucherTab){
                if((int)i!=0){nb++;}
            }                                           
            if(nb==taille){
                 this.setId(0);
                 System.out.println(this+" a ete coulee");
            }

        }
        else{
            System.out.println("on ne tire pas on devoile seulement les cibles");
            /// on montre son id
        }
    }
}