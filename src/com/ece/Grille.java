package com.ece;


public class Grille {

    int[][] tableau = new int[15][15];
    String[] cons = {" 0 "," 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "," 9 ","10 ","11 ","12 ","13 ","14 "};

    public void dessiner() {
        System.out.println("   | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O |");
        System.out.println("=================================================================");

        for(int i=0; i<15;i++)
        {
            System.out.print(cons[i]+ "|");
            for(int j=0; j<15; j++)
            {

                if(tableau[i][j] != 0) {
                    System.out.print(" " + tableau[i][j] + " ");
                }
                else{
                    System.out.print("--- ");
                }
            }
            System.out.println("|");
        }
        System.out.print("=================================================================");
    }
}

