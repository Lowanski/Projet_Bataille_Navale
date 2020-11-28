package com.ece;
import java.awt.*;

public abstract class Navire {

    private Point coord;
    private String orientation; // verticale ; horizontale
    private int taille;
    private int puissanceTire;
    private int id;
    protected int[] toucherTab;

    Navire() {
        coord = null;
        orientation = null;
        taille = 0;
        puissanceTire = 0;
        id =0;
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
        System.out.println("on y est");
        if ((orientation == "h")&& (getCoord().getX() + taille < 15 )&&(g.getTableau()[(int) getCoord()
                .getX()+1][(int) getCoord().getY()] == null))
                {
                    System.out.println("tu peux aller a droite");
                     return true;
                }
       
        else{ System.out.println("tu peux pas aller a droite");
            return false;
        }
        
    }

    protected boolean canGoLeft(Grille g) {
        if ((orientation == "h")&& (getCoord().getX() > 0 )&&(g.getTableau()[(int) getCoord().getX()-1][(int) getCoord().getY()]==null))
        return true;
        else
        return false;
    }

    protected boolean canGoUp(Grille g) {
        if ((orientation == "vertical")&& (getCoord().getY() > 0 )&&(g.getTableau()[(int) getCoord().getX()][(int)getCoord().getY()-1]==null))
        return true;
        else
        return false;
    }

    protected boolean canGoDown(Grille g) {
        if ((orientation == "vertical")&& (getCoord().getY() + taille < 15 )&&(g.getTableau()[(int)getCoord().getX()][(int)getCoord().getY()+1]==null))
        return true;
        else
        return false;
    }

    protected void goDown(){

    }

    protected void goUp(){

    }

    protected void goLeft(){

    }

    protected void goRight(){

    }

    abstract public void tire();

}
