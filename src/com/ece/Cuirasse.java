package com.ece;


import java.io.Serializable;

public class Cuirasse extends Navire implements Serializable {


    Cuirasse(){
        this.setTaille(7);
        this.setId(1);
        this.setPuissanceTire(9);
        this.setToucherTab(new int[this.getTaille()]);
    }


}
