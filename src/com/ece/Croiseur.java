package com.ece;

public class Croiseur extends Navire{



    Croiseur(){
        this.setTaille(5);
        this.setPuissanceTire(4);
        this.setToucherTab(new int[this.getTaille()]);
    }


    @Override
    public void tire() {

    }
}
