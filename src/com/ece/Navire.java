package com.ece;
import java.awt.*;
import java.util.Scanner;

public abstract class Navire {

    private Point coord;
    private String orientation; // verticale ; horizontale
    private int taille;
    private int puissanceTire;
    private int id;

    Navire() {
        coord = null;
        orientation = null;
        taille = 0;
        puissanceTire = 0;
        id =0;
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

    abstract public void tire();

    public boolean bouger(){
        String direction;
        direction = saisirDirection(this);
        direction.toUpperCase();
        switch (direction){
            case "D":{
                boolean yes = this.canGoRight();
                if (yes){
                    this.goRight();
                }
            }

            case "G":{
                boolean yes = this.canGoLeft();
                if (yes){
                    this.goLeft();
                }
            }

            case "H":{
                boolean yes = this.canGoUp();
                if (yes){
                    this.goUp();
                }
            }

            case "B":{
                boolean yes = this.canGoDown();
                if (yes){
                    this.goDown();
                }
            }

            case "X":{
                return false;
            }
        }
        return true;
    }

    protected boolean canGoRight() {
        if (orientation == "h")//&& (coord x + taille < ?bord? )
        return true;
        else
        return false;
    }

    protected boolean canGoLeft() {
        if (orientation == "h")//&& (coord x > ?bord? )
        return true;
        else
        return false;
    }

    protected boolean canGoUp() {
        if (orientation == "v")//&& (coord y > ?bord? )
        return true;
        else
        return false;
    }

    protected boolean canGoDown() {
        if (orientation == "v")//&& (coord y + taille > ?bord? )
        return true;
        else
        return false;
    }

    protected abstract void goDown();

    protected abstract void goUp();

    protected abstract void goLeft();

    protected abstract void goRight();

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
