package com.ece;

import java.io.Serializable;

/*** SousMarin est une class fille de Navire qui a comme taille 1 et comme puissance de tire 1. Par rapport aux autres, le sous-matin peut
 * couler les sous-marins ennemis en plus des autres navire (alors que les autres navires ne peuvent pas toucher les sous-marins). Il ne peut
 * pas se faire detecter par une fusée éclairante ***/
public class SousMarin extends Navire implements Serializable {

    SousMarin(){
        this.setTaille(1);
        this.setId(4);
        this.setPuissanceTire(1);
        this.setToucherTab(new int[this.getTaille()]);
    }



}
