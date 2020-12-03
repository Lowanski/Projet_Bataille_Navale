package com.ece;

import javax.swing.text.StyledEditorKit;



import java.awt.*;
import java.io.Serializable;
import java.nio.file.FileSystemAlreadyExistsException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Grille implements Serializable {

    Navire[][] tableau = new Navire[15][15];
    Cuirasse cuirasse;
    ArrayList<Croiseur> listCroiseur = new ArrayList<Croiseur>();
    ArrayList<Destroyer> listDestroyer = new ArrayList<Destroyer>();
    ArrayList<SousMarin> listSousmarin = new ArrayList<SousMarin>();
    ArrayList <Navire> Navires = new ArrayList<Navire>();
    String[] cons = {" 0 "," 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "," 9 ","10 ","11 ","12 ","13 ","14 "};

    Grille(){
        cuirasse= new Cuirasse();
        setPositionRandom(cuirasse);
        Navires.add(cuirasse);

        for(int i =0; i<2;i++)
        {
            Croiseur croiseur = new Croiseur();
            setPositionRandom(croiseur);
            listCroiseur.add(croiseur);
            Navires.add(croiseur);
        }
        for(int i =0; i<3;i++)
        {
            Destroyer destroyer = new Destroyer();
            setPositionRandom(destroyer);
            listDestroyer.add(destroyer);
            Navires.add(destroyer);
        }
        for(int i =0; i<4;i++)
        {
            SousMarin sousMarin = new SousMarin();
            setPositionRandom(sousMarin);
            listSousmarin.add(sousMarin);
            Navires.add(sousMarin);
        }
    }

    Grille(ArrayList<Cuirasse> listCui,ArrayList<Croiseur> listCroi, ArrayList<Destroyer> listDestr, ArrayList<SousMarin> listSous){
        for (Cuirasse cui:listCui) {
            Point coord = cui.getCoord();
            setPosition(cui,coord.x,coord.y);
        }
        for (Croiseur croi:listCroi) {
            Point coord = croi.getCoord();
            setPosition(croi,coord.x,coord.y);
        }
        for (Destroyer destr:listDestr) {
            Point coord = destr.getCoord();
            setPosition(destr,coord.x,coord.y);
        }
        for (SousMarin sous:listSous) {
            Point coord = sous.getCoord();
            setPosition(sous,coord.x,coord.y);
        }
    }

    public void setListCroiseur(ArrayList<Croiseur> c){
        listCroiseur = c;
    }
    public void setListDestroyer(ArrayList<Destroyer> d){
        listDestroyer = d;
    }
    public void setListSousmarin(ArrayList<SousMarin> sm){
        listSousmarin = sm;
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
                if(tableau[j][i] != null) {
                    System.out.print(" " + tableau[j][i].getId() + "  ");
                }
                else{
                    System.out.print("--- ");
                }
            }
            System.out.println("|");
        }
        System.out.println("=================================================================");

    }

    public void setPositionRandom(Navire n) {
        Random rand = new Random();
        Boolean isOk = false;
        while (!isOk) {
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

    public boolean setPosition(Navire n, int x, int y) {
        if (n.getOrientation() == "verticale") {
            Point point = new Point();
            point.x = x;
            point.y = y;
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
                }return true;
            }
            return false;
        } else {
            Point point = new Point();
            point.x = x;
            point.y = y;
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
                }return true;
            }
            return false;
        }
    }

    public Navire getNavire(){

        System.out.println("choississez un navire: ");
        int choix;

        for(int i=0; i<Navires.size();i++){
            System.out.println(i+1 +". "+Navires.get(i));
        }

        Scanner scan = new Scanner(System.in);
            do {
                choix = scan.nextInt();
                if ((choix < 1) || (choix > 10)) {
                    System.out.println("le nombre n'est pas valide, ressaissir: ");
                }
            } while ((choix < 1) || (choix > 10));

        return Navires.get(choix-1);
    }

    public void checkNavire(int x,int y){
        Point p=new Point();
        p.setLocation(x, y);
        if(getTableau()[x][y]!=null){
            getTableau()[x][y].impactTire(p);
        }
        
    }

    public boolean checkTirePossible(Point p,int puissance){
        
        boolean possible=false;

        if(puissance==4){
            if((p.x==14)&&(p.y<14)){
                checkNavire(p.x, p.y);
                checkNavire(p.x, p.y+1);
                possible=true;
            }
            if((p.x<14)&&(p.y==14)){
                checkNavire(p.x, p.y);
                checkNavire(p.x+1, p.y);
                possible=true;
            }
            if((p.x==14)&&(p.y==14)){
                checkNavire(p.x, p.y);
            }
        }
        if(puissance==9){
            if(p.x<1){
                checkNavire(p.x, p.y-1);
                checkNavire(p.x, p.y);
                checkNavire(p.x, p.y+1);

                checkNavire(p.x+1, p.y-1);
                checkNavire(p.x+1, p.y);
                checkNavire(p.x+1, p.y+1);

                possible=true;
            }

            if(p.y<1){
                checkNavire(p.x-1, p.y);
                checkNavire(p.x, p.y);
                checkNavire(p.x+1, p.y);

                checkNavire(p.x-1, p.y+1);
                checkNavire(p.x, p.y+1);
                checkNavire(p.x+1, p.y+1);

                possible=true;
            }

            if(p.y>=14){
                checkNavire(p.x-1, p.y);
                checkNavire(p.x, p.y);
                checkNavire(p.x+1, p.y);

                checkNavire(p.x-1, p.y-1);
                checkNavire(p.x, p.y-1);
                checkNavire(p.x+1, p.y-1);

                possible=true;
            }
            if(p.x>=14){
                checkNavire(p.x, p.y-1);
                checkNavire(p.x, p.y);
                checkNavire(p.x, p.y+1);

                checkNavire(p.x-1, p.y-1);
                checkNavire(p.x-1, p.y);
                checkNavire(p.x-1, p.y+1);

                possible=true;
            }

            if((p.x==0)&&(p.y==0)){
                checkNavire(p.x, p.y);
                checkNavire(p.x+1, p.y);
                checkNavire(p.x, p.y);
                checkNavire(p.x+1, p.y+1);
                possible=true;
            }
            if((p.x==0)&&(p.y==14)){
                checkNavire(p.x, p.y-1);
                checkNavire(p.x, p.y);
                checkNavire(p.x-1, p.y-1);
                checkNavire(p.x+1, p.y);
                possible=true;
            }
            if((p.x==14)&&(p.y==0)){
                checkNavire(p.x-1, p.y);
                checkNavire(p.x-1, p.y+1);
                checkNavire(p.x, p.y);
                checkNavire(p.x, p.y+1);
                possible=true;
            }
            if((p.x==14)&&(p.y==14)){
                checkNavire(p.x, p.y);
                checkNavire(p.x-1, p.y);
                checkNavire(p.x, p.y-1);
                checkNavire(p.x-1, p.y-1);
                possible=true;
            }
        }
        return possible;
    }

    public void rechercheNavire(Point p,int puissance){

        boolean possible=checkTirePossible(p,puissance);

        if(!possible){
            if(puissance==9){
            checkNavire(p.x-1, p.y-1);
            checkNavire(p.x, p.y-1);
            checkNavire(p.x+1, p.y-1);

            checkNavire(p.x-1, p.y);
            checkNavire(p.x, p.y);
            checkNavire(p.x+1, p.y);

            checkNavire(p.x-1, p.y+1);
            checkNavire(p.x, p.y+1);
            checkNavire(p.x+1, p.y+1);
            }
            else if(puissance==4){
            checkNavire(p.x, p.y);
            checkNavire(p.x+1, p.y);
            checkNavire(p.x, p.y+1);
            checkNavire(p.x+1, p.y+1);
            }
            else{
            checkNavire(p.x, p.y);
            }
        }
    }
}

