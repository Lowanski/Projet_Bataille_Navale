package com.ece;


import java.io.Serializable;

/*** Cuirasse est une class fille de Navire qui a comme taille 7 et comme puissance de tire 9 ***/
public class Cuirasse extends Navire implements Serializable {

    Cuirasse(){
        this.setTaille(7);
        this.setId(1);
        this.setPuissanceTire(9);
        this.setToucherTab(new int[this.getTaille()]);
    }


}
