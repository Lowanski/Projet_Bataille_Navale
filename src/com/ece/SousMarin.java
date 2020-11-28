package com.ece;

public class SousMarin extends Navire{


    SousMarin(){
        this.setTaille(1);
        this.setId(1);
        this.setPuissanceTire(1);
        this.setToucherTab(new int[this.getTaille()]);
    }



    @Override
    public void tire() {

    }
}
