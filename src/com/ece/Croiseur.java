package com.ece;


public class Croiseur extends Navire{



    Croiseur(){
        this.setTaille(5);
        this.setId(2);
        this.setPuissanceTire(4);
        this.setToucherTab(new int[this.getTaille()]);
    }


}
