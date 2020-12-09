package Controleur.ece;

import com.ece.Menu;
import vue.Accueil;


public class ControllerPartie {

    public static void main(String[] arg)
    {
        Accueil f1 = new Accueil();
        f1.setVisible(true);
        /*if(val ==1) {
            f1.setVisible(false);
            jouer();
        }
        if(val==2)
        {
            charger();
        }
        if(val==3)
        {
            aide();
        }*/
    }

    private static void charger() {
        Menu.chargerPartie();
    }


    private static void aide() {

    }

    public static void jouer()
    {
        Menu.jouerPartie();

    }
}
