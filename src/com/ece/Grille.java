package com.ece;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Grille {

    Navire[][] tableau = new Navire[15][15];
    ArrayList<Croiseur> listCroiseur = new ArrayList();
    ArrayList<Destroyer> listDestroyer = new ArrayList();
    ArrayList<SousMarin> listSousmarin = new ArrayList();
    String[] cons = {" 0 "," 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "," 9 ","10 ","11 ","12 ","13 ","14 "};

    Grille(){
        spawn();
    }

    public Navire[][] getTableau() {
        return tableau;
    }

    public void dessiner() {
        System.out.println("   | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O |");
        System.out.println("=================================================================");

        for(int i=0; i<15;i++)
        {
            System.out.print(cons[i]+ "|");
            for(int j=0; j<15; j++)
            {
                if(tableau[i][j] != null) {
                    System.out.print(" " + tableau[i][j].getId() + "  ");
                }
                else{
                    System.out.print("--- ");
                }
            }
            System.out.println("|");
        }
        System.out.println("=================================================================");
    }

    public void setOrientationRandom(Navire n)
    {
        Random rand = new Random();
        int val;
        val = rand.nextInt(2);
        if(val ==0)
        {
            n.setOrientation("verticale");
        }
        else{
            n.setOrientation("horizontale");
        }
    }

    public void setPositionRandom(Navire n)
    {
        Random rand = new Random();
        Boolean isOk = false;
        while(!isOk) {
            if (n.getOrientation() == "verticale") {
                Point point = new Point();
                point.x = rand.nextInt(15);
                point.y = rand.nextInt(15 - n.getTaille());
                int nbCheck = 0;
                for (int i = point.y; i < point.y + (n.getTaille()); i++) {
                    if (tableau[point.x][i] == null) {
                        nbCheck++;
                    }
                }
                if (nbCheck == n.getTaille()) {
                    n.setCoord(point);
                    for (int i = point.y; i < point.y + (n.getTaille()); i++) {
                        tableau[point.x][i] = n;
                        isOk = true;
                    }
                }
            } else {
                Point point = new Point();
                point.x = rand.nextInt(15 - n.getTaille());
                point.y = rand.nextInt(15);
                int nbCheck = 0;
                for (int i = point.x; i < point.x + (n.getTaille()); i++) {
                    if (tableau[i][point.y] == null) {
                        nbCheck++;
                    }
                }
                if (nbCheck == n.getTaille()) {
                    n.setCoord(point);
                    for (int i = point.x; i < point.x + (n.getTaille()); i++) {
                        tableau[i][point.y] = n;
                        isOk = true;
                    }
                }
            }
        }
    }

    public void spawn() {

        Cuirasse cuirasse = new Cuirasse();
        cuirasse.setId(1);
        setOrientationRandom(cuirasse);
        setPositionRandom(cuirasse);

        for(int i =0; i<2;i++)
        {
            Croiseur croiseur = new Croiseur();
            croiseur.setId(2);
            setOrientationRandom(croiseur);
            setPositionRandom(croiseur);
            listCroiseur.add(croiseur);
        }
        for(int i =0; i<3;i++)
        {
            Destroyer destroyer = new Destroyer();
            destroyer.setId(3);
            setOrientationRandom(destroyer);
            setPositionRandom(destroyer);
            listDestroyer.add(destroyer);
        }
        for(int i =0; i<4;i++)
        {
            SousMarin sousMarin = new SousMarin();
            sousMarin.setId(4);
            setOrientationRandom(sousMarin);
            setPositionRandom(sousMarin);
            listSousmarin.add(sousMarin);
        }
    }
}

