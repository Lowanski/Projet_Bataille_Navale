package Controleur.ece;

import com.ece.*;
import com.ece.Menu;
import com.sun.tools.javac.util.Pair;
import vue.Accueil;
import vue.JButtonmod;
import vue.Jeu;
import javax.swing.*;
import java.awt.event.ActionEvent;



public class ControllerPartie {

    private static JFrame fenetre = new JFrame();
    private static Accueil f1 = new Accueil();
    private static Jeu j1 = new Jeu();
    private static Jeu j2 = new Jeu();
    private static Pair<Integer, Integer> selected;
    private static int selection = 0;
    private static Grille Grille1 = new Grille();
    private static Grille Grille2 = new Grille();
    private static Joueur joueur1 = new Joueur(Grille1, Grille2);
    private static Joueur joueur2 = new Joueur(Grille2, Grille1);


    public static void main(String[] arg)
    {
        fenetre.setTitle("Bataille Navale");

        fenetre.setSize(500,500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setContentPane(f1);
        fenetre.setVisible(true);



    }

    private static void charger() {
        Menu.chargerPartie();
    }


    private static void aide() {

    }

    public static void jouer()
    {

        int nombre;

        Grille1.dessiner();
        fenetre.remove(f1);
        fenetre.setSize(1640,640);
        fenetre.setLocationRelativeTo(null);
        fenetre.setContentPane(j1.dessiner(joueur1.getJGrille(),joueur1.getOGrille()));
        fenetre.revalidate();
        fenetre.repaint();
        fenetre.setVisible(true);

        play(joueur1, joueur2);


    }

    private static void play(Joueur joueur1, Joueur joueur2) {

    }


    public static void actionPerformed(ActionEvent e)
    {
        JButtonmod a = (JButtonmod)e.getSource();

        //System.out.println(a.getIntx()+","+a.getInty());
        selected = new Pair<>(a.getIntx(),  a.getInty());
        System.out.println(selected.fst + "  " + selected.snd);

    }

    public static void actionMenu(ActionEvent e)
    {
        JButton a = (JButton)e.getSource();
        System.out.println(a.getText());
        selected =null;
        if(a.getText()== "Tirer")
        {
            selection = 1;
            do{
            doAction(joueur1, joueur2, selection);}

            while(selected==null);
        }
        else if(a.getText()== "Déplacer")
        {
            selection=2;
        }
        else if(a.getText()== "Sauver et quitter")
        {
            selection=3;
        }
    }

    public static int doAction(Joueur joueur1, Joueur joueur2, int nombre) {
        int etatPartie;
        switch (nombre) {
            case 1:

                ///choisir le bateau et tirer
                Navire tireur = joueur1.getJGrille().getNavire();
                if (joueur1.useAction()) {
                    joueur1.getOGrille().rechercheNavire(tireur);
                }
                etatPartie = 1;
                break;

            case 2:
                /// choisir le bateau et bouger si il peut
                boolean ok;
                if (joueur1.useAction()) {
                    do {
                        ok = joueur1.getJGrille().getNavire().canMove(joueur1.getJGrille());
                    } while (!ok);
                }
                joueur1.useAction();
                etatPartie = 1;
                break;

            case 3:
               //sauvegarderPartie(joueur1, joueur2);
                etatPartie = -1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selection);
        }
        return etatPartie;
    }
}
