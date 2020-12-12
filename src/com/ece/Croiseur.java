package com.ece;


import java.io.Serializable;

/*** Croiseur est une class fille de Navire qui a comme taille 5 et comme puissance de tire 4 ***/
public class Croiseur extends Navire implements Serializable {

    Croiseur(){
        this.setTaille(5);
        this.setId(2);
        this.setPuissanceTire(4);
        this.setToucherTab(new int[this.getTaille()]);
    }


}
