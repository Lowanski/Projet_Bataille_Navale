package com.ece;

public class Joueur {
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
}
