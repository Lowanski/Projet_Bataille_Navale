package com.ece;

import java.io.Serializable;

/*** Destroyer est une class fille de Navire qui a comme taille 3 et comme puissance de tire 1. En plus des autres, il peut tirer une
 * fusée éclairante afin de voir les Navires ennemis ***/
public class Destroyer extends Navire implements Serializable {

    private boolean fuse;

    Destroyer(){
        this.setTaille(3);
        this.setToucherTab(new int[this.getTaille()]);
        this.setId(3);
        this.setPuissanceTire(1);
        fuse =true;
    }
    
    public boolean getFusee(){
        return fuse;
    }

    public void setFusee(boolean fusee){
        fuse=fusee;
    }    
}
