package com.ece;
import java.util.ArrayList;
import java.util.Random;

public class Grille {

    int[][] tableau = new int[15][15];

    public int[][] getTableau() {
        return tableau;
    }

    String[] cons = {" 0 "," 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "," 9 ","10 ","11 ","12 ","13 ","14 "};
    Cuirasse cuirasse = new Cuirasse();
    ArrayList<Croiseur> listCroiseur = new ArrayList();
    ArrayList<Destroyer> listDestroyer = new ArrayList();
    ArrayList<SousMarin> listSousmarin = new ArrayList();



    public void dessiner() {
        spawn();
        System.out.println("   | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O |");
        System.out.println("=================================================================");
        tableau[1][1] = 1;
        tableau[1][2] = 1;
        tableau[1][3] = 1;
        tableau[1][4] = 1;
        for(int i=0; i<15;i++)
        {
            System.out.print(cons[i]+ "|");
            for(int j=0; j<15; j++)
            {
                if(tableau[i][j] != 0) {
                    System.out.print(" " + tableau[i][j] + "  ");
                }
                else{
                    System.out.print("--- ");
                }
            }
            System.out.println("|");
        }
        System.out.println("=================================================================");
    }

    public void spawn() {
        for(int i =0; i<2;i++)
        {
            System.out.println("Numero : "+i);
            Croiseur croiseur = new Croiseur();
            listCroiseur.add(croiseur);
        }
        for(int i =0; i<3;i++)
        {
            System.out.println("Numero : "+i);
            Destroyer destroyer = new Destroyer();
            listDestroyer.add(destroyer);
        }
        for(int i =0; i<4;i++)
        {
            System.out.println("Numero : "+i);
            SousMarin sousMarin = new SousMarin();
            listSousmarin.add(sousMarin);
        }

        Random rand = new Random();
        int val;
        val = rand.nextInt(2);
        if(val ==0)
        {
            System.out.println(val);
            cuirasse.setOrientation("verticale");
        }
        else{
            System.out.println(val);
            cuirasse.setOrientation("horizontale");
        }

        for (Croiseur s : listCroiseur)
        {
            Random rando = new Random();
            int valeur;
            valeur = rand.nextInt(2);
            if(valeur ==0)
            {
                System.out.println(valeur);
                s.setOrientation("verticale");
            }
            else{
                System.out.println(valeur);
                s.setOrientation("horizontale");
            }
        }
        for (Destroyer s : listDestroyer)
        {
            Random rando = new Random();
            int valeur;
            valeur = rand.nextInt(2);
            if(valeur ==0)
            {
                System.out.println(valeur);
                s.setOrientation("verticale");
            }
            else{
                System.out.println(valeur);
                s.setOrientation("horizontale");
            }
        }
        for (SousMarin s : listSousmarin)
        {
            Random rando = new Random();
            int valeur;
            valeur = rand.nextInt(2);
            if(valeur ==0)
            {
                System.out.println(valeur);
                s.setOrientation("verticale");
            }
            else{
                System.out.println(valeur);
                s.setOrientation("horizontale");
            }
        }



    }

}

