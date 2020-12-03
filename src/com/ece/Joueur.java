package com.ece;

import java.io.Serializable;

public class Joueur implements Serializable {
    static private final long serialVersionUID = 6L;
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
