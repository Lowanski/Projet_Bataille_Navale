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
<<<<<<< HEAD
=======

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


>>>>>>> 912270c038c2555a446247eba561a95c7c46f275
}
