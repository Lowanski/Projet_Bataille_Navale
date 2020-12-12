package com.ece;


import java.io.Serializable;

public class Croiseur extends Navire implements Serializable {


    
    Croiseur(){
        this.setTaille(5);
        this.setId(2);
        this.setPuissanceTire(4);
        this.setToucherTab(new int[this.getTaille()]);
    }


}
