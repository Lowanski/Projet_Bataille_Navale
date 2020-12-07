package com.ece;

import java.io.Serializable;

public class Destroyer extends Navire implements Serializable {

    private boolean fuse;

    Destroyer(){
        this.setTaille(3);
        this.setToucherTab(new int[this.getTaille()]);
        this.setId(3);
        this.setPuissanceTire(1);
        fuse =true;
    }
    
    public boolean getFusee(){
        return fuse;
    }

    public void setFusee(boolean fusee){
        fuse=fusee;
    }

    @Override
    public int getPuissanceTire(){
            return puissanceTire;
    }

    
}
