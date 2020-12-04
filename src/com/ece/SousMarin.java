package com.ece;

import java.io.Serializable;

public class SousMarin extends Navire implements Serializable {


    SousMarin(){
        this.setTaille(1);
        this.setId(4);
        this.setPuissanceTire(1);
        this.setToucherTab(new int[this.getTaille()]);
    }



}
