package com.ece;

public class Cuirasse extends Navire{



    Cuirasse(){
        this.setTaille(7);
        this.setPuissanceTire(9);
        this.setToucherTab(new int[this.getTaille()]);
    }


    @Override
    public void tire() {

    }

    @Override
    protected void goDown() {

    }

    @Override
    protected void goUp() {

    }

    @Override
    protected void goLeft() {

    }

    @Override
    protected void goRight() {

    }
}
