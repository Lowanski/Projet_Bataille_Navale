package com.ece;

import java.io.Serializable;

public class Joueur implements Serializable {
    static private final long serialVersionUID = 6L;
    private int action;
    private Grille jGrille;
    private Grille oGrille;

    Joueur(Grille jG, Grille oG){
        jGrille = jG;
        oGrille = oG;
    }

    public Grille getJGrille(){
        return jGrille;
    }
    public Grille getOGrille(){
        return oGrille;
    }

    public int getAction() {
        return action;
    }

    public void setActionDebutTour() {
        this.action = 1;
    }

    public boolean useAction(){
        if (action > 0){
            action--;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkEtatJoueur(){
        for(Object i:  this.getJGrille().gettouslesNavires()){
            Navire n=(Navire)i;
            if(n.getAlive()){
               return true;
            }
        }
        return false;
    }
}
