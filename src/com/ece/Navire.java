package com.ece;
import java.awt.*;
import java.util.Scanner;

public abstract class Navire {
    Point coord = null;
    String orientation;

    public static class SaisieErroneeException extends Exception {
        public SaisieErroneeException(String s) {
            super(s);
        }
    }

    public static void controle(String direction) throws SaisieErroneeException {
        if (!direction.equals("D")||!direction.equals("d")||!direction.equals("G")||!direction.equals("g")||!direction.equals("H")||!direction.equals("h")||!direction.equals("B")||!direction.equals("b")||!direction.equals("X")||!direction.equals("x")) {
            throw new SaisieErroneeException("Vous n'avez pas rentrer une direction ( g = Gauche , d = Droit , h = Haut , b = Bas");
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

    protected abstract boolean canGoRight();
    protected abstract boolean canGoLeft();
    protected abstract boolean canGoUp();
    protected abstract boolean canGoDown();

    protected abstract void goDown();
    protected abstract void goUp();
    protected abstract void goLeft();
    protected abstract void goRight();

    private static String saisirDirection(Navire narive) {
        Scanner sc = new Scanner(System.in);
        boolean correct = false;
        String directionEntre;
        do{
            System.out.println("Saisissez une direction pour le navire : "+ narive);
            directionEntre = sc.nextLine();
            try {
                controle(directionEntre);
                correct = true;
            } catch (SaisieErroneeException e) {
                System.out.println(e);
            }
        }while(!correct);
        return directionEntre;
    }
}
