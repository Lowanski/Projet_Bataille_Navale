package com.ece;

public class SousMarin extends Navire{


    SousMarin(){
        this.setTaille(1);
        this.setPuissanceTire(1);
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
