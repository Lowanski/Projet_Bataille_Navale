package com.ece;

public class Destroyer extends Navire{



    Destroyer(){
        this.setTaille(3);
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
