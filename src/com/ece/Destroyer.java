package com.ece;

public class Destroyer extends Navire{



    Destroyer(){
        this.setTaille(3);
        this.setToucherTab(new int[this.getTaille()]);
        this.setId(3);
        this.setPuissanceTire(1);
    }


    
}
