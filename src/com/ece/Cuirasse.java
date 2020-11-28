package com.ece;


public class Cuirasse extends Navire{


    Cuirasse(){
        this.setTaille(7);
        this.setId(1);
        this.setPuissanceTire(9);
        this.setToucherTab(new int[this.getTaille()]);
    }


    @Override
    public void tire() {

    }
}
