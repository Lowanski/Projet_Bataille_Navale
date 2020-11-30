package com.ece;

import java.awt.*;
import java.util.Random;

public abstract class Navire {

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
        if(val ==0)
        orientation = "verticale";
        
        else
        orientation= "horizontale";
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
        if ((orientation == "horizontale")&& (getCoord().getX() + taille < 15 )&&(g.getTableau()[(int) getCoord().getX()+taille][(int) getCoord().getY()] == null)){
            return true;
        }
        else{
            return false;
        }
        
    }

    protected boolean canGoLeft(Grille g) {
        if ((orientation == "horizontale")&& (getCoord().getX() > 0 )&&(g.getTableau()[(int) getCoord().getX()-1][(int) getCoord().getY()]==null)){
            return true;
        }
        else{
           return false; 
        }
        
    }

    protected boolean canGoUp(Grille g) {
        if ((orientation == "verticale")&& (getCoord().getY() > 0 )&&(g.getTableau()[(int) getCoord().getX()][(int)getCoord().getY()-1]==null)){
            return true;
        }
        else{
            return false;
        }
    }

    protected boolean canGoDown(Grille g) {
        if ((orientation == "verticale")&& (getCoord().getY() + taille < 15 )&&(g.getTableau()[(int)getCoord().getX()][(int)getCoord().getY()+taille]==null)){
            return true; 
        }
        else{
            return false;
        }
            
        
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
