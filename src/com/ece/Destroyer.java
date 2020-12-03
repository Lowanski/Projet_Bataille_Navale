package com.ece;

import java.io.Serializable;

public class Destroyer extends Navire implements Serializable {



    Destroyer(){
        this.setTaille(3);
        this.setToucherTab(new int[this.getTaille()]);
        this.setId(3);
        this.setPuissanceTire(1);
    }


    
}
